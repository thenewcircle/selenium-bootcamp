from com.example.selenium.spree.spree_page import SpreePage, url_contains


class ProdPage(SpreePage):

    def __init__(self, test, what):
        what = what.lower().replace(" ", "-")
        super().__init__(test, url_contains("https://selenium.jacobparr.com/products/" + what))

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Tote - Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://selenium.jacobparr.com/products/spree-tote", url)

    def validateImageSrc(self, expected):
        img = self.test.webDriver.find_element_by_css_selector(".panel-body.text-center>img")
        actual = img.get_attribute("src")
        
        msg = "Found " + actual
        self.test.assertTrue(actual.startswith(expected), msg)
        
    def clickThumbnail(self, index):
        index = index + 1
        what = ".//*[@id='product-thumbnails']/li["+str(index)+"]/a/img"
        thmb = self.test.webDriver.find_element_by_xpath(what)
        thmb.click()
        
    def setQuantity(self, count):
        quantityTF = self.test.webDriver.find_element_by_name("quantity")
        quantityTF.clear()
        quantityTF.send_keys(str(count))
        
    def clickAddToCart(self):
        addBtn = self.test.webDriver.find_element_by_tag_name("button")
        addBtn.click()
        
        # from selenium.webdriver.support.wait import WebDriverWait
        # wdw = WebDriverWait(self.test.webDriver, 3)
        # wdw.until(???)
        
    def confirmCartResponse(self, message):
        div = self.test.webDriver.find_element_by_class_name("alert-success")
        self.test.assertEquals(message, div.text)
        