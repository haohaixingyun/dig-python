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
        
    def test_Case_SoftwareSubscription_NoEntitlementCustomerSiteMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p1')
	time.sleep(3)
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Customer name' field with 'nntp' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("cust_name").clear()
	driver.find_element_by_id("cust_name").send_keys("nntp")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step5.Input 'IBM Customer number' field with '256756' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("ibmcustomernum").clear()
	driver.find_element_by_id("ibmcustomernum").send_keys("256756")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p4')
        time.sleep(3)

	print "\n"
	print "step6.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step7.Input 'Postal code' field with '97865' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("postal_code").clear()
	driver.find_element_by_id("postal_code").send_keys("97865")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p5')
        time.sleep(3)

	print "\n"
	print "step8.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step9.Input 'Agreement number' field with '446478' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("446478")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p6')
        time.sleep(3)

	print "\n"
	print "step10.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)	

	print "\n"
	print "step11.Input 'Site number' field with '7254326' value and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7254326")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_NoEntitlementCustomerSiteMatch_p7')
        time.sleep(3)

	print "\n"
	print "step12.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)	



	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_SoftwareSubscription_NoEntitlementCustomerSiteMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_SoftwareSubscription_NoEntitlementCustomerSiteMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is SoftwareSubscription_NoEntitlementCustomerSiteMatch test case')
    runner.run(testunit)




        

