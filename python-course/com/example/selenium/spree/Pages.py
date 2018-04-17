from LoginPage import LoginPage
from HomePage import HomePage


class Pages:
    def open_home_page(self, test):
        return HomePage(test=test, navigate_to=True)

    def open_login_page(self, test):
        return LoginPage(test=test, navigate_to=True)
