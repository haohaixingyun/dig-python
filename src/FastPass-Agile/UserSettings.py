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
        
    def test_Case_UserSettings(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p1')
	time.sleep(3)
	
	    
	###capture screenshots
		
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.save user settings"
	driver.find_element_by_id("match").click()
	time.sleep(1)
	driver.find_element_by_id("origsite").click()
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(3)
        sel = driver.find_element_by_xpath("//select[@id='sortby']")
        Select(sel).select_by_value('Id')
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_name("Save settings").click()
	driver.implicitly_wait(10)
        time.sleep(1)
        message=driver.switch_to_alert().text
        print message 
        driver.switch_to_alert().accept()
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p3')
        time.sleep(3)

	print "\n"
	print "step4.restore default settings"
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_name("Restore defaults").click()
	time.sleep(1)
        message=driver.switch_to_alert().text
        print message 	
        driver.switch_to_alert().accept()
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p4')
        time.sleep(3)

        print "\n"
        print "step5.Click the 'SW Subscription and Support entitlement' option from the navigation panel. "
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
        driver.find_element_by_xpath("//a[contains(text(),'Entitlements')]").click()
        time.sleep(1)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p5')        
	time.sleep(3)

        
        
	print "\n"
	print "step6.save user settings"
	driver.find_element_by_id("match").click()
	time.sleep(1)
	driver.find_element_by_id("origsite").click()
	time.sleep(1)
	driver.find_element_by_id("Descending").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_name("Save settings").click()
	driver.implicitly_wait(10)
        time.sleep(1)
        message=driver.switch_to_alert().text
        print message 
        driver.switch_to_alert().accept()
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p6')
        time.sleep(3)

	print "\n"
	print "step7.restore default settings"
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_name("Restore defaults").click()
	time.sleep(1)
        message=driver.switch_to_alert().text
        print message 	
        driver.switch_to_alert().accept()
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p7')
        time.sleep(3)

        print "\n"
        print "step8.Click the 'Customers' option from the navigation panel. "
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
        driver.find_element_by_xpath("//a[contains(text(),'Customers')]").click()
        time.sleep(1)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p8')        
	time.sleep(3)
        
   
	print "\n"
	print "step9.save user settings"
	driver.find_element_by_id("match").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_name("Save settings").click()
	driver.implicitly_wait(10)
        time.sleep(1)
        message=driver.switch_to_alert().text
        print message 
        driver.switch_to_alert().accept()
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p9')
        time.sleep(3)

	print "\n"
	print "step10.restore default settings"
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_name("Restore defaults").click()
	time.sleep(1)
        message=driver.switch_to_alert().text
        print message 	
        driver.switch_to_alert().accept()
        time.sleep(1)
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','UserSettings_p10')
        time.sleep(3)	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_UserSettings"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_UserSettings.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is UserSettings test case')
    runner.run(testunit)




        

