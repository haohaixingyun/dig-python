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
        
    def test_Case_141_1(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Input MachineType :7800011 and click 'Search'."
	driver.find_element_by_id("machineserialnumber").clear()
	driver.find_element_by_id("machineserialnumber").send_keys("7800011")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Appliance list - Search on machine serial number' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p3')
        time.sleep(3)

	print "\n"
	print "step4.Go back"
	driver.back()
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p4')
	time.sleep(3)

	print "\n"
	print "step5.Input 'Input oneMachineType :0121768 and click 'Search'."
	driver.find_element_by_id("machineserialnumber").clear()
	driver.find_element_by_id("machineserialnumber").send_keys("0121768")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Appliance information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p5')
        time.sleep(3)

	print "\n"
	print "step6.Go back"
	driver.back()
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p6')

	print "\n"
        print "step7.Input machine serial numer:10AA104 and  click 'Search'"
	driver.find_element_by_id("machineserialnumber").clear()
	driver.find_element_by_id("machineserialnumber").send_keys("10AA104")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Appliance list - Search on machine serial number' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p7')
        time.sleep(3)

	print "\n"
	print "step8.Go back"
	driver.back()
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p8')

	print "\n"
        print "step9.Input machine serial numer:1000162 and  click 'Search'"
	driver.find_element_by_id("machineserialnumber").clear()
	driver.find_element_by_id("machineserialnumber").send_keys("1000162")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Appliance information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p9')
        time.sleep(3)

	print "\n"
	print "step10.Go back"
	driver.back()
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p10')

	print "\n"
	print "step11.Input machine serial numer:7800252 and  click 'Search'"
	driver.find_element_by_id("machineserialnumber").clear()
	driver.find_element_by_id("machineserialnumber").send_keys("7800252")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Appliance list - Search on machine serial number' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p11')
        time.sleep(3)

	print "\n"
	print "step12.Go back"
	driver.back()
	time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p12')
	time.sleep(3)
	
	print "\n"	
        print "step13.Input sales order number:54420774 and  click 'Search'"
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("54420774")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease141_1_p11')
        time.sleep(3)
        

	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_141_1"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease141_1.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease141_1 test case')
    runner.run(testunit)




        

