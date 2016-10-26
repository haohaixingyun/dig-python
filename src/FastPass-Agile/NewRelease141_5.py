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
        
    def test_Case_141_5(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'IBM sales order number' field with '54420774' and click 'Search'."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("54420774")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click E01B1LL link"
	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'E01B1LL')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p4')
	time.sleep(3)

	print "\n"
	print "step5.hit back twice"
	driver.back()
	time.sleep(1)
	driver.back()
	time.sleep(1)
        result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p5')
        time.sleep(3)

	print "step6.Input 'IBM sales order number' field with '0053213904' and click 'Search'."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0053213904")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p6')
        time.sleep(3)

	print "\n"
	print "step7.Click D01X8ML link"
	driver.execute_script("window.scrollBy(0,1200)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'D01X8ML')])[1]").click()
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p7')
	time.sleep(3)

	print "\n"
	print "step8.hit back twice"
	driver.back()
	time.sleep(1)
	driver.back()
	time.sleep(1)
        result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p8')
        time.sleep(3)

	print "step9.Input 'IBM sales order number' field with '53515820' and click 'Search'."
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("53515820")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p9')
        time.sleep(3)

	print "\n"
	print "step10.Click D59KALL link"
	driver.execute_script("window.scrollBy(0,2000)","")
	time.sleep(3)
	driver.find_element_by_xpath("(//a[contains(text(),'D59KALL')])[1]").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_5_p10')
	time.sleep(3)

	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_141_5"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease141_5.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease141_5 test case')
    runner.run(testunit)




        

