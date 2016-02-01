package com.example.selenium.spree;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class ScreenshotRule extends TestWatcher {

  private final SeleniumTest seleniumTest;
  private final String screenShotDir;

  public ScreenshotRule(SeleniumTest seleniumTest, String screenShotDir) {
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
    File dir = new File(screenShotDir);
    if (dir.exists() == false) dir.mkdirs();

    File srcFile = webDriver.getScreenshotAs(OutputType.FILE);
    int pos = srcFile.getName().lastIndexOf(".");
    String fileExt = srcFile.getName().substring(pos+1);

    String fileName = String.format("screen-shot-%s.%s", description.getDisplayName(), fileExt);
    File dstFile = new File(screenShotDir, fileName);

    try {
      FileUtils.copyFile(srcFile, dstFile);
      System.out.println("Screen shot saved to " + dstFile.getAbsolutePath());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}