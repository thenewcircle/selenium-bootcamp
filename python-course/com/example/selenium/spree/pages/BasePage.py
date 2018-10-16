class BasePage:
    def __init__(self, test, title, url):
        self.test = test
        self.title = title
        self.url = url

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEqual(self.title, title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEqual(self.url, url)
