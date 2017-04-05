from com.example.selenium.spree.spree_page import SpreePage, url_to_be


class ProductsPage(SpreePage):

    def __init__(self, test, what):
        waitUntil = url_to_be("https://selenium.jacobparr.com/search/" + what.lower())
        super().__init__(test, waitUntil)

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://selenium.jacobparr.com/search/bag", url)
        
    def clickProductLnk(self, what):
        link = self.test.webDriver.find_element_by_link_text(what)
        link.click()

        from com.example.selenium.spree.prod_page import ProdPage
        return ProdPage(self.test, what)
    