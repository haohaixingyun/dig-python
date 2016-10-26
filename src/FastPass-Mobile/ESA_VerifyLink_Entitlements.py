# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
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
        
    def test_Case_ESA_VerifyLink_Entitlements(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'name' field with 'BP ESA'."
	driver.find_element_by_id("cust_name").clear()
	driver.find_element_by_id("cust_name").send_keys("BP ESA")
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
        driver.find_element_by_id("emonly").click()
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
        time.sleep(1)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'Embedded Solution Agreement' in driver.page_source ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p3')
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
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p4')
        time.sleep(3)

        
	print "\n"
	print "step5.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name',"The page did not be opened correct"
        time.sleep(3)
        
	print "\n"
	print "step6.Click the value in the 'Agreement numberfield."
	driver.find_element_by_xpath("(//a[contains(text(),'229225')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p5')
        time.sleep(3)

        
	print "\n"
	print "step7.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name',"The page did not be opened correct"
        time.sleep(3)

	print "\n"
	print "step8.Click the value in the 'Site number' field."
	driver.find_element_by_xpath("(//a[contains(text(),'3806111')])[1]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p6')
        time.sleep(3)


	print "\n"
	print "step9.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name',"The page did not be opened correct"
        time.sleep(3)        


	print "\n"
	print "step10.Select dropdown option 'delivery' and hit Go button. "
        sel = driver.find_element_by_xpath("//select[@id='dropList1']")
        Select(sel).select_by_value('./EntitlemtActvForSAPID?openagent&agree_num=0000229225&program=EA&cust_type=NAV&cust_num=0003806111')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
	print result
	time.sleep(2)
	assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p7')
        time.sleep(3)

	print "\n"
	print "step11.Click the link of Active sales orders"
	driver.find_element_by_link_text("Active sales orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active sales orders for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p8')
        time.sleep(3)
        
	print "\n"
	print "step12.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)        

	print "\n"
	print "step13.Click the link of Active managed entitlements"
	driver.find_element_by_link_text("Active managed entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active managed entitlements for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p9')
        time.sleep(3)


	print "\n"
	print "step14.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)	

        print "\n"
	print "step15.Click link of Active evolutions from sales transactions."
#	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
	driver.find_element_by_link_text("Active evolutions from sales transactions").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p10')
        time.sleep(3)

	print "\n"
	print "step16.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)


        print "\n"
	print "step17.Click link of All sales orders."
#	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)
	driver.find_element_by_link_text("All sales orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales orders for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p11')
        time.sleep(3)

	print "\n"
	print "step16.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)



        print "\n"
	print "step18.Click link of All managed entitlements."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_link_text("All managed entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All managed entitlements for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p12')
        time.sleep(3)

	print "\n"
	print "step19.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)        


        print "\n"
	print "step20.Click link of All sales transactions ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_link_text("All sales transactions").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales transactions for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p13')
        time.sleep(3)

	print "\n"
	print "step21.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)          

        print "\n"
	print "step22.Click link of  All evolutions from sales transactions ."
#	driver.execute_script("window.scrollBy(0,1000)","")
	time.sleep(2)
	driver.find_element_by_link_text("All evolutions from sales transactions").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All evolutions for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','ESA_VerifyLink_Entitlements_p14')
        time.sleep(3)

	print "\n"
	print "step22.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active entitlements for site - Default sort by end date',"The page did not be opened correct"
        time.sleep(3)

        
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_ESA_VerifyLink_Entitlements"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_ESA_VerifyLink_Entitlements.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is ESA_VerifyLink_Entitlements test case')
    runner.run(testunit)




        

