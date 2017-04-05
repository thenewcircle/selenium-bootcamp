from com.example.selenium.spree.spree_page import SpreePage, url_to_be


class HomePage(SpreePage):

    def __init__(self, test):
        # self.test = test
        super().__init__(test, url_to_be("https://selenium.jacobparr.com/"))

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://selenium.jacobparr.com/", url)