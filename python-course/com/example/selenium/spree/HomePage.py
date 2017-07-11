from com.example.selenium.spree.SpreePage import SpreePage, BASE_URL


class HomePage(SpreePage):
    def __init__(self, shoppingSpreeTests):
        SpreePage.__init__(self, shoppingSpreeTests)

    def validateTitle(self):
        self._validateTitle("Spree Demo Site")

    def validateUrl(self):
        self._validateUrl(BASE_URL)
