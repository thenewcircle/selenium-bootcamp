from com.example.selenium.spree.SpreePage import SpreePage, BASE_URL


class HomePage(SpreePage):
    def __init__(self, test):
        super().__init__(test)

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals(BASE_URL, url)
