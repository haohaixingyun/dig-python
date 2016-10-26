# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.select import Select
from selenium.webdriver.common.keys import Keys
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
        
    def test_Case_Agreement_SingleAgreementMatch(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p1')
	time.sleep(3)
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	time.sleep(3)

	print "\n"
	print "step3.Input 'Agreement number' field with '55556' and click 'Search'."
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("55556")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"

        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click on 'All sites' in the related links area."
        driver.find_element_by_link_text("All sites").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p5')        
	time.sleep(3)

	print "\n"
	print "step5.Click on 'Originating site' in the related links area."
        driver.find_element_by_link_text("Originating site").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Originating site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p6')        
	time.sleep(3)	

	print "\n"
	print "step6.Click on 'Maintenance entitlements' in the related links area."
        driver.find_element_by_link_text("Entitlements").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p7')        
	time.sleep(3)
	
	print "\n"
	print "step7.Click on 'Maintenance entitlements' in the related links area."
        driver.find_element_by_xpath("(//a[contains(text(),'55556')])[1]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p8')        
	time.sleep(3)        



	print "\n"
	print "step8.Click on 'Maintenance entitlements' in the related links area."
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.execute_script("window.scrollBy(0,200)","")
        time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Convergys')])[1]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p9')        
	time.sleep(3)

	print "\n"
	print "step9.Go back"
#	driver.execute_script("window.scrollBy(0,1200)","")
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p10')        
	time.sleep(3)     


	print "\n"
	print "step10.Click on first site number in the lower table(7131453)."
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'7131453')])[1]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p11')        
	time.sleep(3)


	print "\n"
	print "step11.Go back"
#	driver.execute_script("window.scrollBy(0,1200)","")
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p12')        
	time.sleep(3)

	
	
	print "\n"
	print "step12.Click on 'Current view' in the sales orders field in the lower field."
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)
        driver.find_element_by_xpath("(//a[contains(text(),'Current view')])[1]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p13')        
	time.sleep(3)


	print "\n"
	print "step13.Go back"
#	driver.execute_script("window.scrollBy(0,1200)","")
        driver.back()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p14')        
	time.sleep(3)


	
	
	print "\n"
	print "step14.Click on 'All view' in the sales orders field in the lower field."
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	driver.execute_script("window.scrollBy(0,500)","")
	time.sleep(1)	
        driver.find_element_by_xpath("(//a[contains(text(),'All view')])[1]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p15')        
	time.sleep(3)



	print "\n"
	print "step15.Click on 'Agreements' in the sales orders field in the lower field."
#	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
#	driver.execute_script("window.scrollBy(0,200)","")
	time.sleep(1)	
        driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[1]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p16')        
	time.sleep(3)


	print "\n"
	print "step16.Input 'site number' field with '11531' and click 'Search'."
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("11531")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p17')
        time.sleep(3)
        
	print "\n"
	print "step17.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)	


	print "\n"
	print "step18.Input 'site number' field with '7254326' and click 'Search'."
	driver.find_element_by_id("site").clear()
	driver.find_element_by_id("site").send_keys("7254326")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"

        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p18')
        time.sleep(3)
        
	print "\n"
	print "step19.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)	


	print "\n"
	print "step20.Input 'cust number' field with '0956850' and click 'Search'."
	driver.find_element_by_id("IBMcustomer").clear()
	driver.find_element_by_id("IBMcustomer").send_keys("0956850")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"

        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p19')
        time.sleep(3)
        
	print "\n"
	print "step21.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)
        

	print "\n"
	print "step22.Input 'Postalcode number' field with '78008' and click 'Search'."
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("78008")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p20')
        time.sleep(3)
        
	print "\n"
	print "step23.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)


	print "\n"
	print "step24.Input 'Postalcode number' field with '313002' and click 'Search'."
	driver.find_element_by_id("Postalcode").clear()
	driver.find_element_by_id("Postalcode").send_keys("313002")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p21')
        time.sleep(3)
        
	print "\n"
	print "step25.Go back."
	driver.back()
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"	
	time.sleep(3)


	print "\n"
	print "step26.Input 'site number' field with 'Kunta AVP' and click 'Search'."
	driver.find_element_by_id("name").clear()
	driver.find_element_by_id("name").send_keys("Kunta AVP")
	time.sleep(1)
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(1)	
	driver.find_element_by_id("fconly").click()
	time.sleep(1)	
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"

        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_SingleAgreementMatch_p22')
        time.sleep(3)
		
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("test_Case_Agreement_SingleAgreementMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+" FastPass_Test_Case_Agreement_SingleAgreementMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is Agreement_SingleAgreementMatch test case')
    runner.run(testunit)




        

