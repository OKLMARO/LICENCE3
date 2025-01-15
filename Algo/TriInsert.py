def TriInsert(A):
    #Les indices du tableau A sont de 1 à A.taille = n
    n = len(A)
    for j in range(1, n):
        key = A[j]
        #A[j] dans le sous-tableau trié A[1] ... A[j-1]
        i = j - 1
        while i >= 0 and A[i] > key:
            A[i + 1] = A[i]
            i = i - 1
        A[i + 1] = key
    return A

print(TriInsert([5,4,3,2,1,6,8,1]))