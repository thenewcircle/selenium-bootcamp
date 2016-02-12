package com.example.selenium.spree;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotRule extends TestListenerAdapter {

  private static final Logger log = LoggerFactory.getLogger(ScreenshotRule.class);

  private final RemoteWebDriver webDriver;
  private final File screenShotDir;

  public ScreenshotRule(RemoteWebDriver webDriver, String screenShotDir) {
    this(webDriver, new File(screenShotDir));
  }

  public ScreenshotRule(RemoteWebDriver webDriver, File screenShotDir) {
    this.webDriver = webDriver;
    this.screenShotDir = screenShotDir;
  }

//  @Override
//  protected void finished(Description description) {
//    RemoteWebDriver webDriver = seleniumTest.getWebDriver();
//    if (webDriver != null) {
//      webDriver.quit();
//    }
//  }

  @Override
  public void onTestFailure(ITestResult tr) {
    if (webDriver == null) {
      log.error("The web driver does not exist.");
      return;
    }

    if (screenShotDir.exists() == false) {
      if (screenShotDir.mkdirs() == false) {
        log.error("Unable to create the working directory ({}) for capturing screen shots.", screenShotDir.getAbsolutePath());
        return;
      }
    }

    File srcFile;

    try {
      srcFile = webDriver.getScreenshotAs(OutputType.FILE);
//      Robot robot = new java.awt.Robot();
//      robot.
      
    } catch (Exception ex) {
      log.error("Unable to capture screen shot: {}", ex.getMessage());
      return;
    }

    int pos = srcFile.getName().lastIndexOf(".");
    String fileExt = srcFile.getName().substring(pos+1);

    String fileName = String.format("screen-shot-%s.%s", tr.getName(), fileExt);
    File dstFile = new File(screenShotDir, fileName);

    if (dstFile.exists() && dstFile.delete() == false) {
      log.error("Unable to delete previous screen shot: {}", dstFile.getAbsolutePath());
      return;
    }

    try {
      FileUtils.copyFile(srcFile, dstFile);
    } catch (IOException ioe) {
      log.error("Unable to copy temporary screen shot:\nsrc: {}\ndst: {}", srcFile.getAbsolutePath(), dstFile.getAbsolutePath());
      return;
    }

    log.warn("Screen shot saved to {}", dstFile.getAbsolutePath());
  }
}
