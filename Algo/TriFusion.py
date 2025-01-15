def tri_fusion(A, p, d):
    if p < d:
        m = (p + d) / 2
        tri_fusion(A, p, m)
        tri_fusion(A, m + 1, d)
        fusion(A, p, m, d)
    return A

def fusion(A, p, m, d):
    n1 = m - p + 1
    n2 = d - m
    L = L[n1]
    R = R[n2]
    for i in range(n1):
        L[i] = A[p + i - 1]
    for j in range(n2):
        R[j] = A[m + j]
    
    i = 0
    j = 0

    for k in range(p, d):
        if L[i] <= R[j]:
            A[k] = L[i]
            i = i + 1
        else:
            A[k] = R[j]
            j = j + 1
    return A

print(tri_fusion([5,4,3,2,1,6,8,1], 0, 8))
