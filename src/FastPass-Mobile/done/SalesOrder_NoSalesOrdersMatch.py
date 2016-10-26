# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
from selenium.webdriver.common.keys import Keys
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
        
    def test_Case_SalesOrder_NoSalesOrdersMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_NoSalesOrdersMatch_p1')
	time.sleep(3)
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_NoSalesOrdersMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'SAP sales order number' field with '30123456' and click 'Search'"
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("30123456")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_NoSalesOrdersMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.Go back"
        driver.find_element_by_xpath("(//a[contains(text(),'Sales orders')])[2]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step5.Input 'Special bid number' field with 'BAD-VALUE' and click 'Search'."
	driver.find_element_by_id("nim_specl_bid_offerg_num").clear()
	driver.find_element_by_id("nim_specl_bid_offerg_num").send_keys("BAD-VALUE")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information - Search on special bid number' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source,"The page did not be opened correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_NoSalesOrdersMatch_p4')
        time.sleep(3)

	print "\n"
	print "step6.Go back"
        driver.find_element_by_xpath("(//a[contains(text(),'Sales orders')])[2]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step7.Input 'Opportunity number' field with 'BAD-VALUE' and click 'Search'."
#	driver.execute_script("window.scrollBy(0,200)","")
#	time.sleep(1)		
	driver.find_element_by_id("opprtnty_num").clear()
	driver.find_element_by_id("opprtnty_num").send_keys("BAD-VALUE")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information - Search on opportunity number' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source,"The page did not be opened correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_NoSalesOrdersMatch_p5')
        time.sleep(3)

	print "\n"
	print "step8.Go back"
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
    testunit.addTest(FastPass_Mobile("test_Case_SalesOrder_NoSalesOrdersMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_SalesOrder_NoSalesOrdersMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is SalesOrder_NoSalesOrdersMatch test case')
    runner.run(testunit)




        

