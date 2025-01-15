def tri_select(A):
    n = len(A)
    for j in range(0, n - 1):
        min = j
        #Trouver min du sous-tableau j+1 ... n
        for i in range(j + 1, n):
            if A[i] < A[min]:
                min = i
            temp = A[j]
            A[j] = A[min]
            A[min] = temp
    return A

print(tri_select([5,4,3,2,1,6,8,1]))