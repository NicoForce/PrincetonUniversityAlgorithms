def connected(p, q, arr):
    return root(p, arr) == root(q, arr)

def rootMod(p, arr):
    init = p
    while(arr[p] != p):
        p = arr[p]
    arr[init] = p
    return p

def root(p, arr):
    while(arr[p] != p):
        arr[p] = arr[arr[p]]
        p = arr[p]
    return p

def union(p, q, arr):
    pRoot = root(p, arr)
    arr[pRoot] = root(q, arr)
    return arr

def main():
    arr = list(range(10))
    arr = union(4, 3, arr)
    arr = union(3, 8, arr)
    arr = union(6, 5, arr)
    arr = union(9, 4, arr)
    arr = union(2, 1, arr)
    arr = union(8, 9, arr)
    
    print(connected(2, 0, arr))
    print(connected(8, 9, arr))

if __name__ == "__main__":
    main()