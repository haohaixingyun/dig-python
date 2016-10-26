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
        
    def test_Case_NewRelease102(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p1')
	time.sleep(3)
	
	    
	###capture screenshots

	
	print "\n"
	print "step2.Click help link"
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/help_home.html")
	time.sleep(1)
        assert "What's new" in driver.page_source ,"The page did not be opened correct"	
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p2')
	time.sleep(3)

	print "\n"
	print "step3.whatsNewLeft_link"
	driver.find_element_by_link_text("What's new").click()
	time.sleep(1)
        assert 'New Features' in driver.page_source ,"The page did not be opened correct"	
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p3')
	time.sleep(3)

	print "\n"
	print "step4.whatsNewLeft_link"
	driver.find_element_by_xpath("(//a[contains(text(),'April 2016 release')])[1]").click()
	time.sleep(1)
        assert 'The April 2016 release of FastPass includes the following key enhancements and modifications:' in driver.page_source ,"The page did not be opened correct"	
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p4')
	time.sleep(3)                                     
	
	
	print "\n"
	print "step5.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p5')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	time.sleep(3)


	print "\n"
	print "step6.Input 'site number' field with '7887612' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("118092")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7887612")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'No active PAE transactions' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p6')
        time.sleep(3)
        
	print "\n"
	print "step7.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"	
	time.sleep(3)
        


	print "\n"
	print "step8.Input 'site number' field with '7887612' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("118092")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7887612")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'No active PAE transactions' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p7')
        time.sleep(3)
        
	print "\n"
	print "step9.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"	
	time.sleep(3)


	
	print "\n"
	print "step10.Input 'site number' field with '3006912' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("620980")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("3006912")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'Active VSP customer'in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p8')
        time.sleep(3)
        
	print "\n"
	print "step11.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"	
	time.sleep(3)
	
	print "\n"
	print "step12.Input 'site number' field with '7616445' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("8407288")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7616445")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'Active OEM Customer'in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p9')
        time.sleep(3)
        
	print "\n"
	print "step13.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"	
	time.sleep(3)


	print "\n"
	print "step14.Input 'site number' field with '7116355' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("6520564")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7116355")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'Acquisition Customer No IBM DSW Sales'in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease102_p10')
        time.sleep(3)
        

		
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRelease102"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRelease102.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRelease102 test case')
    runner.run(testunit)




        

