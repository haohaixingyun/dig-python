'''
Created on Mar 30, 2016

@author: yunxinghai
'''
import re



def main():
    strs = 'what are you doing now ,got you'
    matchs = re.search(r'doing \w\w\w',strs)
    if matchs :
        print 'found',matchs.group()
    else:
        print 'nothing'    
    print re.search(r'iii', 'piiig') .group()
    
    print re.search(r'\d\d\d', 'p123g').group()
    
    print re.search(r'\w\w\w', '@@abcd!!').group()
    
    print re.search(r'..g', 'piiig').group()
    
    
    
if __name__ == '__main__':
    main()