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


class FastPass_Agile(unittest.TestCase):
    
    def setUp(self):
        self.driver =webdriver.Chrome()
        self.base_url = "https://fpagile.boulder.ibm.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.wait = WebDriverWait(self.driver, 10) # timeout after 10 seconds
        
    def test_Case_FastPassHelp(self):
		
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
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p1')
	time.sleep(3)
	
	    
	###capture screenshots

	print "\n"
	print "step2. Click the 'Help' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/help_home.html")
	time.sleep(1)
        assert 'Help' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p2')       
	time.sleep(3)

	print "\n"
	print "step3. Click the 'User Guide' link."
	driver.find_element_by_xpath("(//a[contains(text(),'User guide')])[1]").click()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass | User guide' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p3')       	
	time.sleep(3)


	print "\n"
	print "step4. Go back."
	driver.back()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass',"The page did not be opened correct"

	print "\n"
	print "step5. Click the 'Getting Started' link."
	driver.find_element_by_xpath("(//a[contains(text(),'Getting started')])[1]").click()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass | Getting started' ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p4')       	
	time.sleep(3)
	
	print "\n"
	print "step6. Go back."
	driver.back()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass',"The page did not be opened correct"
        time.sleep(3)    

	print "\n"
	print "step7. Click the 'Frequently Asked Questions' link."
	driver.find_element_by_xpath("(//a[contains(text(),'Frequently asked questions')])[1]").click()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass | Frequently asked questions' ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p5')       	
	time.sleep(3)


	print "\n"
	print "step8. Go back."
	driver.back()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass',"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step9. Click the 'What's New' link."
	driver.find_element_by_link_text("What's new").click()
	time.sleep(3)
	result = driver.title
	assert result == "FastPass | What's new" ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p6')	
	time.sleep(3)
	
	print "\n"
	print "step10. Go back."
	driver.back()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass',"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step11. Click the 'Help index' link."
	driver.find_element_by_link_text("Help index").click()
	time.sleep(3)
	result = driver.title
	assert result == "FastPass | Help index" ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p7')	
	time.sleep(3)
	
	print "\n"
	print "step12. Go back."
	driver.back()
	time.sleep(3)
	result = driver.title
	assert result == 'FastPass',"The page did not be opened correct"
	time.sleep(3)

	print "\n"
	print "step13. Click the 'User Guide' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/user_guide.html")
	time.sleep(3)
	assert 'The User Guide provides an overview of FastPass and how to use it.' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p8')		
	time.sleep(3)

	print "\n"
	print "step14. Click the 'User Guide' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/getting_started.html")
	time.sleep(3)
	assert 'The topics below are targeted primarily to first-time users.  They cover basic concepts you need to know to begin using FastPass.' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p9')		
	time.sleep(3)


	print "\n"
	print "step15. Click the 'User Guide' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/faq.html")
	time.sleep(3)
	assert 'If you cannot find the answer to your question here, please refer to the User Guide.' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p10')		
	time.sleep(3)


	print "\n"
	print "step16. Click the 'User Guide' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/udr1.html")
	time.sleep(3)
	assert 'The current version of FastPass includes these changes made in recent releases.' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p11')		
	time.sleep(3)
	
	print "\n"
	print "step17. Click the 'User Guide' option from the navigation panel."
	driver.get("https://fpagile.boulder.ibm.com/software/xl/fastpass/agile/fphelp.nsf/html/help_index.html")
	time.sleep(3)
	assert 'Useful Terms, Acronyms and Abbreviations' in driver.page_source ,"The page did not be opened correct"
	C_screenshots.C_screenshots(self,'C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\image\\','FastPassHelp_p12')		
	time.sleep(3)

	

	
	print "\n"        
        print "Test Case end with successfully!"    
        
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)
        


if __name__ == '__main__':
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    testunit=unittest.TestSuite()
    testunit.addTest(FastPass_Agile("test_Case_FastPassHelp"))
    filename="C:\LM_IBM_WORK\LM_WORK\FastPass\FastPass_Agile\\result\\"+now+" FastPass_Test_Case_FastPassHelp.html"
    fp=file(filename,'wb')
    runner = HTMLTestRunner.HTMLTestRunner(stream=fp,title='FastPass_Agile Test Case',description='This is FastPassHelp test case')
    runner.run(testunit)




        

