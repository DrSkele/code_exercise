#swe 2930
def add_node(node): #add new node
    heap.append(node) #at first, add the node to the end of the heap
    idx = len(heap)-1 #current location of pivot
    parent_idx = idx//2 #idx of parent node
    
    if(parent_idx < 1): #has no parent node
        return
    
    #swap with parent node with greater value
    while(heap[parent_idx] < heap[idx]):
        heap[parent_idx], heap[idx] = heap[idx], heap[parent_idx] #swap
        
        idx = parent_idx
        parent_idx = idx//2
        if(parent_idx < 1):
            return

def child_idx(idx):#get index of largest child
    if(idx*2+1 < len(heap)): #has two child
        left = heap[idx*2]
        right = heap[idx*2+1]
        return idx*2 if left >= right else idx*2+1 #compare and return the bigger one
    elif(idx*2 < len(heap)):
        return idx*2 #only has one child
    else:
        return idx #does not have child

def remove_node():
    
    if(len(heap) <= 1): #empty heap
        return -1
    elif(len(heap) == 2): #has only one node
        return heap.pop()
    
    idx = 1
    root = heap[idx] #extract the root node
    last_node = heap.pop() 
    heap[idx] = last_node #replace the root node with the end node
    
    while(True):
        child = child_idx(idx) #get the index of child to compare
        
        if(heap[idx] < heap[child]): #swap with the child with greater value
            heap[idx], heap[child] = heap[child], heap[idx]
            idx = child #move the pivot to the child and continue
        else:
            break
            
    return root


T = int(input())
for test_case in range(1, T + 1):
    
    cnt = int(input())
    heap = [ 0 ] #an empty value to make the index of the first node to 1
    result = []
    
    for i in range(cnt):
        cmd = list(map(int, list(input().split())))
        if(cmd[0] == 1):
            add_node(cmd[1])
        else:
            result.append(remove_node())
    
    answer = ' '.join(map(str, result))
    print(f'#{test_case} {answer}')
    