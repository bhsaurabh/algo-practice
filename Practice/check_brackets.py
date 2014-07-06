#!/usr/bin/python

class Stack(object):
    def __init__(self):
        self.items = []
    
    def isEmpty(self):
        return self.items == []

    def push(self, item):
        # O(1)
        self.items.append(item)  # can be done at position 0 but that is O(N) complexity
    
    def pop(self):
        # O(1)
        return self.items.pop()

def verify(s):
    """
    Checks if brackets are matched
    """
    openers_closers = {'[':']', '{':'}', '(':')'}
    stack = Stack()  # implement this
    openers = openers_closers.keys()
    closers = openers_closers.values()
    for i in range(len(s)):
        ch = s[i]
        if ch in openers:
            # ch is an opening bracket
            # last one opened should be the first one closed
            stack.push(ch)
        elif ch in closers:
            # should match opener on top of stack
            opener = stack.pop()
            if openers_closers[opener] != ch:
                return False
    # stack should be empty
    return stack.isEmpty()

# boiler plate
if __name__ == '__main__':
    # test code
    s = raw_input()
    print(verify(s))