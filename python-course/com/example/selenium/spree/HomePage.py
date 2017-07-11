from com.example.selenium.spree.SpreePage import SpreePage, BASE_URL


class HomePage(SpreePage):
    def __init__(self, shoppingSpreeTests):
        super().__init__(shoppingSpreeTests)

    def validateTitle(self):
        super()._validateTitle("Spree Demo Site")

    def validateUrl(self):
        super()._validateUrl(BASE_URL)
