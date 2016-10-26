# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
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
        
    def test_Case_SalesOrder_VerifyLinks(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'SAP sales order number' field with '52182772' and click 'Search'."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("52182772")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'Current sales orders' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click the 'Customer name' link."
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
        driver.find_element_by_xpath("//a[contains(text(),'State Farm Mutual AutomobileInsurance Company')]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p4')
	time.sleep(3)	

	print "\n"
	print "step5.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step6.Click the 'Agreement number' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'09382')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p5')
	time.sleep(3)	

	print "\n"
	print "step7.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step8.Click the 'Site number' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'7017541')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p6')        
	time.sleep(3)	

	print "\n"
	print "step9.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)
	
	print "\n"
	print "step10.Click the 'Bill-to site' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'IBM Global Finance')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Partner site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p7')        
	time.sleep(3)	

	print "\n"
	print "step11.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step12.Click the 'Ship-to site:' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'State Farm Mutual Automobile')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Partner site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p8')
	time.sleep(3)	

	print "\n"
	print "step13.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step14.Click the 'Reseller:' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'State Farm Mutual Automobile')])[3]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Partner site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p9')        
	time.sleep(3)	

	print "\n"
	print "step15.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step16.Click the 'Payer:' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'State Farm Mutual Automobile')])[4]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Partner site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p10')        
	time.sleep(3)	

	print "\n"
	print "step17.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step18.Click the 'Delivery details:' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'0052182772')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Media delivery shipping detail' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p11')        
	time.sleep(3)	

	print "\n"
	print "step19.Click the 'SAP sales order:' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'0052182772')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p12')        
	time.sleep(3)	

	print "\n"
	print "step20.hit back"
	driver.back()
	time.sleep(1)
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)

	
	print "\n"
	print "step21.Click the 'All sales order' related link."
	time.sleep(1)
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'All sales orders')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p13')        
	time.sleep(3)	

	print "\n"
	print "step22.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step23.Click the 'All sales order' related link."
	time.sleep(1)
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Current sales orders')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p14')        
	time.sleep(3)	


	print "\n"
	print "step24.Click the 'Customer name' link."
	time.sleep(1)
        driver.find_element_by_xpath("//a[contains(text(),'State Farm Mutual Automobile')]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p15')
	time.sleep(3)
	
	print "\n"
	print "step25.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step26.Click the 'Agreement number' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'09382')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p16')
	time.sleep(3)	

	print "\n"
	print "step27.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step28.Click the 'Site number' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'7017541')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p17')        
	time.sleep(3)	

	print "\n"
	print "step29.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
	time.sleep(3)


	
	print "\n"
	print "step30.Click the 'All sales order' related link."
	time.sleep(1)
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'All sales orders')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p18')        
	time.sleep(3)
	

	print "\n"
	print "step31.Click the 'Customer name' link."
	time.sleep(1)
        driver.find_element_by_xpath("//a[contains(text(),'State Farm Mutual Automobile')]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p19')
	time.sleep(3)
	
	print "\n"
	print "step32.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
	time.sleep(3)


	print "\n"
	print "step33.Click the 'Agreement number' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'09382')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p20')
	time.sleep(3)	

	print "\n"
	print "step34.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step35.Click the 'Site number' link."
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'7017541')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p21')        
	time.sleep(3)	

	print "\n"
	print "step36.hit back"
	driver.back()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step37.Click the 'Site number' link."
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
        driver.find_element_by_xpath("//a[@data-dt-idx='2']").click()
	time.sleep(3)
	result = driver.title
	print result
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','SalesOrder_VerifyLinks_p22')        
	time.sleep(3)		
	
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_SalesOrder_VerifyLinks"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_SalesOrder_VerifyLinks.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is SalesOrder_VerifyLinks test case')
    runner.run(testunit)




        

