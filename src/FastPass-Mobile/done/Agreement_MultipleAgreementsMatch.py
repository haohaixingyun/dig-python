# coding = utf - 8


from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains
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
        

        
    def Agreement_MultipleAgreementsMatch(self):
        
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)
	time.sleep(3)

	print "\n"
	print "step3.Input 'Name' field with 'ING' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
	driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("ING")
        driver.find_element_by_id("All_sites").click()
	#driver.execute_script("window.scrollBy(0,200)","")
        time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p3')
        time.sleep(3)


        print "\n"
        print "step4. click link 11531 on the page"
        driver.implicitly_wait(10)
        time.sleep(3)
        driver.execute_script("window.scrollBy(0,300)","")
        time.sleep(3)
        driver.find_element_by_xpath("//th[contains(text(),'Agreement - Site number')]").click()
        time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'04942')])[1]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p4')
        time.sleep(3)
        

        

        print "\n"
        print "step5. hit back"
        driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)

        print "\n"
        print "step6. Click 'Customer numbers' (7084912) displayed on the result page."
        driver.implicitly_wait(10)
        time.sleep(3)
        #driver.execute_script("window.scrollBy(0,300)","")
        #time.sleep(3)
        driver.find_element_by_xpath("//th[contains(text(),'Agreement - Site number')]").click()
        time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'7586162')])[1]").click()
        time.sleep(8)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p5')
        time.sleep(3)


        print "\n"
        print "step7. hit back"
        driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)


        print "\n"
        print "step8. Click 'Customer name' (ING (US) Financial Services Corp) displayed on the result page."
        driver.implicitly_wait(10)
        time.sleep(3)
        driver.find_element_by_xpath("//th[contains(text(),'Agreement - Site number')]").click()
        time.sleep(3)
        driver.find_element_by_link_text("Ingenio").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p6')
        time.sleep(3)

        
        print "\n"
        print "step9.Click on 'Contacts' in related links area on the Agreement site information page."  
        driver.implicitly_wait(10)
        time.sleep(3)
        #driver.execute_script("window.scrollBy(0,300)","")
        #time.sleep(3)
        driver.find_element_by_link_text("Contacts").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Contact information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p7')
        time.sleep(3)
        
        print "\n"
        print "step10. hit back"
        driver.back()
	time.sleep(5)

        

        print "\n"
        print "step11.Click on 'Current sales orders' in related links area on the Agreement site information page."
        driver.implicitly_wait(10)
        time.sleep(3)
        #driver.execute_script("window.scrollBy(0,300)","")
        #time.sleep(3)
        driver.find_element_by_link_text("Current sales orders").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p8')
        time.sleep(3)        

        print "\n"
        print "step12. hit back"
        driver.back()
	time.sleep(5)

        print "\n"
        print "step13.Click on 'All sales orders' in related links area on the Agreement site information page."
        driver.implicitly_wait(10)
        time.sleep(3)
        #driver.execute_script("window.scrollBy(0,300)","")
        #time.sleep(3)
        driver.find_element_by_link_text("All sales orders").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p9')
        time.sleep(3)          

        print "\n"
        print "step14. hit back"
        driver.back()
	time.sleep(5)

        print "\n"
        print "step15.Click on 'Software subscription and support entitlements' in related links area on the Agreement site information page."
        driver.implicitly_wait(10)
        time.sleep(3)
        #driver.execute_script("window.scrollBy(0,300)","")
        #time.sleep(3)
        driver.find_element_by_link_text("Entitlements").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p10')
        time.sleep(3)
        
        print "\n"
        print "step16. hit back"
        driver.back()
	time.sleep(5)

        print "\n"
        print "step17.Click on the 'Site number' next to 'Agreement number' on the Agreement site information page."
        driver.implicitly_wait(10)
        time.sleep(3)
#        driver.execute_script("window.scrollBy(0,500)","")
        #time.sleep(3)
        driver.find_element_by_link_text("7586162").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p11')
        time.sleep(3)

        print "\n"
        print "step18.Click the 'Agreements' option from the navigation panel."
        driver.implicitly_wait(10)
        time.sleep(3)
        #driver.execute_script("window.scrollBy(0,500)","")
        #time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'Agreements')])[2]").click()
        time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p12')
        time.sleep(3)


        print "\n"
        print "step19.Input 'Name' field with 'ING Insurance' and check mark 'Match name exactly' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
	driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("ING Insurance")
        driver.find_element_by_id("All_sites").click()
	#driver.execute_script("window.scrollBy(0,200)","")
        time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p13')
        time.sleep(3)
        
        print "\n"
        print "step20. hit back"
        driver.get("https://sbybz2239.sby.ibm.com:19543/FastPassS2/agreements.html")
	time.sleep(5)

        print "\n"
        print "step21.Input 'IBM Customer number' field with '401976' and click 'Search'.(Have 'All sites' clicked.  Have all program types checked.)"
	driver.find_element_by_id("IBMcustomer").clear()
        driver.find_element_by_id("IBMcustomer").send_keys("401976")
        driver.find_element_by_id("All_sites").click()
	#driver.execute_script("window.scrollBy(0,200)","")
        time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p14')
        time.sleep(3)

        print "\n"
        print "step22. hit back"
        driver.get("https://sbybz2239.sby.ibm.com:19543/FastPassS2/agreements.html")
	time.sleep(5)

        print "\n"
        print "step23.Input 'Postal code' field with '02169' and click 'Search'."
	driver.find_element_by_id("Postalcode").clear()
        driver.find_element_by_id("Postalcode").send_keys("02169")
        time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p15')
        time.sleep(3)

        print "\n"
        print "step24. hit back"
        driver.get("https://sbybz2239.sby.ibm.com:19543/FastPassS2/agreements.html")
	time.sleep(5)

        print "\n"
        print "step25.Input 'Customer name' field with 'Connectria'.  Have 'Active sites' clicked.  Have all program types checked. Click 'Search'."
	driver.find_element_by_id("name").clear()
        driver.find_element_by_id("name").send_keys("Connectria")
        driver.find_element_by_id("All_sites").click()
	#driver.execute_script("window.scrollBy(0,200)","")
        time.sleep(1)
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("fconly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\image\\','Agreement_MultipleAgreementsMatch_p16')
        time.sleep(3)

	
	print "\n"        
        print "Test Case end with successfully!"    	
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Mobile("Agreement_MultipleAgreementsMatch"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Mobile\\result\\"+now+"FastPass_Test_Case_Agreement_MultipleAgreementsMatch.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Mobile Test Case',description='This is FastPass_Test_Case_Agreement_MultipleAgreementsMatch test case')
    runner.run(testunit)

        
        
