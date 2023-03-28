def connected(p, q, arr):
    return root(p, arr) == root(q, arr)

def root(p, arr):
    while(arr[p] != p):
        p = arr[p]
    return p

def union(p, q, arr, size):
    pRoot = root(p, arr)
    qRoot = root(q, arr)
    if pRoot == qRoot: return arr
    if size[p] > size[q]:
        arr[qRoot] = pRoot
        size[p] += size[q]
    else:
        arr[pRoot] = qRoot
        size[q] += size[p]
    return arr

def main():
    treeSize = 10
    arr = list(range(treeSize))
    size = [1] * treeSize
    arr = union(4, 3, arr, size)
    arr = union(3, 8, arr, size)
    arr = union(6, 5, arr, size)
    arr = union(9, 4, arr, size)
    arr = union(2, 1, arr, size)
    arr = union(8, 9, arr, size)
    
    print(connected(2, 0, arr))
    print(connected(8, 9, arr))

if __name__ == "__main__":
    main()