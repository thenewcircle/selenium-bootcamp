from selenium.webdriver.remote.webelement import WebElement


class SpreePage:

    def __init__(self, test):
        self.test = test
        self.webDriver = test.webDriver

    def getDepartmentCmb(self) -> WebElement:
        return self.webDriver.find_element_by_id("taxon")
