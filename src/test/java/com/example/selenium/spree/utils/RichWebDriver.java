package com.example.selenium.spree.utils;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RichWebDriver implements

	JavascriptExecutor,
	FindsById,
	FindsByClassName,
	FindsByLinkText,
	FindsByName,
	FindsByCssSelector,
	FindsByTagName,
	FindsByXPath,
	HasInputDevices,
	HasCapabilities,
	TakesScreenshot,
	WebDriver {

	private final JavascriptExecutor javascriptExecutor;
	private final FindsById findsById;
	private final FindsByClassName findsByClassName;
	private final FindsByLinkText findsByLinkText;
	private final FindsByName findsByName;
	private final FindsByCssSelector findsByCssSelector;
	private final FindsByTagName findsByTagName;
	private final FindsByXPath findsByXPath;
	private final HasInputDevices hasInputDevices;
	private final HasCapabilities hasCapabilities;
	private final TakesScreenshot takesScreenshot;
	private final WebDriver webDriver;

	public RichWebDriver(RemoteWebDriver wd) {
		this.javascriptExecutor = wd;
		this.findsById = wd;
		this.findsByClassName = wd;
		this.findsByLinkText = wd;
		this.findsByName = wd;
		this.findsByCssSelector = wd;
		this.findsByTagName = wd;
		this.findsByXPath = wd;
		this.hasInputDevices = wd;
		this.hasCapabilities = wd;
		this.takesScreenshot = wd;
		this.webDriver = wd;
	}

	public RichWebDriver(HtmlUnitDriver wd) {
		this.javascriptExecutor = wd;
		this.findsById = wd;
		this.findsByClassName = wd;
		this.findsByLinkText = wd;
		this.findsByName = wd;
		this.findsByCssSelector = wd;
		this.findsByTagName = wd;
		this.findsByXPath = wd;
		this.hasInputDevices = wd;
		this.hasCapabilities = wd;
		this.takesScreenshot = null;
		this.webDriver = wd;
	}

	public Object executeAsyncScript(String arg0, Object... arg1) {
		return javascriptExecutor.executeAsyncScript(arg0, arg1);
	}

	public Object executeScript(String arg0, Object... arg1) {
		return javascriptExecutor.executeScript(arg0, arg1);
	}

	public WebElement findElementById(String arg0) {
		return findsById.findElementById(arg0);
	}

	public List<WebElement> findElementsById(String arg0) {
		return findsById.findElementsById(arg0);
	}

	public WebElement findElementByClassName(String arg0) {
		return findsByClassName.findElementByClassName(arg0);
	}

	public List<WebElement> findElementsByClassName(String arg0) {
		return findsByClassName.findElementsByClassName(arg0);
	}

	public WebElement findElementByLinkText(String arg0) {
		return findsByLinkText.findElementByLinkText(arg0);
	}

	public WebElement findElementByPartialLinkText(String arg0) {
		return findsByLinkText.findElementByPartialLinkText(arg0);
	}

	public List<WebElement> findElementsByLinkText(String arg0) {
		return findsByLinkText.findElementsByLinkText(arg0);
	}

	public List<WebElement> findElementsByPartialLinkText(String arg0) {
		return findsByLinkText.findElementsByPartialLinkText(arg0);
	}

	public WebElement findElementByName(String arg0) {
		return findsByName.findElementByName(arg0);
	}

	public List<WebElement> findElementsByName(String arg0) {
		return findsByName.findElementsByName(arg0);
	}

	public WebElement findElementByCssSelector(String arg0) {
		return findsByCssSelector.findElementByCssSelector(arg0);
	}

	public List<WebElement> findElementsByCssSelector(String arg0) {
		return findsByCssSelector.findElementsByCssSelector(arg0);
	}

	public WebElement findElementByTagName(String arg0) {
		return findsByTagName.findElementByTagName(arg0);
	}

	public List<WebElement> findElementsByTagName(String arg0) {
		return findsByTagName.findElementsByTagName(arg0);
	}

	public WebElement findElementByXPath(String arg0) {
		return findsByXPath.findElementByXPath(arg0);
	}

	public List<WebElement> findElementsByXPath(String arg0) {
		return findsByXPath.findElementsByXPath(arg0);
	}

	public Keyboard getKeyboard() {
		return hasInputDevices.getKeyboard();
	}

	public Mouse getMouse() {
		return hasInputDevices.getMouse();
	}

	public Capabilities getCapabilities() {
		return hasCapabilities.getCapabilities();
	}

	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		return takesScreenshot.getScreenshotAs(arg0);
	}

	public void close() {
		webDriver.close();
	}

	public WebElement findElement(By arg0) {
		return webDriver.findElement(arg0);
	}

	public List<WebElement> findElements(By arg0) {
		return webDriver.findElements(arg0);
	}

	public void get(String arg0) {
		webDriver.get(arg0);
	}

	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	public String getPageSource() {
		return webDriver.getPageSource();
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public String getWindowHandle() {
		return webDriver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return webDriver.getWindowHandles();
	}

	public Options manage() {
		return webDriver.manage();
	}

	public Navigation navigate() {
		return webDriver.navigate();
	}

	public void quit() {
		webDriver.quit();
	}

	public TargetLocator switchTo() {
		return webDriver.switchTo();
	}
	
}
