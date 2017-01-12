from com.example.selenium.spree.spree_page import SpreePage, url_to_be


class HomePage(SpreePage):
    def __init__(self, test):
        super().__init__(test)

        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.webDriver, 30).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/")
        )

    def validateTitle(self):
        title = self.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/", url)
