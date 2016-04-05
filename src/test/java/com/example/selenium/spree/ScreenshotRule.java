package com.example.selenium.spree;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.selenium.Selenium;

public class ScreenshotRule extends TestWatcher {

  private static final Logger log = LoggerFactory.getLogger(ScreenshotRule.class);

  private final SeleniumTest seleniumTest;
  private final File screenShotDir;

  public ScreenshotRule(SeleniumTest seleniumTest, String screenShotDir) {
    this(seleniumTest, new File(screenShotDir));
  }

  public ScreenshotRule(SeleniumTest seleniumTest, File screenShotDir) {
    this.seleniumTest = seleniumTest;
    this.screenShotDir = screenShotDir;
  }

  @Override
  protected void finished(Description description) {
    RemoteWebDriver webDriver = seleniumTest.getRemoteWebDriver();
    if (webDriver != null) {
      webDriver.quit();
    }
  }

  @Override
  protected void failed(Throwable exception, Description description) {
    RemoteWebDriver webDriver = seleniumTest.getRemoteWebDriver();
    if (webDriver == null) {
      log.error("The web driver does not exist.");
      return;
    }

    if (screenShotDir.exists() == false) {
      if (screenShotDir.mkdirs() == false) {
        log.error("Unable to create the working directory ({}) for capturing screen shots.", screenShotDir.getAbsolutePath());
      }
    }

    File srcFile;

    try {
      srcFile = webDriver.getScreenshotAs(OutputType.FILE);
    } catch (Exception ex) {
      log.error("Unable to capture screen shot: {}", ex.getMessage());
      return;
    }

    int pos = srcFile.getName().lastIndexOf(".");
    String fileExt = srcFile.getName().substring(pos+1);

    String fileName = String.format("screen-shot-%s.%s", description.getDisplayName(), fileExt);
    File dstFile = new File(screenShotDir, fileName);

    if (dstFile.exists() && dstFile.delete() == false) {
      log.error("Unable to delete previous screen shot: {}", dstFile.getAbsolutePath());
      return;
    }

    try {
      FileUtils.copyFile(srcFile, dstFile);
    } catch (IOException ioe) {
      log.error("Unable to copy temporary screen shot:\nsrc: {}\ndst: {}", srcFile.getAbsolutePath(), dstFile.getAbsolutePath());
    }

    log.warn("Screen shot saved to {}", dstFile.getAbsolutePath());
  }
}
