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
        
    def test_Case_CSTA(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Input site number :3044949  and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("3044949")
	#driver.execute_script("window.scrollBy(0,200)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("crponly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click the link of  all site "
	driver.implicitly_wait(10)
	driver.find_element_by_link_text("All sites").click()
	#wait.until(lambda the_driver: the_driver.find_element_by_id('dropList1').is_displayed())
	#menu = driver.find_element_by_link_text("All sites")
        #driver.implicitly_wait(10)
        time.sleep(3)
	#ActionChains(driver).move_to_element(menu).perform()
	#driver.implicitly_wait(10)
	#driver.find_element_by_xpath("//input[@name='ibm-go']").click
	#driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fastpass.nsf/salesorders?openform")
	
	#result = driver.title
        assert "Not applicable" in driver.page_source ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p4')
	time.sleep(3)

	print "step5.go back."
	driver.back()
	driver.implicitly_wait(10)
        time.sleep(5)
       

	print "\n"
	print "step6.click link of Originating site"
	driver.find_element_by_link_text("Originating site").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Originating site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p5')
        time.sleep(5)

	print "\n"
	print "step7.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)

        print "step8.click entitlement link"
	driver.implicitly_wait(10)
        time.sleep(5)
        driver.find_element_by_link_text("Entitlements").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p6')
        time.sleep(3)

	print "\n"
	print "step9.Go back"
	driver.back()
	time.sleep(5)

        print "step10.click link of customer name"
        driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_link_text("Lifoam Industries, LLC").click()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p7')
        time.sleep(3)

	print "\n"
	print "step11.Go back"
	driver.back()
	time.sleep(5)
		
        print "step12.click link of agreement link "
        driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_link_text("215513").click()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p8')
        time.sleep(3)

        print "\n"
	print "step13.Go back"
	driver.back()
	time.sleep(5)

        print "step14.click Site number link"
        driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_link_text("3044949").click()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p9')
        time.sleep(3)

        print "\n"
	print "step15.Go back"
	driver.back()
	time.sleep(5)

        print "step16.click Current view link"
        driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_link_text("Current view").click()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p10')
        time.sleep(3)

        print "\n"
	print "step17.Go back"
	driver.back()
	time.sleep(5)

        print "step18.click all view link"
        driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_link_text("All view").click()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','CSTA_p11')
        time.sleep(3)

        print "\n"
	print "step19.Go back"
	driver.back()
	time.sleep(5)

	
        

	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_CSTA"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_CSTA.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is CSTA test case')
    runner.run(testunit)




        

