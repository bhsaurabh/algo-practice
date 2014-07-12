#!/usr/bin/python
import sys

def run():
    """
    Maintain a stack and its maximums
    """
    stack = []  # use a list as a stack (modify at end)
    maxs = []   # stack of maximum
    while True:
        ch = input()
        if ch == 0:
            # get maximum
            print(maxs[-1])
        elif ch > 0:
            if len(maxs) == 0 or ch >= maxs[-1]:
                maxs.append(ch)
            stack.append(ch)
        elif ch == -1:
            a = stack.pop(-1)
            if a == maxs[-1]:
                maxs.pop(-1)
    
    

if __name__ == '__main__':
    run()