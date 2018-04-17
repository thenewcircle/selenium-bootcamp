from BasePage import BasePage
from NewsFeedPage import NewsFeedPage


class LoginPage(BasePage):
    def __init__(self, test, navigate_to):
        super(LoginPage, self).__init__(test=test,
                                        url="https://cloudsso.cisco.com/sp/startSSO.ping?SpSessionAuthnAdapterId=standardnomfa&TargetResource=https://sso.cisco.com/autho/login/loginaction.html",
                                        title="Cisco.com Login Page",
                                        navigate_to=navigate_to)

    def assert_on_page(self):
        inner_title = self.test.web_driver.find_element_by_xpath("//*[@id=\"fw-title-nav-login\"]/h1")
        self.test.assertEquals(inner_title.text, "Log in to your account")

    def get_login_button(self):
        return self.test.web_driver.find_element_by_id("login-button")

    def get_user_input(self):
        return self.test.web_driver.find_element_by_xpath("//*[@id=\"userInput\"]")

    def get_password_input(self):
        return self.test.web_driver.find_element_by_id("passwordInput")

    def sign_in(self, username, password):
        if not self.login(username, password):
            return None

        return NewsFeedPage(self.test, False)

    def login(self, username, password):
        self.get_user_input().send_keys(username)
        self.get_login_button().click()

        password_input = self.get_password_input()
        self.test.assertTrue(password_input.is_displayed())
        self.test.assertTrue(password_input.is_enabled())
        password_input.send_keys(password)

        self.get_login_button().click()
        msg = self.test.web_driver.find_element_by_css_selector("#warning-msg > h3")
        return not msg.text == "That login didn't work:"
