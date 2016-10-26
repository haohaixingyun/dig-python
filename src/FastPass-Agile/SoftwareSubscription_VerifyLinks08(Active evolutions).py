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
        
    def test_Case_SoftwareSubscription_VerifyLinks08(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Entitlements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input 'Agreement number' field with '09382'.Input 'Site number' field with '7017541'.Click 'Search'."
	driver.find_element_by_id("contractnum").clear()
	driver.find_element_by_id("contractnum").send_keys("09382")
	time.sleep(1)
	driver.find_element_by_id("customernum").clear()
	driver.find_element_by_id("customernum").send_keys("7017541")	
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p3')
        time.sleep(3)

	print "\n"
	print "step4.Select dropdown option 'Active evolutions' and hit Go button. "
        sel = driver.find_element_by_xpath("//select[@id='dropList1']")
        Select(sel).select_by_value('./EntitlemtEvoltnActvForSAPID?openagent&agree_num=0000009382&program=PA&ibm_cust_num=7824737&cust_type=NAV&cust_num=0007017541')
        driver.find_element_by_name("ibm-go").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p4')
        time.sleep(3)

	print "\n"
	print "step5.Click the value in the 'Customer name' field."
	driver.find_element_by_link_text("State Farm Mutual Automobile").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p5')
        time.sleep(3)

        
	print "\n"
	print "step6.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"

        
	print "\n"
	print "step7.Click the value in the 'Agreement number' field."
	driver.find_element_by_xpath("//a[contains(text(),'09382')]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p6')
        time.sleep(3)

        
	print "\n"
	print "step8.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"


	print "\n"
	print "step9.Click the value in the 'Site number' field."
	driver.find_element_by_xpath("//a[contains(text(),'7017541')]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p7')
        time.sleep(3)

        
	print "\n"
	print "step10.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"
        
	

	print "\n"
	print "step11.Click the first value in the 'Part number' column."
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
	driver.find_element_by_xpath("//a[contains(text(),'D03U5LL')]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p8')
        time.sleep(3)

        
	print "\n"
	print "step12.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"

        print "\n"
	print "step13.Click the first value in the 'Original part number' column."
#	driver.execute_script("window.scrollBy(0,800)","")
	time.sleep(1)
	driver.find_element_by_xpath("//a[contains(text(),'D0CYHLL')]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Part information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p9')
        time.sleep(3)

        
	print "\n"
	print "step14.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"       
        

        print "\n"
	print "step15.Click on 'All evolutions from sales transactions."
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("All evolutions from sales transactions").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All evolutions for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p10')
        time.sleep(3)

        print "\n"
	print "step16.Click the first value in the 'sales order number' column."
	driver.execute_script("window.scrollBy(0,800)","")
	time.sleep(1)
	driver.find_element_by_xpath("//a[contains(text(),'0050082266')]").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Sales orders - Sales order information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p11')
        time.sleep(3)        

        
	print "\n"
	print "step17.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	driver.back()
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"

        print "\n"
	print "step18.Click on 'Active sales orders' in the related links area."
	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("Active sales orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active sales orders for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p12')
        time.sleep(3)        

        
	print "\n"
	print "step19.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"
        

        print "\n"
	print "step20.Click on 'Active managed entitlements' in the related links area."
#	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("Active managed entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active managed entitlements for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p13')
        time.sleep(3)        

        
	print "\n"
	print "step21.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"


        print "\n"
	print "step22.Click on 'Active allocations' in the related links area."
#	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("Active allocations").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active allocations for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p14')
        time.sleep(3)        

        
	print "\n"
	print "step23.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"
        

        print "\n"
	print "step24.Click on 'All sales orders' in the related links area."
#	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("All sales orders").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All sales orders for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p15')
        time.sleep(3)        

        
	print "\n"
	print "step25.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"
        
        print "\n"
	print "step26.Click on 'All managed entitlements' in the related links area."
#	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("All managed entitlements").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All managed entitlements for site - Default sort by order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p16')
        time.sleep(3)        

        
	print "\n"
	print "step27.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"

        print "\n"
	print "step28.Click on 'All allocations' in the related links area."
#	driver.execute_script("window.scroll(0,0)","")
	time.sleep(1)
	driver.find_element_by_link_text("All allocations").click()
	driver.implicitly_wait(10)
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Entitlements - All allocations for site - Default sort by end date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SoftwareSubscription_VerifyLinks08_p17')
        time.sleep(3)        

        
	print "\n"
	print "step29.hit back"
	driver.back()
	driver.implicitly_wait(10)
	time.sleep(2)
	result = driver.title
        assert result == 'FastPass | Entitlements - Active evolutions for site - Default sort by end date',"The page did not be opened correct"

        
	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_SoftwareSubscription_VerifyLinks08"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_SoftwareSubscription_VerifyLinks08.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is SoftwareSubscription_VerifyLinks08 test case')
    runner.run(testunit)




        

