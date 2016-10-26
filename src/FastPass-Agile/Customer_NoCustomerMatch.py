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
        
    def test_Case_Customer_NoCustomerMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p1')
	time.sleep(3)
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'customer name' field with '55556' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("19355")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step5.Input 'customer name' field with '55556' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("zze")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p4')
        time.sleep(3)

	print "\n"
	print "step6.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step7.Input 'customer name' field with '55556' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("5557878")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p5')
        time.sleep(3)

	print "\n"
	print "step8.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step9.Input 'customer name' field with '55556' and click 'Search'."
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("58765")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p6')
        time.sleep(3)

	print "\n"
	print "step10.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step11.Input 'customer name' field with '9988776' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("9988776")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p7')
        time.sleep(3)

	print "\n"
	print "step12.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)



	print "\n"
	print "step13.Input 'customer name' field with '300' and click 'Search'."
	driver.find_element_by_id("migrtn_cust_num").clear()
	driver.find_element_by_id("migrtn_cust_num").send_keys("300")
	time.sleep(1)	
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p8')
        time.sleep(3)

	print "\n"
	print "step14.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)




	print "\n"
	print "step15.Input 'customer name' field with '300' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(1)
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("0956850")
	time.sleep(1)
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("19355")
	time.sleep(1)		
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p9')
        time.sleep(3)

	print "\n"
	print "step16.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step17.Input 'customer name' field with '300' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("zze")
	time.sleep(1)
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7907887")
	time.sleep(1)		
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p10')
        time.sleep(3)

	print "\n"
	print "step18.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step19.Input 'customer name' field with '300' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("zze")
	time.sleep(1)
	driver.find_element_by_id("migrtn_cust_num").clear()
	driver.find_element_by_id("migrtn_cust_num").send_keys("300")
	time.sleep(1)		
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p11')
        time.sleep(3)

	print "\n"
	print "step20.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)



	print "\n"
	print "step21.Input 'customer name' field with '300' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7907887")
	time.sleep(1)
	driver.find_element_by_id("migrtn_cust_num").clear()
	driver.find_element_by_id("migrtn_cust_num").send_keys("300")
	time.sleep(1)		
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'No results were found that match the search criteria.' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','Customer_NoCustomerMatch_p12')
        time.sleep(3)

	print "\n"
	print "step22.Go back"
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_Customer_NoCustomerMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_Customer_NoCustomerMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is Customer_NoCustomerMatch test case')
    runner.run(testunit)




        

