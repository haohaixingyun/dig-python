# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
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
        
    def test_Case_141_2(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease141_2_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease141_2_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Agreement number' field with '09382'.Input 'Site number' field with '7017541'.Click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("09382")	
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7017541")
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,200)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease141_2_p3')
        time.sleep(3)

	print "\n"
	print "step4.Select dropdown option 'All entitlements' and hit Go button."
        menu = driver.find_element_by_xpath("//select[@id='dropList1']")
        Select(menu).select_by_value('/FastPassS2/page/EntitlemtAllForSAPID?cust_num=0007017541&sap_ctrct_num=0000009382&cust_type=NAV&program=PA')
        time.sleep(1)
        driver.find_element_by_name("ibm-go").click()
	time.sleep(10)
	#wait.until(lambda the_driver: the_driver.find_element_by_id('dropList1').is_displayed())
	#menu = driver.find_element_by_id('dropList1').find_element_by_xpath("//option[@title='Active managed entitlements description']")
        #driver.implicitly_wait(10)
	#ActionChains(driver).move_to_element(menu).perform()
	#driver.implicitly_wait(10)
	#driver.find_element_by_xpath("//input[@name='ibm-go']").click
	#driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fastpass.nsf/EntitlemtMngdEmntsActvForSAPID?openagent&agree_num=0000009382&program=PA&ibm_cust_num=7824737&cust_type=NAV&cust_num=0007017541")
	
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Entitlements - All entitlements for site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease141_2_p4')
	time.sleep(3)

	print "\n"
	print "step5.Click the value in the 'Customer name' field."
	time.sleep(3)
	driver.find_element_by_link_text("State Farm Mutual Automobile").click()
        time.sleep(5)
        result = driver.title
        time.sleep(5)
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRelease141_2_p5')
        time.sleep(3)


	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_141_2"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRelease141_2.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRelease141_2 test case')
    runner.run(testunit)




        

