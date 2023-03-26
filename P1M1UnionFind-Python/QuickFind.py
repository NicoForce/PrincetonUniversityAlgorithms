def connected(p, q, arr):
    return arr[p] == arr[q]

def union(p, q, arr):
    for i, e in enumerate(arr):
        if e == arr[p]:
            arr[i] = arr[q]
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