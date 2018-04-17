from BasePage import BasePage
from LoginPage import LoginPage


class HomePage(BasePage):
    def __init__(self, test, navigate_to):
        super(HomePage, self).__init__(test=test,
                                       url="https://www.cisco.com/",
                                       title="Cisco - Global Home Page",
                                       navigate_to=navigate_to)

    def navigate_to_login_page(self):
        login_button = self.test.web_driver.find_element_by_xpath("//*[@id=\"actions\"]/li[1]/a")
        self.test.assertTrue(login_button.is_displayed())
        self.test.assertTrue(login_button.is_enabled())
        login_button.click()

        login_page = LoginPage(self.test, navigate_to=False)
        login_page.assert_on_page()

        return login_page
