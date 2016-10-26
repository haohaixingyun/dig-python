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
        
    def test_Case144_1(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_1_p1')
        time.sleep(3)	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Sales orders')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_1_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Special bid number' field with 'SWG-PM010' and click 'Search'"
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0077090125")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_1_p3')
        time.sleep(3)
        
	print "\n"
	print "step4.hit back"
	driver.back()
        time.sleep(5)
        result1 = driver.title
        assert result1 == 'FastPass | Sales order - Sales order search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_1_p4')
        time.sleep(3)
        
	print "\n"
	print "step3.Input 'Special bid number' field with 'SWG-PM010' and click 'Search'"
	driver.find_element_by_id("sap_sales_ord_num").clear()
	driver.find_element_by_id("sap_sales_ord_num").send_keys("0077090204")
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result1 = driver.title
        assert result1 == 'FastPass | Sales orders - Services agreement' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_1_p5')
        time.sleep(3)

	print "\n"        
        print "Test Case end with successfully!"    
        
    def test_Case144_2(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_2_p1')
        time.sleep(3)	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_2_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Agreement number' field with '208295'.(Have all program types checked.)"
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("208295")
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,200)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_id("crponly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_2_p3')
        time.sleep(3)
        
	print "\n"
	print "step4.hit back"
	driver.back()
        time.sleep(5)
        result1 = driver.title
        assert result1 == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_2_p4')
        time.sleep(3)
        
	print "\n"
	print "step3.Input 'Agreement number' field with '212950'.(Have all program types checked.)"
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("212950")
	time.sleep(3)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,200)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	time.sleep(3)
	driver.find_element_by_id("crponly").click()
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_2_p5')
        time.sleep(3)

	print "\n"        
        print "Test Case end with successfully!"    
        
    def test_Case144_3(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_3_p1')
        time.sleep(3)	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_3_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Agreement number' field with '141103'.Input 'Site number' field with '7533956'.Click 'Search'.(Have all program types checked.)"
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("141103")
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7533956")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,200)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(1)
	driver.find_element_by_id("crponly").click()
	time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_3_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click current view"
	driver.execute_script("window.scrollBy(0,500)","")
	#driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_link_text("Current view").click()
	time.sleep(3)
        result1 = driver.title
        assert result1 == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_3_p4')
        time.sleep(3)
        
	print "\n"
	print "step5.Click SAP sales order(0055146989)"
	driver.execute_script("window.scrollBy(0,300)","")
	#driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_xpath("//a[contains(text(),'0055146989')]").click()
        time.sleep(3)
        result2 = driver.title
        assert result2 == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_3_p5')
        time.sleep(3)
        
        print "\n"
	print "step6.Click View_details"
	driver.execute_script("window.scrollBy(0,500)","")
	#driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_xpath("//a[contains(text(),'0055146989')]").click()
        time.sleep(3)
        result2 = driver.title
        assert result2 == 'FastPass | Sales orders - Media delivery shipping detail' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_3_p6')
        time.sleep(3)

	print "\n"        
        print "Test Case end with successfully!"
                            
    def test_Case144_4(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_4_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_4_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Name' field with 'ING' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("ING")
	time.sleep(3)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,500)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_4_p3')

        print "\n"
	print "step4.hit back"
	time.sleep(5)
	driver.back()
	

	
        print "\n"
	print "step5.Input 'Agreement number' field with '141103'.Input 'Site number' field with '7533956'.Click 'Search'.(Have all program types checked.)"
	driver.implicitly_wait(10)
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("141103")
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7533956")
	time.sleep(3)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,500)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
	driver.find_element_by_id("fconly").click()
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").submit()
        time.sleep(5)
        result1 = driver.title
        assert result1 == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','NewRelease144_4_p4')


	print "\n"        
        print "Test Case end with successfully!"
                            
    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case144_1"))
    testunit.addTest(FastPass_Agile("test_Case144_2"))
    testunit.addTest(FastPass_Agile("test_Case144_3"))
    testunit.addTest(FastPass_Agile("test_Case144_4"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_NewRelease144.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is NewRelease144 test case')
    runner.run(testunit)




        

