from com.example.selenium.spree.pages.BasePage import BasePage


class YahooPage(BasePage):
    title = "Not testing"
    url = "https://finance.yahoo.com/quote/AAPL/financials?p=AAPL"

    def __init__(self, test):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test

    def getFinanceTable(self):
        return self.test.webDriver.find_element_by_xpath("//*[@id='Col1-1-Financials-Proxy']/section/div[3]/table")
