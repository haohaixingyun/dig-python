
#coding = utf - 8

from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
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
    
        

    def Test_Case1(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.4_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.4_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	
	print "\n"
	print "step3. Input 'customer number' field with 'tom' and click 'Search'."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fastpass.nsf/customers?openform")
	driver.implicitly_wait(10)
	driver.find_element_by_id("person_name").clear()
	driver.find_element_by_id("person_name").send_keys("tom")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result1 = driver.title
        print result1
        assert result1 == 'FastPass | Customers - Persons information' ,"Page opened is not correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.4_p3')
        time.sleep(5)
	
        print "\n"
	print "step4. hit back"
        driver.back()
        driver.implicitly_wait(10)

        print "\n"
	print "step5. Input 'customer number' field with '1234567' and click 'Search'."
	driver.implicitly_wait(10)
	driver.find_element_by_id("person_name").clear()
	driver.find_element_by_id("person_name").send_keys("1234567")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result2 = driver.title
        print result2
        assert result2 == 'FastPass | Customers - Persons information' ,"Page opened is not correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.4_p4')
        time.sleep(5)
	
        
        print "Test Case end with successfully!"
                            
	
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("Test_Case1"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease15.4.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease15.4 test case')
    runner.run(testunit)




        

