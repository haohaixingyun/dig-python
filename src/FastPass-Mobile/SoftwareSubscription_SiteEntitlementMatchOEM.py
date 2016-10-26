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
        
    def test_Case_SoftwareSubscription_SiteEntitlementMatchOEM(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Agreement number' field with '7239211'. and click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7239211")
	time.sleep(1)
	driver.find_element_by_id("oemonly").click()

	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p3')
        time.sleep(3)

	print "\n"
	print "step4.Select dropdown option 'All entitlements' and hit Go button. "
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
        sel = driver.find_element_by_xpath("(//select[@id='dropList1'])[1]")
        Select(sel).select_by_value('./EntmtSbscrptnProd?openagent&agree_num=0000125980&program=OE&ibm_cust_num=002574&cust_type=NAV&cust_num=0007239211')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Product summary' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p4')
        time.sleep(3)

	print "\n"
	print "step5.Click the value view product by part ."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)	
	driver.find_element_by_xpath("(//a[contains(text(),'view product by part')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part detail for product' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p5')
        time.sleep(3)


	print "\n"
	print "step6.Click the value View all entitlements  ."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)	
	driver.find_element_by_xpath("(//a[contains(text(),'View all entitlements')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All entitlements for site and part' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p6')
        time.sleep(3)

	print "\n"
	print "step7.Click the value of 'Sales transaction' in the 'Entitlement source' column."
	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(1)	
	driver.find_element_by_xpath("(//a[contains(text(),'Sales transactions')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales transactions for site and part' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SoftwareSubscription_SiteEntitlementMatchOEM_p7')
        time.sleep(3)
		
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_SoftwareSubscription_SiteEntitlementMatchOEM"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_SoftwareSubscription_SiteEntitlementMatchOEM.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is SoftwareSubscription_SiteEntitlementMatchOEM test case')
    runner.run(testunit)




        

