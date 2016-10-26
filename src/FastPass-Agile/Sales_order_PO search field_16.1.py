#coding = utf - 8

from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By

#import selenium.webdriver.support.ui as ui

import unittest
import time,sys
import login,C_screenshots
import HTMLTestRunner
reload(sys)
sys.setdefaultencoding("utf-8")

class FastPass_Agile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        #self.action =ActionChains(self.driver)
        #self.driver.implicitly_wait(30)
        self.base_url = "https://fpagile.boulder.ibm.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
    
        

    def Test_Case1(self):
		
	print "Test case start:"
	print "\n"
	print "step1. open the home page"
	driver = self.driver
	#action = self.action
	wait = self.wait
	#implicitly_wait = self.driver.implicitly_wait(30)
	driver.get(self.base_url + "software/xl/fastpass/agile/fphome.nsf/default?openform")
	driver.maximize_window()
	now_url = driver.current_url
	print now_url
	assert now_url == 'https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphome.nsf/default?openform' ,"URL is not correct."
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','PO_Search_16.1_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','PO_Search_16.1_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	
	print "\n"
	print "step3. click Sales orders and input 'Purchase Order' field with '0000002397' and click 'Search'."
	driver.implicitly_wait(10)
	driver.find_element_by_id("purchase_ord").clear()
	driver.find_element_by_id("purchase_ord").send_keys("0000002397")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(4)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"Page opened is not correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','PO_Search_16.1_p3')
        time.sleep(5)
	
        print "\n"
	print "step4. hit back"
        driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)

        print "\n"
	print "step5. input 'Purchase Order' field with 'SSSO-Melissa Goodwin' and click 'Search'."
	
	driver.implicitly_wait(10)
	driver.find_element_by_id("purchase_ord").clear()
	driver.find_element_by_id("purchase_ord").send_keys("SSSO-Melissa Goodwin")
	driver.find_element_by_name("ibm-submit").submit()
        driver.implicitly_wait(10)
        result = wait.until(EC.title_contains('Purchase order information'))
        assert result == True ,"Page opened is not correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','PO_Search_16.1_p4')
        time.sleep(3)
        
        print "\n"
	print "step5.click link of sales order num 0052832044"
        driver.execute_script("window.scrollBy(0,500)","")
        time.sleep(3)
        driver.find_element_by_xpath(".//*[@id='ibm-content-main']/div[3]/div/table/tbody/tr[2]/td[4]/a").click() ## 0052832044
        driver.implicitly_wait(10)
        time.sleep(3)
        result = wait.until(EC.title_contains('Sales order information'))
        assert 'Sales order information' in driver.page_source ,"Page opened is not correct"        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','PO_Search_16.1_p5')

        print "\n"
	print "step6.hit back"
        driver.back()
        time.sleep(3)
        driver.back()
        time.sleep(3)

                            
        print "\n"
	print "step7. input 'Purchase Order' field with '0000007855' and click 'Search'."
	driver.implicitly_wait(10)
	driver.find_element_by_id("purchase_ord").clear()
	driver.find_element_by_id("purchase_ord").send_keys("0000007855")
	driver.find_element_by_name("ibm-submit").submit()
        driver.implicitly_wait(10)
        assert  "No results were found that match the search criteria" in driver.page_source        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','PO_Search_16.1_p6')
        time.sleep(5)
        print "Test Case end with successfully!"
                            
	
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("Test_Case1"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_Sales_order(PO search field)_16.1.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is Po Number search function test case')
    runner.run(testunit)




        

