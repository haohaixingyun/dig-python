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
        
    def test_Case_NewRealease_VOctober_V17UI_08(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	time.sleep(3)


	print "\n"
	print "step3.Click the 'Entitlements' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p3')
        time.sleep(3)


	print "\n"
	print "step4.Click the 'Sales orders' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Sales orders')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p4')
        time.sleep(3)



	print "\n"
	print "step5.Click the 'Customers' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p5')
        time.sleep(3)

	print "\n"
	print "step6.Click the 'FastPass access' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'FastPass access')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | How do I manage FastPass access?' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p6')
        time.sleep(3)

	print "\n"
	print "step7.Click the 'Agreements' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p7')
        time.sleep(3)
		

	print "\n"
	print "step8.Input 'name' field with '3381897'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(10)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p8')
        time.sleep(3)

	print "\n"
	print "step9.Click the 'Entitlements' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Entitlements search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p9')
        time.sleep(3)

	print "\n"
	print "step10.Input 'agreement num' field with '09382'."
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("09382")
	time.sleep(1)
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7017541")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(10)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        assert 'Country State Province' in driver.page_source ,"The data is not avaliable"
        assert 'Agreement - Site number' in driver.page_source ,"The data is not avaliable"
        assert 'Program type' in driver.page_source ,"The data is not avaliable"
        assert 'ICN / Enterprise / International account numbers' in driver.page_source ,"The data is not avaliable"
        assert 'Site status / Allocated entitlements' in driver.page_source ,"The data is not avaliable"
        assert 'Customer name' in driver.page_source ,"The data is not avaliable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p10')
        time.sleep(3)

	print "\n"
	print "step11.Click the 'Customers' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p11')
        time.sleep(3)

	print "\n"
	print "step12.Input 'name' field with '3381897'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(10)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p12')
        time.sleep(3)        


	print "\n"
	print "step13.Click the link of site number ."
	driver.find_element_by_xpath("(//a[contains(text(),'3577683')])[1]").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p13')
        time.sleep(3)



	print "\n"
	print "step14.Click the 'Help' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/help_home.html")
	driver.implicitly_wait(10)
        time.sleep(3)
        assert 'FastPass is an IBM internal' in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','NewRealease_VOctober_V17UI_08_p14')
        time.sleep(3)
        
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_NewRealease_VOctober_V17UI_08"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_NewRealease_VOctober_V17UI_08.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is NewRealease_VOctober_V17UI_08 test case')
    runner.run(testunit)




        

