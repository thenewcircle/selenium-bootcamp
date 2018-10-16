from com.example.selenium.spree.pages.BasePage import BasePage


class ProductsPage(BasePage):
    title = "Spree Demo Site"
    url = "https://spreecommerce-demo.herokuapp.com/products?utf8=%E2%9C%93&taxon=&keywords="

    def __init__(self, test, keywords):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test
        self.keywords = keywords

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEqual(self.url + self.keywords, url)


