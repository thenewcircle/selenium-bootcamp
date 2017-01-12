from selenium.webdriver.common.by import By

from com.example.selenium.spree.spree_page import SpreePage, url_contains, url_to_be


class ProductPage(SpreePage):
    
    def __init__(self, test, product:str):
        super().__init__(test)

        product = product.replace(" ", "-")
        product = product.lower()

        from selenium.webdriver.support.wait import WebDriverWait
        WebDriverWait(self.test.webDriver, 30).until(
            url_to_be("https://spreecommerce-demo.herokuapp.com/products/" + product)
        )

    def validateTitle(self):
        title = self.webDriver.title
        self.test.assertEquals("Spree Tote - Spree Demo Site", title)

    def validateUrl(self):
        url = self.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/products/spree-tote", url)

    def validateImageSrc(self, expected_url):
        img = self.webDriver.find_element_by_css_selector(".panel-body.text-center>img")
        actual_url = img.get_attribute("src")
        
        msg = "Found " + actual_url
        self.test.assertTrue(expected_url in actual_url, msg)
        
    def clickThumbnail(self, index):
        thumb = self.webDriver.find_element_by_xpath(".//*[@id='product-thumbnails']/li[" + str(index+1) + "]/a/img")
        thumb.click()
        
    def setQuantity(self, count):
        quantity_tf = self.webDriver.find_element_by_name("quantity")
        quantity_tf.clear()
        quantity_tf.send_keys( str(count) )
        
    def clickAddToCart(self):
        addBtn = self.webDriver.find_element_by_tag_name("button")
        addBtn.click()
        
    def confirmCartResponse(self, expected):

        by = (By.CLASS_NAME, 'alert-success')
        
        from selenium.webdriver.support.wait import WebDriverWait
        from selenium.webdriver.support.expected_conditions import text_to_be_present_in_element
        WebDriverWait(self.test.webDriver, 5).until(
            text_to_be_present_in_element(by, expected)
        )

        # response = self.webDriver.find_element_by_class_name("alert-success")
        # self.test.assertEquals(expected, response.text)
