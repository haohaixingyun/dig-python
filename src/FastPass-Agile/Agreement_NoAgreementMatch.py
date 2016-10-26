#coding = utf - 8

from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By


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
    
        

    def Test_Case2(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2. click agreements and login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
		
	print "\n"
	print "step3. Input 'Name' field with 'nntp' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("nntp")
	driver.find_element_by_id("All_sites").click()
	#driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
        driver.implicitly_wait(10)
        assert "No results were found that match the search criteria" in driver.page_source
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p3')
        driver.implicitly_wait(10)

        print "\n"
	print "step4. hit back"
	driver.get(self.base_url + "/software/xl/fastpass/agile/fastpass.nsf/agreements?openform")
        driver.implicitly_wait(10)

        print "\n"
        print "step5.Input 'IBM Customer number' field with '256756' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
        driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("256756")
	driver.find_element_by_id("All_sites").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        assert "No results were found that match the search criteria" in driver.page_source
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p4')
        driver.implicitly_wait(10)

        print "\n"
	print "step6. hit back"
	driver.get(self.base_url + "/software/xl/fastpass/agile/fastpass.nsf/agreements?openform")
        driver.implicitly_wait(10)

        print "\n"
        print "step7.Input 'Postal code' field with '4800123' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
        driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("4800123")
	driver.find_element_by_id("All_sites").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        assert "No results were found that match the search criteria" in driver.page_source
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p5')
        driver.implicitly_wait(10)

        print "\n"
	print "step8. hit back"
	driver.get(self.base_url + "/software/xl/fastpass/agile/fastpass.nsf/agreements?openform")
        driver.implicitly_wait(10)

        print "\n"
        print "step9.Input 'Agreement number' field with '446478' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
        driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("446478")
	driver.find_element_by_id("All_sites").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        assert "No results were found that match the search criteria" in driver.page_source
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p6')
        driver.implicitly_wait(10)

        print "\n"
	print "step10. hit back"
	driver.get(self.base_url + "/software/xl/fastpass/agile/fastpass.nsf/agreements?openform")
        driver.implicitly_wait(10)
        
        print "\n"
        print "step11.Input 'Site number' field with '7254326' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
        driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7254326")
	driver.find_element_by_id("All_sites").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        assert "No results were found that match the search criteria" in driver.page_source
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p7')
        driver.implicitly_wait(10)
        
        print "\n"
	print "step12. hit back"
	driver.get(self.base_url + "/software/xl/fastpass/agile/fastpass.nsf/agreements?openform")
        driver.implicitly_wait(10)

        
        print "\n"
        print "step13.Input 'Site number' field with '7023461'.  Have 'Active sites' clicked. Have all program types checked. Click 'Submit'"
        driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7023461")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        assert "No results were found that match the search criteria" in driver.page_source
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','T2_p8')
        driver.implicitly_wait(10)
        
        print "\n"
	print "step14. hit back"
	driver.get(self.base_url + "/software/xl/fastpass/agile/fastpass.nsf/agreements?openform")
        driver.implicitly_wait(10)

        print "Test case end with successfully!"


        

        
        time.sleep(3)
			
		
				
        
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("Test_Case2"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_Agreement_NoAgreementMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is Agreement_NoAgreementMatch Test case')
    runner.run(testunit)

