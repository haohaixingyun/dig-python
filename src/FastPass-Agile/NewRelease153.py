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
    
        

    def Test_Case153_1(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_1_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self)
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_1_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3. Input 'SAP sales order number' field with '0053766300' and click 'Search'."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fastpass.nsf/salesorders?openform")
	driver.implicitly_wait(10)
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0053766300")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_1_p3')
        
        
	print "\n"        
        print "Test Case end with successfully!"
                            
    def Test_Case153_2(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_2_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self)
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_2_p2')
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
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_2_p3')

        print "\n"
	print "step4.Click English Button"
	driver.implicitly_wait(10)
	driver.find_element_by_link_text("Toggle English/international characters").click()
        time.sleep(5)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_2_p4')
        time.sleep(5)
        element = driver.find_element_by_xpath("//input[@value='English']")
        ActionChains(driver).click(element).perform()
        
        time.sleep(5)
        
        assert 'FIJIKEN Co.,Ltd' in driver.page_source ,"The English Button is unavilable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_2_p5')

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
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease15.3_2_p6')
        
        
	print "\n"        
        print "Test Case end with successfully!"
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("Test_Case153_1"))
    testunit.addTest(FastPass_Agile("Test_Case153_2"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease15.3.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease15.3 test case')
    runner.run(testunit)




        

