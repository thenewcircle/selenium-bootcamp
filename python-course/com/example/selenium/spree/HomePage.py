class HomePage:
    def __init__(self, test):
        self.test = test

    def validateTitle(self):
        title = self.test.webDriver.title
        self.test.assertEquals("Spree Demo Site", title)

    def validateUrl(self):
        url = self.test.webDriver.current_url
        self.test.assertEquals("https://spreecommerce-demo.herokuapp.com/", url)
