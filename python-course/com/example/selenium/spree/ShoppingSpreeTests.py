import unittest


class ShoppingSpreeTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        print("Before class")

    def setUp(self):
        print("Before method")

    def testA(self):
        print("Test A")
        self.assertEquals(1, 2)

    def testB(self):
        print("Test B")
        self.assertEquals(1, 1)

    def tearDown(self):
        print("After method\n")

    @classmethod
    def tearDownClass(cls):
        print("After class")
