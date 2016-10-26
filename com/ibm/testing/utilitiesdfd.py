
import urllib2


def main():

    requests  = urllib2.Request('http://www.baidu.com')
    try:
        
        ufiles = urllib2.urlopen(requests)
    except  urllib2.URLError, e:
        print e.reason()
    print ufiles.read()


if __name__ == '__main__':
    main()