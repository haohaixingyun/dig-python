
def repeat(s,exclaim):
    
    result = s * 3 
    if exclaim:
        result = result + '!!!'
    return result

def main():
    print repeat('Yap', True)
    print repeat('Yap', False)
if __name__ == '__main__':
    main()