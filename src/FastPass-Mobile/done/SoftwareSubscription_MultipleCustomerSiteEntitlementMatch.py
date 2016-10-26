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
        
    def test_Case_SoftwareSubscription_MultipleCustomerSiteEntitlementMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_MultipleCustomerSiteEntitlementMatch_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_MultipleCustomerSiteEntitlementMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'CUST NAME' field with 'ING' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_id("cust_name").clear()
	driver.find_element_by_id("cust_name").send_keys("ING")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_MultipleCustomerSiteEntitlementMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.hit back"
	driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step5.Input 'CUST NAME' field with 'ING Insurance' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_id("cust_name").clear()
	driver.find_element_by_id("cust_name").send_keys("ING Insurance")
	time.sleep(1)
	driver.find_element_by_id("match").click()
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source ,"The data is not avalible"
        assert 'Passport Advantage Express' in driver.page_source ,"The data is not avalible"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_MultipleCustomerSiteEntitlementMatch_p4')
        time.sleep(3)

	print "\n"
	print "step6.hit back"
	driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step7.Input 'CUST Num' field with '401976' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_id("ibmcustomernum").clear()
	driver.find_element_by_id("ibmcustomernum").send_keys("401976")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert '06644' in driver.page_source ,"The data is not avalible"
        assert '11531' in driver.page_source ,"The data is not avalible"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_MultipleCustomerSiteEntitlementMatch_p5')
        time.sleep(3)

	print "\n"
	print "step8.hit back"
	driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step9.Input 'CUST Num' field with '02169' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_id("postal_code").clear()
	driver.find_element_by_id("postal_code").send_keys("02169")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source ,"The data is not avalible"
        assert 'Passport Advantage Express' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_MultipleCustomerSiteEntitlementMatch_p6')
        time.sleep(3)

	print "\n"
	print "step10.hit back"
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
    testunit.addTest(FastPass_Mobile("test_Case_SoftwareSubscription_MultipleCustomerSiteEntitlementMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_SoftwareSubscription_MultipleCustomerSiteEntitlementMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is SoftwareSubscription_MultipleCustomerSiteEntitlementMatch test case')
    runner.run(testunit)




        

