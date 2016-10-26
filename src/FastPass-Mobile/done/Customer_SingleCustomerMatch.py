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


class FastPass_Mobile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        self.base_url = "http://sbybz2239.sby.ibm.com:19080/FastPassS2/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
        
    def test_Case_Customer_SingleCustomerMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Customers')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'site number' field with '7017541' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7017541")
	time.sleep(1)

	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.Hit the Contacts related link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_link_text("Contacts").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Contact information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p4')
	time.sleep(3)


	

	print "\n"
	print "step5.Click the first email address in the Email column."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
	time.sleep(1)
        ele = driver.find_element_by_xpath("(//a[contains(text(),'jason.erwin.siu4@statefarm.com')])[1]").is_displayed()
	time.sleep(1)
	if ele == True:
            print "email link exist"
        else:
            print "email link does not exist"
            

            
	print "\n"
	print "step6.click site number link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'7017541')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p5')
	time.sleep(3)


	print "\n"
	print "step7.click Agreements link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p6')
	time.sleep(3)
	
	print "\n"
	print "step8.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step9.click Entitlements link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Entitlements')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p7')
	time.sleep(3)

	print "\n"
	print "step10.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step11.click Entitlements link."
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'View')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Acquisition customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p8')
	time.sleep(3)

	print "\n"
	print "step12.click site number link."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,200)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'7017541')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p9')
	time.sleep(3)
	
	print "\n"
	print "step13.In the Passport Advantage customer information table at the bottom, click the link for agreement number '09382'."
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,1500)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'09382')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p10')
	time.sleep(3)	
	
	print "\n"
	print "step14.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step15.In the Passport Advantage customer information table at the bottom, click the link for site name 'State Farm Mutual Automobile Insurance Company'"
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,1500)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'State Farm Mutual Automobile Insurance Company')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p11')
	time.sleep(3)

	print "\n"
	print "step16.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step17.In the Passport Advantage customer information table at the bottom, click the 'view' link of the Maintenance Entitlement field for agreement site '09382 / 7017541'."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,1500)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'View')])[9]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p12')
	time.sleep(3)


	print "\n"
	print "step18.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step19.In the Passport Advantage customer information table at the bottom, click 'Current view' link of the Sales Orders field for agreement site '09382 / 7017541'."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,1500)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Current view')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p13')
	time.sleep(3)


	print "\n"
	print "step20.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step21.In the Passport Advantage customer information table at the bottom, click 'Current view' link of the Sales Orders field for agreement site '09382 / 7017541'."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,1500)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'All view')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p14')
	time.sleep(3)

	print "\n"
	print "step22.Click the 'Customers' option from the navigation panel."
	time.sleep(1)
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p15')
	time.sleep(3)

	
	print "\n"
	print "step23.Click the 'Customers' option from the navigation panel."
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(3)
	driver.find_element_by_id("match").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p16')
	time.sleep(3)

	print "\n"
	print "step24.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step25.Input 'IBM customer number' field with '8112327' and click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("IBMcustomer").clear()
        driver.find_element_by_id("IBMcustomer").send_keys("8112327")
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p17')
	time.sleep(3)

	print "\n"
	print "step26.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step27.Input 'Acquisition customer number' field with '13156' and click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("migrtn_cust_num").clear()
        driver.find_element_by_id("migrtn_cust_num").send_keys("13156")
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p18')
	time.sleep(3)
	
	print "\n"
	print "step28.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)

	
	print "\n"
	print "step29.Input 'Customer name' field with 'State Farm'. Input 'IBM customer number' field with '8112327'. Click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(3)
        driver.find_element_by_id("IBMcustomer").clear()
        driver.find_element_by_id("IBMcustomer").send_keys("8112327")	
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p19')
	time.sleep(3)
	
	print "\n"
	print "step30.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step31.Input 'Customer name' field with 'Bell'. Input 'IBM customer number' field with '0956850'. Input 'Postal code' field with '19355'.Click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("Bell")
	time.sleep(3)
        driver.find_element_by_id("IBMcustomer").clear()
        driver.find_element_by_id("IBMcustomer").send_keys("0956850")	
	time.sleep(3)
        driver.find_element_by_id("Postalcode").clear()
        driver.find_element_by_id("Postalcode").send_keys("19355")	
	time.sleep(3)	
	driver.find_element_by_name("ibm-submit").click()
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p20')
	time.sleep(3)	

	print "\n"
	print "step32.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step33.Input 'Customer name' field with 'Bell'. Input 'Site number' field with '7125097'. Click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("Bell")
	time.sleep(3)
        driver.find_element_by_id("site").clear()
        driver.find_element_by_id("site").send_keys("7125097")	
	time.sleep(3)	
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p21')
	time.sleep(3)	


	print "\n"
	print "step34.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step35.Input 'Customer name' field with 'Pepsi'. Input 'Acquisition customer number' field with '199734'. Click 'Search'"
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("Pepsi")
	time.sleep(3)
        driver.find_element_by_id("migrtn_cust_num").clear()
        driver.find_element_by_id("migrtn_cust_num").send_keys("199734")	
	time.sleep(3)	
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p22')
	time.sleep(3)


	
	print "\n"
	print "step36.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step37.Input 'Customer name' field with 'State Farm'.Input 'IBM customer number' field with '7824737'. Input 'Postal code' field with '61791'. Click 'Search'"
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(3)
        driver.find_element_by_id("IBMcustomer").clear()
        driver.find_element_by_id("IBMcustomer").send_keys("7824737")	
	time.sleep(3)
        driver.find_element_by_id("Postalcode").clear()
        driver.find_element_by_id("Postalcode").send_keys("61791")
        time.sleep(3)
	driver.find_element_by_name("ibm-submit").click()
        time.sleep(3)	
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p23')
	time.sleep(3)

	
	print "\n"
	print "step38.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step39.Input 'Customer name' field with 'State Farm'. Input 'Site number' field with '7017541'. Click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("State Farm")
	time.sleep(3)
        driver.find_element_by_id("site").clear()
        driver.find_element_by_id("site").send_keys("7017541")	
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p24')
	time.sleep(3)
	

	print "\n"
	print "step40.go back."
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,100)","")
#	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Customers')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step41.Input 'Site number' field with '7907887'.Input 'Acquisition customer number' field with '1+L+12711'.Click 'Search'."
	time.sleep(1)
        driver.find_element_by_id("site").clear()
        driver.find_element_by_id("site").send_keys("7907887")	
	time.sleep(3)
        driver.find_element_by_id("migrtn_cust_num").clear()
        driver.find_element_by_id("migrtn_cust_num").send_keys("1+L+12711")
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Customer_SingleCustomerMatch_p25')
	time.sleep(3)

		
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_Customer_SingleCustomerMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_Customer_SingleCustomerMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is Customer_SingleCustomerMatch test case')
    runner.run(testunit)




        

