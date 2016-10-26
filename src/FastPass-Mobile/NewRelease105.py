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
        
    def test_Case_NewRelease105(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'site number' field with '7766058' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("822277")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7766058")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p3')
        time.sleep(3)

	print "\n"
	print "step4.click Services agreements view link."
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'View')])[3]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p4')
	time.sleep(3)	

	print "\n"
	print "step5.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step6.click Sales orders view"
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'All view')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p5')
	time.sleep(3)	



	print "\n"
	print "step7.click Sales orders  link "
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'0051539838')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p5')
	time.sleep(3)	


	print "\n"
	print "step8.click Sales orders num link "
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Sales orders')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p5')
	time.sleep(3)	


	print "\n"
	print "step9.click Sales orders num link "
	time.sleep(1)
        driver.find_element_by_id("sap_sales_ord_num").send_keys("0077081653")
	time.sleep(3)
        driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        assert 'Original line item' in driver.page_source ,"The page did not be opened correct"
        assert 'Co-term line item' in driver.page_source ,"The page did not be opened correct"
        assert 'Additional purchase order' in driver.page_source ,"The page did not be opened correct"
        assert 'Replaced' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p5')
	time.sleep(3)


	print "\n"
	print "step10.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step11.click Sales orders num link "
	time.sleep(1)
        driver.find_element_by_id("sap_sales_ord_num").send_keys("0077082063")       
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        assert 'Coverage term' in driver.page_source ,"The page did not be opened correct"
        assert '12 / 36' in driver.page_source ,"The page did not be opened correct"
        assert 'Ramp-up indicator' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p5')
	time.sleep(3)


	print "\n"
	print "step12.Click the 'Reseller:' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p9')        
	time.sleep(3)	

	
	print "\n"
	print "step13.Input 'site number' field with '7766058' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("IBM")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease105_p3')
        time.sleep(3)
	
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRelease105"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRelease105.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRelease105 test case')
    runner.run(testunit)




        

