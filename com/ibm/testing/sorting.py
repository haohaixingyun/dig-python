'''
Created on Mar 30, 2016

@author: yunxinghai
'''
def main():
    strs = ['dw','aa', 'BB', 'CC', 'zz']
    print sorted(strs, key=str.lower)
if __name__ == '__main__':
    main()