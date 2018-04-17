class BasePage(object):
    def __init__(self, test, url, title, navigate_to):
        self.test = test
        self.url = url
        self.title = title
        if navigate_to:
            self.navigate()

    def navigate(self):
        self.test.web_driver.get(self.url)
        self.assert_on_page()

    def assert_on_page(self):
        self.test.assertEquals(self.test.web_driver.title, self.title)
