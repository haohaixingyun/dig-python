# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
import unittest
import time,sys
import login,C_screenshots
import HTMLTestRunner


class FastPass_Mobile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        self.base_url = "http://sbybz2239.sby.ibm.com:19080/FastPassS2/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
        
    def test_Case_Customer_INAC(self):
		
	print "Test case start:"
	print "\n"
	print "step1. open the home page"
	driver = self.driver
	wait = self.wait
	driver.get(self.base_url + "fastpass.html")
	driver.maximize_window()
	now_url = driver.current_url
	print now_url
	assert now_url == 'http://sbybz2239.sby.ibm.com:19080/FastPassS2/fastpass.html' ,"URL is not correct."
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_INAC_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_INAC_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Customer number' field with '9593023'.Click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("9593023")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'International account number' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_INAC_p3')
        time.sleep(3)

	print "\n"
	print "step4.click enterprise number"
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(3)
	driver.find_element_by_xpath("//a[contains(text(),'9593023')]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Enterprise number details' ,"The page did not be opened correct"

        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_INAC_p4')
	time.sleep(3)


	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_Customer_INAC"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_Customer_INAC.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is Customer_INAC test case')
    runner.run(testunit)




        

