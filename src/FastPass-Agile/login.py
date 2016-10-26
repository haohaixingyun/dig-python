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
    driver.find_element_by_name("Username").clear()
    driver.find_element_by_name("Username").send_keys("XXXXX")
    driver.find_element_by_name("Password").clear()
    driver.find_element_by_name("Password").send_keys("XXXXX")
    time.sleep(3)
