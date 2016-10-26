# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
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
        
    def test_Case_NewRelease101_2(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'services_agreement_number' field with '0077080419' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("48640")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        ele = driver.find_element_by_link_text("Originating site").is_displayed()
        print ele
        assert ele == True ,"The link of Originating site is unavilable"
        ele2 = driver.find_element_by_link_text("Michael Weinig AG").is_displayed()
        print ele2
        assert ele2 == True ,"The link of Michael Weinig AG is unavilable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click link of Originating site ."
#	driver.execute_script("window.scrollBy(0,200)","")
#	time.sleep(3)
	driver.find_element_by_link_text("Originating site").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Originating site' ,"The page did not be opened correct"
        assert 'Accepted' in driver.page_source ,"The data is unavailble"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p4')
        time.sleep(3)
	

	print "\n"
	print "step5.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
	
	print "\n"
	print "step6.Click link of customer link ."
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(3)
	driver.find_element_by_link_text("Michael Weinig AG").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        assert 'Accepted' in driver.page_source ,"The data is unavailble"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p5')
        time.sleep(3)

	print "\n"
	print "step7.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
	
	print "\n"
	print "step8.Click link of customer link ."
#	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'48640')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        assert 'Accepted' in driver.page_source ,"The data is unavailble"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p6')
        time.sleep(3)        


	print "\n"
	print "step9.Input 'agreement_number' field with '11585' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"

	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("11585")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p7')
        time.sleep(3)


	print "\n"
	print "step10.Click link of customer name  ."
	#driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(3)
	driver.find_element_by_link_text("AIA INSURANCE LANKA PLC").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        assert 'Government for business partner incentives:' in driver.page_source ,"The data is unavailble"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p8')
        time.sleep(3)

	print "\n"
	print "step11.Input 'agreement_number' field with '114676' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_link_text("Agreements").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"

	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("114676")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p9')
        time.sleep(3)
        
	print "\n"
	print "step12.Click link of current view  ."
	driver.execute_script("window.scrollBy(0,800)","")
	time.sleep(3)
	driver.find_element_by_link_text("Current view").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p10')
        time.sleep(3)


	print "\n"
	print "step13.Click link of 0053031062  ."
	driver.execute_script("window.scrollBy(0,1500)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'0053031062')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'SW Value Plus terms / product group:' in driver.page_source ,"The data is unavailable"
        assert 'Business partner incentives:' in driver.page_source ,"The data is unavailable"
        assert 'SW Value Plus terms / product group:' in driver.page_source ,"The data is unavailable"
        
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p11')
        time.sleep(3)

	print "\n"
	print "step14.Click link of view  ."
	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Business partner incentives' ,"The page did not be opened correct"
        assert 'Site information (at time of transaction)' in driver.page_source ,"The data is unavailable"
        assert 'Business partner data' in driver.page_source ,"The data is unavailable"
        assert 'Sales order' in driver.page_source ,"The data is unavailable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p12')
        time.sleep(3)        


	print "\n"
	print "step15.Click the 'Customers' option from the navigation panel."
	driver.find_element_by_link_text("Customers").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer search' ,"The page did not be opened correct"

	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7762975")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'Government for business partner incentives:' in driver.page_source ,"The data is unavailable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p13')
        time.sleep(3)


	print "\n"
	print "step16.Click the 'Agreements' option from the navigation panel."
	driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[2]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"


	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7019455")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p14')
        time.sleep(3)
        
	print "\n"
	print "step17.Click link of 7019455  ."
	driver.execute_script("window.scrollBy(0,800)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'7019455')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'Global brand family history' in driver.page_source ,"The data is unavailable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p15')
        time.sleep(3)

	print "\n"
	print "step18.Click link of GBFH view link   ."
	driver.execute_script("window.scrollBy(0,800)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[4]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Global brand family history' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p16')
        time.sleep(3)

	print "\n"
	print "step19.Click the 'Sales orders' option from the navigation panel."
	driver.find_element_by_link_text("Sales orders").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"

	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0052437024")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        assert 'Brand family for business partner incentives:' in driver.page_source ,"The data is unavailable"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease101_2_p17')
        time.sleep(3)

        
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_NewRelease101_2"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease101_2.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease101_2 test case')
    runner.run(testunit)




        

