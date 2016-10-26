# coding = utf - 8
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException
import time,unittest

def C_screenshots(self,p,a):
    driver = self.driver
    now = time.strftime("%Y-%m-%d-%H_%M_%S", time.localtime(time.time()))
    pic_path=p + now + a + '.png'
#    pic_path='C:\Users\IBM_ADMIN\Desktop\FastPass_Uat\\result\\image\\'+now+'1.png'
    print pic_path
    
    driver.execute_script("""
       (function () {
            var y = 0;
            var step = 100;
            window.scroll(0, 0);
 
            function f() {
                if (y < document.body.scrollHeight) {
                    y += step;
                    window.scroll(0, y);
                    setTimeout(f, 50);
                } else {
                    window.scroll(0, 0);
                    document.title += "scroll-done";
                }
            }
 
            setTimeout(f, 1000);
        })();
    """)
 
    for i in xrange(30):
        if "scroll-done" in driver.title:
            break
        time.sleep(1)

    driver.save_screenshot(pic_path)
    time.sleep(2)
    
    
