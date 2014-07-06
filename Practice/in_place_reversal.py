#!/usr/bin/python

def reverse_inplace(s):
    """
    Reverses a list of characters (NOT an immutable string)
    O(n) time and O(1) space
    """
    i, j = 0, len(s)-1
    # repeatedly exchange characters till pointers cross
    while j > i:
        # if j == i, dont bother (its just 1 character)
        s[i], s[j] = s[j], s[i]
        # update the pointers
        i += 1
        j -= 1

if __name__ == '__main__':
    s = list(raw_input().strip())
    reverse_inplace(s)
    print(s)
    