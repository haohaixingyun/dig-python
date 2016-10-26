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
        
    def test_Case_NewRelease104_2(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Search for one of the Billing Orders (53425267) using the Sales Order Search form's SAP Sales Order number entry box."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("53425267")
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p3')
        time.sleep(3)

        
	print "\n"
	print "step4.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search',"The page did not be opened correct"


	print "\n"
	print "step5.Search for Services Agreement value (77080151)using Sales Order Search form."
	driver.find_element_by_id("services_agreement_number").clear()
	driver.find_element_by_id("services_agreement_number").send_keys("77080151")
	time.sleep(1)
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p4')
        time.sleep(3)

	print "\n"
	print "step6.Click on 'entitlements' in the related links area."
	driver.find_element_by_link_text("Entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p5')
        time.sleep(3)


	print "\n"
	print "step7.Select dropdown option 'Active evolutions' and hit Go button. "
        sel = driver.find_element_by_xpath("//select[@id='dropList1']")
        Select(sel).select_by_value('./EntitlemtSalesTransAllForSAPID?openagent&agree_num=NAV&program=FC&ibm_cust_num=2829503&cust_type=NAV&cust_num=0003381669')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales transactions for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p6')
        time.sleep(3)

	print "\n"
	print "step8.Click on the first Part Number values."
	driver.find_element_by_xpath("(//a[contains(text(),'D0M7QLL')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        assert 'SaaS part type:' in driver.page_source,"The page did not be opened correct"
        assert 'SaaS service provider:' in driver.page_source,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p7')
        time.sleep(3)
  
	print "\n"
	print "step9.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales transactions for site - Default sort by order date'

	print "\n"
	print "step10.Click link of All managed entitlements."
	driver.find_element_by_link_text("All managed entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All managed entitlements for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p8')        
        
	print "\n"
	print "step11.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales transactions for site - Default sort by order date'


	print "\n"
	print "step12.Click link of All sales orders."
	driver.find_element_by_link_text("All sales orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales orders for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p9')

	print "\n"
	print "step13.Click on the first sales order Number values."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
	driver.find_element_by_xpath("(//a[contains(text(),'0077080151')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p10')
        time.sleep(3)

	print "\n"
	print "step14.Click link of All managed entitlements."
	driver.find_element_by_link_text("Associated billing orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement billing orders' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p11')        
                
	print "\n"
	print "step15.Click on the first Billing order number  values."
	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
	driver.find_element_by_xpath("(//a[contains(text(),'0053652320')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease104_2_p12')
        time.sleep(3)        
     
        

	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_NewRelease104_2"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease104_2.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease104_2 test case')
    runner.run(testunit)




        

