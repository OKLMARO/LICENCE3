def miroir(phrase):
    temp = ""
    for i in range(len(phrase)):
        temp = temp + phrase[(len(phrase) - 1) - i]
    return temp

def entrelacement(L1, L2):
    

miroir("salut")