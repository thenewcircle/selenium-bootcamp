class BasePage(object):
    def __init__(self, test, url, title, navigate_to):
        self.test = test
        self.url = url
        self.title = title
        if navigate_to:
            self.navigate()

    def url_to_be(self, web_driver):
        return self.url == web_driver.current_url

    def navigate(self):
        self.test.web_driver.get(self.url)
        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.web_driver, 30).until(self.url_to_be)

    def assert_on_page(self):
        self.test.assertEquals(self.test.web_driver.title, self.title)
