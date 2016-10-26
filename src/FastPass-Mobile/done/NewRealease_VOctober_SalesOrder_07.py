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
        
    def test_Case_NewRealease_VOctober_SalesOrder_07(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	time.sleep(3)

	print "\n"
	print "step3.Input 'site num' field with '0007052069' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(2)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("0007052069")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        assert '7052069' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click link of 7052069 ."
	driver.execute_script("window.scrollBy(0,800)","")
	time.sleep(3)
	driver.find_element_by_link_text("7052069").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'Customer name #2:' in driver.page_source ,"The data is not avalible"
        assert 'Customer name #3:' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p4')
        time.sleep(3)
	

	print "\n"
	print "step5.click link of Current view"
	driver.execute_script("window.scrollBy(0,1200)","")
	time.sleep(3)
	driver.find_element_by_link_text("Current view").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p5')
	time.sleep(3)

	print "\n"
	print "step6.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)	

	print "\n"
	print "step7.click link of allView ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(3)
	driver.find_element_by_link_text("All view").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p6')
        time.sleep(3)

	print "\n"
	print "step8.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step9.click link of View ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[3]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p7')
        time.sleep(3)

	print "\n"
	print "step10.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step11.click link of View ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[4]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Global brand family history' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p8')
        time.sleep(3)

	print "\n"
	print "step12.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step13.click link of View ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_SalesOrder_07_p9')
        time.sleep(3)        

	print "\n"
	print "step14.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRealease_VOctober_SalesOrder_07"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRealease_VOctober_SalesOrder_07.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRealease_VOctober_SalesOrder_07 test case')
    runner.run(testunit)




        

