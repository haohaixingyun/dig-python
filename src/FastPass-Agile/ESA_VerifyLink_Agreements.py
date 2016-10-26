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
        
    def test_Case_ESA_VerifyLink_Agreements(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'name' field with 'BP ESA'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("BP ESA")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)
        driver.find_element_by_id("eaonly").click()
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Embedded Solution Agreement' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click the name of link (BP ESA AUS customer FVT)  "
        driver.find_element_by_xpath("(//a[contains(text(),'BP ESA AUS customer FVT')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
	print result
	time.sleep(2)
	assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p4')
        time.sleep(3)

        
	print "\n"
	print "step5.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name',"The page did not be opened correct"
        time.sleep(3)
        
	print "\n"
	print "step6.Click the value in the 'Agreement numberfield."
	driver.find_element_by_xpath("(//a[contains(text(),'229225')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p5')
        time.sleep(3)

        
	print "\n"
	print "step7.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name',"The page did not be opened correct"
        time.sleep(3)

	print "\n"
	print "step8.Click the value in the 'Site number' field."
	driver.find_element_by_xpath("(//a[contains(text(),'3806111')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p6')
        time.sleep(3)

	print "\n"
	print "step9.Click the link of contacts"
	driver.find_element_by_link_text("Contacts").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Contact information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p7')
        time.sleep(3)
        
	print "\n"
	print "step10.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)        

	print "\n"
	print "step11.Click the link of Agreements"
	driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p8')
        time.sleep(3)


	print "\n"
	print "step12.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)	

        print "\n"
	print "step13.Click link of Entitlements."
#	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
	driver.find_element_by_link_text("Entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p9')
        time.sleep(3)

	print "\n"
	print "step14.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)


        print "\n"
	print "step15.Click link of Entitlements."
#	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
	driver.find_element_by_link_text("ICN hold orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Orders on ICN hold - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p10')
        time.sleep(3)

	print "\n"
	print "step16.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)



        print "\n"
	print "step17.Click link of Current view."
	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_link_text("Current view").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p11')
        time.sleep(3)

	print "\n"
	print "step18.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)        


        print "\n"
	print "step19.Click link of All view ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_link_text("All view").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p12')
        time.sleep(3)

	print "\n"
	print "step20.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)          

        print "\n"
	print "step21.Click link of  view ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[3]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p13')
        time.sleep(3)

	print "\n"
	print "step22.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)

        print "\n"
	print "step23.Click link of  view ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_xpath("(//a[contains(text(),'View')])[4]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Global brand family history' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','ESA_VerifyLink_Agreements_p14')
        time.sleep(3)

	print "\n"
	print "step24.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details',"The page did not be opened correct"
        time.sleep(3)
        
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_ESA_VerifyLink_Agreements"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_ESA_VerifyLink_Agreements.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is ESA_VerifyLink_Agreements test case')
    runner.run(testunit)




        

