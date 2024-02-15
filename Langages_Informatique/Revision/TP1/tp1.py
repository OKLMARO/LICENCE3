class Miroir:
    def __init__(self, phrase):
        self.phrase = phrase
    
    def __iter__(self):
        return MiroirIterator(self.phrase)

class MiroirIterator:
    def __init__(self, phrase):
        self.phrase = phrase
        self.index = len(phrase)
    
    def __next__(self):
        if self.index == 0:
            raise StopIteration
        self.index -= 1
        return self.phrase[self.index]

class Entrelacement:
    def __init__(self, phrase1, phrase2):
        self.phrase1 = phrase1
        self.phrase2 = phrase2
    
    def __iter__(self):
        return EntrelacementIterator(self.phrase1, self.phrase2)
    
class EntrelacementIterator:
    def __init__(self, phrase1, phrase2):
        self.phrase1 = phrase1
        self.phrase2 = phrase2
        self.index1 = 0
        self.index2 = 0
        self.token = True
    
    def __next__(self):
        if(self.index1 < len(self.phrase1) and self.index2 < len(self.phrase2)):
            if(self.token):
                self.token = False
                self.index1 += 1
                return self.phrase1[self.index1 - 1]
            else:
                self.token = True
                self.index2 += 1
                return self.phrase2[self.index2 - 1]

        elif(self.index1 == len(self.phrase1) and self.index2 == len(self.phrase2)):
            raise StopIteration
        
        elif(self.index1 < len(self.phrase1)):
            self.index1 += 1
            return self.phrase1[self.index1 - 1]
        
        else:
            self.index2 += 1
            return self.phrase2[self.index2 - 1]