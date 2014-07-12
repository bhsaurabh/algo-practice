#!/usr/bin/python

class Radio(object):
    def __init__(self):
        self.queue = []  # play queue
        self.stack = []  # stack of played songs
    
    def enqueue(self, song):
        self.queue.append(song)
    
    def play(self):
        if len(self.queue) == 0:
            print('Playlist empty :(')
        else:
            song = self.queue.pop(0)
            print('Playing: ' + song)
            # add song to played stack
            self.stack.append(song)
    
    def previous(self):
        # play previous song
        print('Playing: ' + self.stack[-1])
    
    def skip(self):
        # skip a song
        self.stack.append(self.queue.pop(0))
        self.play()
    
    def run(self):
        # run!
        self.enqueue('Song 1')
        self.enqueue('Song 2')
        self.enqueue('Song 3')
        self.enqueue('Song 4')
        self.enqueue('Song 5')
        self.skip()  # expected: Playing Song 2
        self.previous()  # expected: Playing Song 2
        self.stack.pop(-1)
        self.previous()  # expected: Playing Song 1
        self.play()  # expected: Playing Song 3
        self.skip()  #expected: Playing Song 5
        self.stack.pop(-1)
        self.previous()  #expected: Playing Song 4

if __name__ == '__main__':
    radio = Radio()
    radio.run()