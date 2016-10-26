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
        
    def test_Case_SalesOrder_ServicesAgreement(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SalesOrder_ServicesAgreement_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SalesOrder_ServicesAgreement_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'services_agreement_number' field with '0077080419' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)."
	driver.find_element_by_id("services_agreement_number").clear()
	driver.find_element_by_id("services_agreement_number").send_keys("0077080419")
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SalesOrder_ServicesAgreement_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click link of Associated billing orders ."
#	driver.execute_script("window.scrollBy(0,200)","")
#	time.sleep(3)
	driver.find_element_by_link_text("Associated billing orders").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement billing orders' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SalesOrder_ServicesAgreement_p4')
        time.sleep(3)
	

	print "\n"
	print "step5.Click the 'Next' link to see page 2 of data."
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.implicitly_wait(10)
	time.sleep(10)
	ele = driver.find_element_by_xpath("//a[@href='./ServsAgrmtBillingOrder?openagent&program=FC&sales_ord_num=0077080419&cust_num=0003393391&startrow=101']").is_displayed()
	print ele
	
	driver.find_element_by_xpath("a[@href='./ServsAgrmtBillingOrder?openagent&program=FC&sales_ord_num=0077080419&cust_num=0003393391&startrow=101']").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement billing orders' ,"The page did not be opened correct"
        assert '101-200' in driver.page_source ,"The data is not avalible"
        assert 'Billing order date' in driver.page_source ,"The data is not avalible"
        assert 'Billing order number' in driver.page_source ,"The data is not avalible"
        assert 'Part number' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SalesOrder_ServicesAgreement_p5')
	time.sleep(3)

	
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_SalesOrder_ServicesAgreement"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_SalesOrder_ServicesAgreement.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is SalesOrder_ServicesAgreement test case')
    runner.run(testunit)




        

