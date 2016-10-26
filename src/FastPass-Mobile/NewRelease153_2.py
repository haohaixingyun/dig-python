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
    
        

    def Test_Case1(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease15.3_2_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease15.3_2_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3. Input 'customer number' field with 'FIJIKEN' and click 'Search."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fastpass.nsf/customers?openform")
	driver.implicitly_wait(10)
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("FIJIKEN")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        print result
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease15.3_2_p3')

        print "\n"
	print "step4.Click English Button"
	driver.implicitly_wait(10)
	driver.find_element_by_link_text("Toggle English/international characters").click()
        time.sleep(5)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease15.3_2_p4')
        time.sleep(5)
        element = driver.find_element_by_xpath("//input[@value='English']")
        ActionChains(driver).click(element).perform()
        
        time.sleep(5)
        
        assert 'FIJIKEN Co.,Ltd' in driver.page_source ,"The English Button is unavilable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease15.3_2_p5')

        print "\n"
	print "step5.Click International Button"
	driver.implicitly_wait(10)
	driver.find_element_by_link_text("Toggle English/international characters").click()
        time.sleep(5)
        element = driver.find_element_by_xpath("//input[@value='International']")
        time.sleep(5)
        ActionChains(driver).click(element).perform()
        
        time.sleep(5)
        
#       assert  in driver.page_source ,"The International Button is unavilable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease15.3_2_p6')
        
        
	print "\n"        
        print "Test Case end with successfully!"
                            
	
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("Test_Case1"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRelease15.3_2.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRelease15.3_2 test case')
    runner.run(testunit)




        

