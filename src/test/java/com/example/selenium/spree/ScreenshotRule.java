package com.example.selenium.spree;

import java.awt.Dimension;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotRule extends TestListenerAdapter {

  private static final Logger log = LoggerFactory.getLogger(ScreenshotRule.class);

  private final File screenShotDir;

  public ScreenshotRule() {
    String path = System.getenv("tests.selenium.screenshots");
    this.screenShotDir = new File("c:\tmp\test-failures");
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
    if (screenShotDir.exists() == false) {
      if (screenShotDir.mkdirs() == false) {
        log.error("Unable to create the working directory ({}) for capturing screen shots.", screenShotDir.getAbsolutePath());
        return;
      }
    }

    File srcFile;

    try {
      Dimension ss = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
      java.awt.Rectangle r = new java.awt.Rectangle(ss);
      java.awt.image.BufferedImage image = new Robot().createScreenCapture(r);
      ImageIO.write(image, "png", dstFile);
      
    } catch (Exception ex) {
      log.error("Unable to capture screen shot: {}", ex.getMessage());
      return;
    }

    log.warn("Screen shot saved to {}", dstFile.getAbsolutePath());
  }
}
