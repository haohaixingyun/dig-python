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
        
    def test_Case_NewRelease101(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	time.sleep(3)

	print "\n"
	print "step3.Input 'invoice number' field with  DSW Invoice Number '115270396' and click 'Search'."
	driver.find_element_by_id("invoice_num").clear()
	driver.find_element_by_id("invoice_num").send_keys("115270396")
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information - Search on invoice number' ,"The page did not be opened correct"
        ele = driver.find_element_by_xpath("//a[contains(text(),'0053127775')]").is_displayed()
        print ele
        assert ele == True ,"The data is unavailable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click the link in the 'SAP sales order' column."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(3)
	driver.find_element_by_xpath("//a[contains(text(),'0053127775')]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p4')
        time.sleep(3)
	

	print "\n"
	print "step5.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	driver.back()
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"

	print "\n"
	print "step6.Input 'invoice number' field with  '170779986' and click 'Search'."
	driver.find_element_by_id("invoice_num").clear()
	driver.find_element_by_id("invoice_num").send_keys("170779986")
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information - Search on invoice number' ,"The page did not be opened correct"
        ele = driver.find_element_by_xpath("//a[contains(text(),'0053128386')]").is_displayed()
        print ele
        assert ele == True ,"The data is unavailable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p5')
        time.sleep(3)

	print "\n"
	print "step7.Click the link in the 'SAP sales order' column."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(3)
	driver.find_element_by_xpath("//a[contains(text(),'0053128386')]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert '170779986' in driver.page_source ,"The data is unavaliable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p6')
        time.sleep(3)
	

	print "\n"
	print "step8.Click the 'Software entitlements' option from the navigation panel."
        driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[2]").click()
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
		
	print "\n"
	print "step9.Input 'Site number' field with '7516475'.Click 'Search'.(Have all program types checked.) ."
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7516475")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()	
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p7')
        time.sleep(3)

	print "\n"
	print "step10.Select dropdown option 'All evolutions' and hit Go button. "
        sel = driver.find_element_by_xpath("//select[@id='dropList1']")
        Select(sel).select_by_value('/FastPassS2/page/EntitlemtEvoltnAllForSAPID?cust_num=0007516475&sap_ctrct_num=NAV&cust_type=NAV&program=PX')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All evolutions for site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease101_p8')
        time.sleep(3)

        
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRelease101"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRelease101.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRelease101 test case')
    runner.run(testunit)




        

