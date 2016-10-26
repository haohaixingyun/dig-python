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
        
    def test_Case_SaasSolution(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p1')
	
	    
	###capture screenshots
	
	
	print "\n"
	print "step2.login"
	login.login(self,'Agreements')
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p2')
	driver.find_element_by_name("submit").click()
	driver.implicitly_wait(10)

	print "\n"
	print "step3.Input Agreement number 0000208050."
	driver.find_element_by_id("Agreement").clear()
	driver.find_element_by_id("Agreement").send_keys("0000208050")
	driver.find_element_by_id("All_sites").click()
	time.sleep(1)
	#driver.execute_script("window.scrollBy(0,200)","")
	driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
	driver.find_element_by_id("spponly").click()
	driver.find_element_by_name("ibm-submit").submit()
	driver.implicitly_wait(10)
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider - End user' in driver.page_source , "The data is not avalible"
        assert 'SaaS Solution Provider - Provider' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p3')
        time.sleep(3)

	print "\n"
	print "step4.Click the link of customer name (Etisalat Communications Corp ) "
	driver.implicitly_wait(10)
	driver.find_element_by_link_text("Etisalat Communications Corp").click()
	#wait.until(lambda the_driver: the_driver.find_element_by_id('dropList1').is_displayed())
	#menu = driver.find_element_by_link_text("All sites")
        #driver.implicitly_wait(10)
        time.sleep(3)
	#ActionChains(driver).move_to_element(menu).perform()
	#driver.implicitly_wait(10)
	#driver.find_element_by_xpath("//input[@name='ibm-go']").click
	#driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fastpass.nsf/salesorders?openform")	
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"
        assert 'SaaS Solution Provider' in driver.page_source , "The data is not avalible"
        assert 'End user' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p4')
	time.sleep(3)

	print "\n"
	print "step5.click the link of Agreement_num (208050)."
	driver.implicitly_wait(10)
	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(3)
	driver.find_element_by_link_text("208050").click()
	#driver.execute_script("window.scrollBy(0,200)","")
	#driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p5')
        time.sleep(3)
	
        print "\n"
	print "step6.go back."
	driver.back()
	driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
       

	print "\n"
	print "step7.click link of site number (7044053)"
	driver.implicitly_wait(10)
#	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(3)
	driver.find_element_by_link_text("7044053").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider providers' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p6')
        time.sleep(5)

	print "\n"
	print "step8.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"        
        print "step9.Click on 'Contacts' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
	driver.execute_script("window.scroll(0,0)","")
        time.sleep(5)
        driver.find_element_by_link_text("Contacts").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Contact information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p7')
        time.sleep(3)

	print "\n"
	print "step10.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step11.Click on 'Current sales order' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Current sales orders").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p8')
        time.sleep(3)

	print "\n"
	print "step12.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)

        print "\n"
        print "step13.Click on 'All sales orders' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("All sales orders").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p9')
        time.sleep(3)

	print "\n"
	print "step14.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step15.Click on 'Entitlements' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Entitlements").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p10')

	print "\n"
	print "step16.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step17.Click on 'Services agreements' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Services agreements").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p11')

	print "\n"
	print "step18.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
        driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)        
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step19.Click the link of customer name( 'Gulf Business Machine BSC')."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Gulf Business Machine BSC").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider' in driver.page_source , "The data is not avalible"
        assert 'Provider' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p12')

	print "\n"
	print "step20.click the link of Agreement_num (208050)."
	driver.implicitly_wait(10)
	driver.execute_script("window.scrollBy(0,300)","")
	time.sleep(3)
	driver.find_element_by_link_text("208050").click()
	#driver.execute_script("window.scrollBy(0,200)","")
	#driver.execute_script("window.scrollBy(0,document.body.scrollHeight)","")
	time.sleep(3)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p13')
        time.sleep(3)
	
        print "\n"
	print "step21.go back."
	driver.back()
	driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
       

	print "\n"
	print "step22.click link of site number (3583726)"
	driver.implicitly_wait(10)
#	driver.execute_script("window.scrollBy(0,300)","")
        time.sleep(3)
	driver.find_element_by_link_text("3583726").click()
	time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Customers - Customer details' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider provider' in driver.page_source , "The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p14')
        time.sleep(5)

	print "\n"
	print "step23.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)

        print "\n"        
        print "step24.Click on 'Contacts' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
	driver.execute_script("window.scroll(0,0)","")
        time.sleep(5)
        driver.find_element_by_link_text("Contacts").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Customers - Contact information' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p15')
        time.sleep(3)

	print "\n"
	print "step25.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)

        print "\n"
        print "step26.Click on 'Current sales order' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Current sales orders").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p16')
        time.sleep(3)

	print "\n"
	print "step27.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)

        print "\n"
        print "step28.Click on 'All sales orders' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("All sales orders").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p17')
        time.sleep(3)

	print "\n"
	print "step29.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)

        print "\n"
        print "step30.Click on 'Entitlements' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Entitlements").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Entitlements - Customer site entitlement information - Default sort by customer name' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p18')

	print "\n"
	print "step31.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(5)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement site information',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step32.Click on 'Services agreements' in related links area on the Agreement site information page."
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_link_text("Services agreements").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p19')
        
	print "\n"
	print "step33.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
        driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)        
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name',"The page did not be opened correct"        
        time.sleep(5)
        
        
        print "\n"
        print "step34.Click tag208050_link"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'208050')])[1]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Agreements - Agreement details' ,"The page did not be opened correct"
#        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible" 
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p20')        
       
        print "\n"
        print "step35.Click End user  View"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("//a[@href='./SAPIDServsAgrmt?openagent&agree_num=0000208050&program=SP&ibm_cust_num=000115&cust_type=ENDUSRCUST&cust_num=0007044053']").click()
        time.sleep(8)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"
        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible"
        assert 'End user' in driver.page_souce ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p21')

	print "\n"
	print "step36.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step37.Click current View link"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'Current view ')])[1]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"        
        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible"
        assert 'End user' in driver.page_souce ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p22')
        

	print "\n"
	print "step38.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step39.Click All View link"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'All view')])[1]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"        
        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible"
        assert 'End user' in driver.page_souce ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p23')


	print "\n"
	print "step40.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step41.Click provider View link"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("//a[@href='./SAPIDServsAgrmt?openagent&agree_num=0000208050&program=SP&ibm_cust_num=565046&cust_type=PROVIDER&cust_num=0003583726']").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Services agreements by site' ,"The page did not be opened correct"        
        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible"
        assert 'Provider' in driver.page_souce ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p24')
        
	print "\n"
	print "step42.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details',"The page did not be opened correct"        
        time.sleep(5)

        print "\n"
        print "step43.Click current View link"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'Current view ')])[4]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Current sales order' ,"The page did not be opened correct"        
        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible"
        assert 'Provider' in driver.page_souce ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p25')
        

	print "\n"
	print "step44.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement details',"The page did not be opened correct"        
        time.sleep(5)
        
        print "\n"
        print "step45.Click All View link"
	driver.implicitly_wait(10)
	time.sleep(3)
        driver.find_element_by_xpath("(//a[contains(text(),'All view')])[4]").click()
        time.sleep(5)
        result = driver.title
        assert result == 'FastPass | Sales orders - Site historical information - Sorted by purchase order date' ,"The page did not be opened correct"        
        assert 'SaaS Solution Provider' in driver.page_souce ,"The data is not avalible"
        assert 'Provider' in driver.page_souce ,"The data is not avalible"
        C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','SaasSolution_p26')

	print "\n"
	print "step46.Go back"
	driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
        driver.back()
        driver.implicitly_wait(10)
        time.sleep(3)
	result = driver.title
        assert result == 'FastPass | Agreements - Agreement search information - Default sort by customer name',"The page did not be opened correct"        
        time.sleep(5)        
        
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_SaasSolution"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_SaasSolution.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is SaasSolution test case')
    runner.run(testunit)




        

