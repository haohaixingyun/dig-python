# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
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
        
    def test_Case_SalesOrder_Dsw13_5(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_Dsw13_5_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_Dsw13_5_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'sap_sales_ord_num' field with '0040761375'."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0040761375")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(10)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'SW Value Plus product group:' in driver.page_source ,"The data is not avalible"
        assert 'Business partner incentives:' in driver.page_source ,"The data is not avalible"
        assert 'End user site number:' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_Dsw13_5_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click the link of All sales order  "
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(2)
        driver.find_element_by_link_text("All sales orders").click()
	driver.implicitly_wait(20)
	time.sleep(5)
	result = driver.title
	print result
	time.sleep(3)
	assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_Dsw13_5_p4')
        time.sleep(3)


	print "\n"
	print "step5.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information',"The page did not be opened correct"
        time.sleep(3)        

	print "\n"
	print "step6.Click the link of Entitlements"
	driver.find_element_by_link_text("Entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"

        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_Dsw13_5_p5')
        time.sleep(3)

	print "\n"
	print "step7.Click the link of 7954318   "
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(2)
        driver.find_element_by_xpath("(//a[contains(text(),'7954318')])[1]").click()
	driver.implicitly_wait(20)
	time.sleep(5)
	result = driver.title
	print result
	time.sleep(3)
	assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_Dsw13_5_p6')
        time.sleep(3)


	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_SalesOrder_Dsw13_5"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_SalesOrder_Dsw13_5.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is SalesOrder_Dsw13_5 test case')
    runner.run(testunit)




        

