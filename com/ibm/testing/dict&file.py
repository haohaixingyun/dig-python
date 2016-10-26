'''
Created on Mar 30, 2016

@author: yunxinghai
'''
def main():
    dicts = {}
    dicts['a'] = 'alpha'
    dicts['g'] = 'gamma'
    dicts['o'] = 'omega'

    print dicts 
    print dicts.keys()
    
    if 'a' in dicts:
        print dicts['a']
        
    for key in dicts :
        print key 
        print dicts[key]
    f = open('C:\workplacebus\\business\\yunxinghai_Ethan\\PROD_DIMNSN\\rshr1.prod_dimnsn_debug.tbl.sql','rU')
    for line in f:
        print line 
    f.close()    
if __name__ == '__main__':
    main()