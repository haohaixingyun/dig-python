# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
import unittest
import time,sys
import login,C_screenshots
import HTMLTestRunner


class FastPass_Agile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        self.base_url = "https://fpagile.boulder.ibm.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
        
    def test_Case_NewRealease_VOctober_OrderReason(self):
		
	print "Test case start:"
	print "\n"
	print "step1. open the home page"
	driver = self.driver
	wait = self.wait
	driver.get(self.base_url + "software/xl/fastpass/agile/fphome.nsf/default?openform")
	driver.maximize_window()
	now_url = driver.current_url
	print now_url
	assert now_url == 'https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphome.nsf/default?openform' ,"URL is not correct."
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRealease_VOctober_OrderReason_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRealease_VOctober_OrderReason_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)


	print "\n"
	print "step3.Input 'SAP sales order number' field with '0052900555' and click 'Search'.."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0052900555")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	time.sleep(1)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'DEBIT' in driver.page_source ,"the data is not available"
        assert 'Order type:' in driver.page_source ,"the data is not available"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRealease_VOctober_OrderReason_p3')
        time.sleep(3)


	print "\n"
	print "step4.Click the 'Sales orders' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Sales orders')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRealease_VOctober_OrderReason_p4')
        time.sleep(3)


	print "\n"
	print "step5.Input 'SAP sales order number' field with '0053218611' and click 'Search'.."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0053218611")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	time.sleep(1)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'STANDARD' in driver.page_source ,"the data is not available"
        assert 'Order type:' in driver.page_source ,"the data is not available"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRealease_VOctober_OrderReason_p5')
        time.sleep(3)


        
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_NewRealease_VOctober_OrderReason"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRealease_VOctober_OrderReason.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRealease_VOctober_OrderReason test case')
    runner.run(testunit)




        

