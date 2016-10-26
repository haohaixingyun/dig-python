# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
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
        
    def test_Case_NewRelease104(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Search for one of the Billing Orders (0053909201) using the Sales Order Search form's SAP Sales Order number entry box."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0053909201")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.implicitly_wait(10)
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'Services agreement number:' in driver.page_source ,"The data is not available"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p3')
        time.sleep(3)

	print "\n"
	print "step4.click the link of service num (0053909201) "
	driver.find_element_by_link_text("0077082205").click()
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        assert 'Services agreement number:' in driver.page_source ,"The data is not available"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p4')
        time.sleep(3)

	print "\n"
	print "step5.click the link of entitlements "
	driver.find_element_by_link_text("Entitlements").click()
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p5')
        time.sleep(3)

	print "\n"
	print "step6.Select dropdown option 'All Entitlements' and hit Go button. "
        sel = driver.find_element_by_xpath("//select[@id='dropList1']")
        Select(sel).select_by_value('./EntitlemtAllForSAPID?openagent&agree_num=NAV&program=PX&ibm_cust_num=5877797&cust_type=NAV&cust_num=0003464947')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All entitlements for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p6')
        time.sleep(3)

	print "\n"
	print "step7.Click on the first Part Number values."
	driver.execute_script("window.scrollBy(0,600)","")
	driver.implicitly_wait(10)
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'D0NQBLL')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        assert 'D0NQBLL' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p7')
        time.sleep(3)
  
	print "\n"
	print "step8.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - All entitlements for site - Default sort by end date'

	print "\n"
	print "step9.Click on 'All sales orders' in the related links area."
	driver.execute_script("window.scroll(0,0)","")	
	driver.implicitly_wait(10)
	time.sleep(3)	
	driver.find_element_by_link_text("All sales orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales orders for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p8')        


	print "\n"
	print "step10.Click on the first Part Number values."
	driver.execute_script("window.scrollBy(0,500)","")
	driver.implicitly_wait(10)
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'D0NQBLL')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        assert 'D0NQBLL' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p9')
        time.sleep(3)
  
	print "\n"
	print "step11.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales orders for site - Default sort by order date'



	print "\n"
	print "step12.Click on the first Sales order Number values."
#	driver.execute_script("window.scrollBy(0,400)","")	
	driver.find_element_by_xpath("(//a[contains(text(),'0077082205')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p10')
        time.sleep(3)
  
	print "\n"
	print "step13.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales orders for site - Default sort by order date'
        
	print "\n"
	print "step14.Click on the  site Number values."
	driver.execute_script("window.scroll(0,0)","")
	driver.implicitly_wait(10)
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'3464947')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p11')
        time.sleep(3)

	print "\n"
	print "step15.Click on the  agreements link."
	driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details - Not applicable' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p12')
        time.sleep(3)
        
	print "\n"
	print "step15.Click on the  Originating site link."
	driver.find_element_by_link_text("Originating site").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Originating site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p13')
        time.sleep(3)

	print "\n"
	print "step16.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details - Not applicable'
        time.sleep(3)

	print "\n"
	print "step17.Click link of All sales orders."
	driver.execute_script("window.scrollBy(0,200)","")
	driver.implicitly_wait(10)
	time.sleep(3)	
	driver.find_element_by_link_text("All view").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_p14')

	print "\n"
	print "step18.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details - Not applicable'
        time.sleep(3)
     
        

	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_NewRelease104"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease104.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease104 test case')
    runner.run(testunit)




        

