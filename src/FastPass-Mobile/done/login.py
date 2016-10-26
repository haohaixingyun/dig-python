# coding = utf - 8
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
import time,unittest

def login(self,var):
    driver = self.driver
    
    time.sleep(1)
    if var == 'Agreements':
        driver.find_element_by_link_text("Agreements").click()
    elif var == 'Entitlements':
        driver.find_element_by_link_text("Entitlements").click()
    elif var == 'Sales orders':
        driver.find_element_by_link_text("Sales orders").click()
    else:
        driver.find_element_by_link_text("Customers").click()

    time.sleep(2)
    driver.find_element_by_name("j_username").clear()
    driver.find_element_by_name("j_username").send_keys("liumlm@cn.ibm.com")
    driver.find_element_by_name("j_password").clear()
    driver.find_element_by_name("j_password").send_keys("LM201608")
    time.sleep(3)
