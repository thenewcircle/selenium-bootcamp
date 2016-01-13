package com.example.selenium.spree.utils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

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
  protected void failed(Throwable exception, Description description) {
    RichWebDriver webDriver = seleniumTest.getRichWebDriver();
    if (webDriver == null) {
      log.error("The web driver does not exist.");
      return;
    } else if (webDriver.isHtmlUnit()) {
      log.error("HtmlUnit does not support taking screen shots.");
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

    log.info("Screen shot saved to {}", dstFile.getAbsolutePath());
  }

  @Override
  protected void finished(Description description) {
    RichWebDriver webDriver = seleniumTest.getRichWebDriver();
    if (webDriver != null) {
      webDriver.quit();
    }
  }
}

