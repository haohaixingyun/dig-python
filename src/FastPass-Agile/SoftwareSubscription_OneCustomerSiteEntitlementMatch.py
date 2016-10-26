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


class FastPass_Agile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        self.base_url = "https://fpagile.boulder.ibm.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
        
    def test_Case_SoftwareSubscription_OneCustomerSiteEntitlementMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p1')
	time.sleep(3)
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Customer name' field with 'nntp' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("cust_name").clear()
	driver.find_element_by_id("cust_name").send_keys("Kunta AVP")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step5.Input 'IBM Customer number' field with '0956850' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("ibmcustomernum").clear()
	driver.find_element_by_id("ibmcustomernum").send_keys("0956850")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p4')
        time.sleep(3)

	print "\n"
	print "step6.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step7.Input 'Postal code' field with '78008' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("postal_code").clear()
	driver.find_element_by_id("postal_code").send_keys("78008")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p5')
        time.sleep(3)

	print "\n"
	print "step8.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step9.Input 'Agreement number' field with '55556' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("55556")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p6')
        time.sleep(3)

	print "\n"
	print "step10.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step11.Input 'Agreement number' field with '11531' and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("11531")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p7')
        time.sleep(3)

	print "\n"
	print "step12.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
	time.sleep(3)

	

	print "\n"
	print "step13.Input 'Site number' field with '7131453' value and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7131453")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Passport Advantage' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_OneCustomerSiteEntitlementMatch_p8')
        time.sleep(3)

	print "\n"
	print "step14.Go back"
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
    testunit.addTest(FastPass_Agile("test_Case_SoftwareSubscription_OneCustomerSiteEntitlementMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_SoftwareSubscription_OneCustomerSiteEntitlementMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is SoftwareSubscription_OneCustomerSiteEntitlementMatch test case')
    runner.run(testunit)




        

