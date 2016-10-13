from com.example.selenium.spree.spreepage import SpreePage


class HomePage(SpreePage):
    
    def __init__(self, test):
        super().__init__(test)
        
        from selenium.webdriver.support.wait import WebDriverWait
        from com.example.selenium.spree.spreepage import url_to_be
        WebDriverWait(self.test.webDriver, 5).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/")
        )

        
    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)
    
    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/", url)