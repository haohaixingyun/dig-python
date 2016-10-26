'''
Created on Mar 30, 2016

@author: yunxinghai
'''

def main():
    Squ  = [1,2,3,45,6,8]
    sums = 0
    lists = ['larry', 'curly', 'moe']
    for var in Squ:
        sums +=var
    print sums
    
    
    if 'curly' in lists:
        print 'yay'
        
    for i in range(10):
        print i 
     
    j = 0    
    while j < 100:
        print j
        j = j +  1
        
        
    

if __name__ == '__main__':
    main()