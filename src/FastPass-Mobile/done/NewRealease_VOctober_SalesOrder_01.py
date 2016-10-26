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
        
    def test_Case_NewRealease_VOctober_SalesOrder_01(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_01_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_01_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'SAP sales order number' field with '0055561375' and click 'Search'."###salesOrderNumWithInstallation
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0055561375")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'IBM machine type / model / serial number:' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_01_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click link of view detailes ."###salesOrderNumWithInstallation
	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(3)
	driver.find_element_by_xpath(".//*[@id='fptable']/tbody/tr/td[1]").click()
	time.sleep(1)
	driver.find_element_by_link_text("View details").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Appliance information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_01_p4')
        time.sleep(3)
	

	print "\n"
	print "step5.hit back"
	driver.back()
	time.sleep(3)
	driver.back()
	time.sleep(3)	
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step6.Input 'SAP sales order number' field with '0055559591' and click 'Search'."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0055559591")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'IBM machine type / model / serial number:' in driver.page_source ,"The data is not avalible"
        assert 'Not applicable' in driver.page_source ,"The data is not avalible"
        assert 'Revenue stream' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_01_p5')
        time.sleep(3)

	print "\n"
	print "step7.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
	time.sleep(3)


	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRealease_VOctober_SalesOrder_01"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRealease_VOctober_SalesOrder_01.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRealease_VOctober_SalesOrder_01 test case')
    runner.run(testunit)




        

