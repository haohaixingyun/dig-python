# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
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
        
    def test_Case_141_3(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'IBM site number' field with '7406770' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7406770")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click ICN hold orders link"
	driver.find_element_by_link_text("ICN hold orders").click()
	#element = driver.find_element_by_link_text("ICN hold orders")
	#ActionChains(driver).click(element).perform()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Orders on ICN hold - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p4')
	time.sleep(3)

	print "\n"
	print "step5.hit back twice"
	driver.back()
	time.sleep(3)
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p5')
        time.sleep(3)

	print "step6.Input 'IBM site number' field with '3427026' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("3427026")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p6')
        time.sleep(3)

	print "\n"
	print "step7.Click ICN hold orders link"
#	element = driver.find_element_by_link_text("ICN hold orders")
	driver.find_element_by_link_text("ICN hold orders").click()
	time.sleep(3)
	#ActionChains(driver).click(element).perform()
	#time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Sales orders - Orders on ICN hold - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p7')
	time.sleep(3)

	print "\n"
	print "step8.hit back twice"
	driver.back()
	time.sleep(3)
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p8')
        time.sleep(3)

	print "step9.Input 'IBM site number' field with '3345156' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("3345156")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p9')
        time.sleep(3)

	print "\n"
	print "step10.Click ICN hold orders link"
	driver.find_element_by_link_text("ICN hold orders").click()
	#element = driver.find_element_by_link_text("ICN hold orders")
	#ActionChains(driver).click(element).perform()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Orders on ICN hold - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_3_p10')
	time.sleep(3)

	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_141_3"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease141_3.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease141_3 test case')
    runner.run(testunit)




        

