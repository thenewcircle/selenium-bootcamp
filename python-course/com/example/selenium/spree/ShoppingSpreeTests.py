import unittest


class ShoppingSpreeTests(unittest.TestCase):


    def setUp(self):
        print("Before method")

    def testA(self):
        print("Test A")
        self.assertTrue(1 == 1)

    def testB(self):
        print("Test B")
        self.assertEquals(1, 1)

    def tearDown(self):
        print("After method\n")
