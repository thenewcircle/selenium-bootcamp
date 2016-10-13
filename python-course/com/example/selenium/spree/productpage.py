from com.example.selenium.spree.spreepage import SpreePage


class ProductPage(SpreePage):
    
    def __init__(self, test, prodName):
        # self.test = test
        super().__init__(test)

        name = prodName.lower().replace(" ", "-").replace(".","-")

        from selenium.webdriver.support.wait import WebDriverWait
        from com.example.selenium.spree.spreepage import url_to_be
        WebDriverWait(self.test.webDriver, 5).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/products/" + name)
        )

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/products/spree-tote", url)
    
    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Tote - Spree Demo Site", title)
        
    def clickAddToCart(self):
        cartBtn = self.test.webDriver.find_element_by_tag_name("button")
        cartBtn.click()

        from selenium.webdriver.support.wait import WebDriverWait
        from selenium.webdriver.support import expected_conditions
        from selenium.webdriver.common.by import By
        WebDriverWait(self.test.webDriver, 5).until(
            expected_conditions.element_to_be_clickable((By.CSS_SELECTOR, "a.cart-info.full"))
        )
        
    def clickShoppingCart(self):
        cartLink = self.test.webDriver.find_element_by_partial_link_text("Cart: ")
        cartLink.click()
        
        from com.example.selenium.spree.cartpage import CartPage
        return CartPage(self.test)
        
    def validateCartLink(self, quantity, amount):
        cartLink = self.test.webDriver.find_element_by_partial_link_text("Cart: ")
        actual = cartLink.text
        
        if quantity == 0:
            self.test.assertEquals("Cart: (Empty)", actual)
        else:
            expected = "Cart: (" + str(quantity) + ") $" + amount
            self.test.assertEquals(expected, actual)
        
    def setQuantity(self, amount):
        quantityTF = self.test.webDriver.find_element_by_name("quantity")
        quantityTF.clear()
        quantityTF.send_keys("3")
        
    def validateImageSrc(self, expectedUrl):
                                                            # .panel-body.text-center>img
        image = self.test.webDriver.find_element_by_css_selector("#main-image > div > img")
        actualUrl = image.get_attribute("src")
        self.test.assertTrue(expectedUrl in actualUrl, "Found " + actualUrl)

    def clickThumbnail(self, i):
        i = i + 1
        path = ".//*[@id='product-thumbnails']/li[" + str(i) + "]/a/img"
        thmb = self.test.webDriver.find_element_by_xpath(path)
        thmb.click()