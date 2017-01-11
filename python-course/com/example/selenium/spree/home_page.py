from com.example.selenium.spree.spree_page import SpreePage


class HomePage(SpreePage):
    def __init__(self, test):
        super().__init__(test)

    def validateTitle(self):
        title = self.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/", url)
