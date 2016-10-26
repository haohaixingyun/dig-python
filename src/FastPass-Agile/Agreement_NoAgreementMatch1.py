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


class FastPass_Agile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        self.base_url = "https://fpagile.boulder.ibm.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
        
    def test_Case_Agreement_NoAgreementMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p1')
	time.sleep(3)
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)


	print "\n"
	print "step3.Input 'cust name' field with '7887612' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("nntp")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p3')
        time.sleep(3)
        
	print "\n"
	print "step4.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)
        

	print "\n"
	print "step5.Input 'cust number' field with '256756' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("256756")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p4')
        time.sleep(3)
        
	print "\n"
	print "step6.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)
        

	print "\n"
	print "step7.Input 'Postalcode number' field with '97865' and click 'Search'."
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("97865")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p5')
        time.sleep(3)
        
	print "\n"
	print "step8.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)

	print "\n"
	print "step9.Input 'Agreement number' field with '446478' and click 'Search'."
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("446478")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p6')
        time.sleep(3)
        
	print "\n"
	print "step10.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)


	print "\n"
	print "step11.Input 'site number' field with '7254326' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7254326")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p7')
        time.sleep(3)
        
	print "\n"
	print "step12.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)	

	print "\n"
	print "step13.Input 'site number' field with '7023461' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7023461")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Agreement_NoAgreementMatch_p8')
        time.sleep(3)
        
	print "\n"
	print "step14.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)	
		
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_Agreement_NoAgreementMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_Agreement_NoAgreementMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is Agreement_NoAgreementMatch test case')
    runner.run(testunit)




        

