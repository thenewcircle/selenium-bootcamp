class SpreePage:

    def __init__(self, test):
        self.test = test

    def getDepartmentCmb(self):
        return self.test.webDriver.find_element_by_id("taxon")

        