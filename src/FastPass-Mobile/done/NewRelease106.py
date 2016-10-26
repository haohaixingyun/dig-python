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
        
    def test_Case_NewRelease106(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Agreement number' field with '95113'.Input 'Site number' field with '7011428'.Click 'Search'."
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("95113")
	time.sleep(1)
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7011428")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p3')
        time.sleep(3)

	print "\n"
	print "step4.Select dropdown option 'Substitution list' and hit Go button. "
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
        sel = driver.find_element_by_xpath("(//select[@id='dropList1'])[1]")
        Select(sel).select_by_value('/FastPassS2/page/EntitlemtSubActvForSAPID?cust_num=0007011428&sap_ctrct_num=0000095113&cust_type=NAV&program=PA')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active substitution list for site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p4')
        time.sleep(3)

	print "\n"
	print "step5.click Historicalub stitution list."
#	driver.execute_script("window.scrollBy(0,200)","")
#	time.sleep(1)	
	driver.find_element_by_xpath("(//a[contains(text(),'Historical substitution list')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Historical substitution list for site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p5')
        time.sleep(3)

	print "\n"
	print "step6.Go back."	
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active substitution list for site' ,"The page did not be opened correct"
        time.sleep(3)

	print "\n"
	print "step7.click Customer name link."
#	driver.execute_script("window.scrollBy(0,300)","")
#	time.sleep(1)	
	driver.find_element_by_link_text("Abbott Laboratories").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p6')
        time.sleep(3)

	print "\n"
	print "step8.Go back."	
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active substitution list for site' ,"The page did not be opened correct"
        time.sleep(3)

	print "\n"
	print "step9.click Customer name link."
#	driver.execute_script("window.scrollBy(0,300)","")
#	time.sleep(1)	
	driver.find_element_by_xpath("(//a[contains(text(),'95113')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p7')
        time.sleep(3)

	print "\n"
	print "step10.Go back."	
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active substitution list for site' ,"The page did not be opened correct"
        time.sleep(3)

	print "\n"
	print "step11.click Site number link."
#	driver.execute_script("window.scrollBy(0,300)","")
#	time.sleep(1)	
	driver.find_element_by_xpath("(//a[contains(text(),'7011428')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease106_p8')
        time.sleep(3)

        
		
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRelease106"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRelease106.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRelease106 test case')
    runner.run(testunit)




        

