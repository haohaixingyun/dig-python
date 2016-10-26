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
        
    def test_Case_Customer_MultipleCustomersMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Customer name field with 'State Farm'.Click 'Search."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)

	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.click first site number  link."
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'3577683')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p4')
	time.sleep(3)	

	print "\n"
	print "step5.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	

	print "\n"
	print "step6.Input Customer name' field with 'State Farm'.Click descending sort order.Click 'Search."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
        driver.find_element_by_id("Descending").click()
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p5')
        time.sleep(3)


	print "\n"
	print "step7.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step8.Input 'Customer name' field with 'State Farm'.Sort by 'Site number'.Click 'Search'"
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
        driver.find_element_by_id("Descending").click()
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p6')
        time.sleep(3)

	print "\n"
	print "step9.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step10.Click the 'Customers' option from the navigation panel. "
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,200)","")
        sel = driver.find_element_by_xpath("//select[@id='sortby']")
        Select(sel).select_by_value('Site')
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p7')
        time.sleep(3)

	print "\n"
	print "step11.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step12.Input 'Customer name' field with 'State Farm'.Sort by 'Country'.Click 'Search' "
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)	
        sel = driver.find_element_by_xpath("//select[@id='sortby']")
        Select(sel).select_by_value('Country')
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p8')
        time.sleep(3)


	print "\n"
	print "step13.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"

	time.sleep(3)	


	print "\n"
	print "step14.Input 'Customer name' field with 'State Farm'.Sort by 'Country'.Click 'Search' "
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
        sel = driver.find_element_by_xpath("//select[@id='sortby']")
        Select(sel).select_by_value('Country')
        time.sleep(1)
        driver.find_element_by_id("Descending").click()
        time.sleep(1)        
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p9')
        time.sleep(3)

	print "\n"
	print "step15.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step16.Input 'Postal code' field with '61710' and click 'Search'. "
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("61710")
	time.sleep(1)       
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p10')
        time.sleep(3)


	print "\n"
	print "step17.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step18.Input 'Postal code' field with '61710' and click 'Search'. "
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)	
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("61710")
	time.sleep(1)       
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p11')
        time.sleep(3)


	print "\n"
	print "step19.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step20.Input 'IBM customer number' field with '7824737' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("7824737")
	time.sleep(1)	
   
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_12')
        time.sleep(3)


	print "\n"
	print "step21.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step22.Input 'Postal code' field with '19355' and click 'Search'. "
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("19355")
	time.sleep(1)       
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p13')
        time.sleep(3)


	print "\n"
	print "step23.click customer search page  link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)	


	print "\n"
	print "step24.Input 'Input 'Acquisition customer number' field with '28688' and click 'Search'.."
	driver.find_element_by_id("migrtn_cust_num").clear()
	driver.find_element_by_id("migrtn_cust_num").send_keys("28688")
	time.sleep(1)	
   
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_MultipleCustomersMatch_p14')
        time.sleep(3)



	
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_Customer_MultipleCustomersMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_Customer_MultipleCustomersMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is Customer_MultipleCustomersMatch test case')
    runner.run(testunit)




        

