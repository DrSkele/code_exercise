#swe 1244
def recursive_all(cur_num, swap_cnt):
    str_num = ''.join(list(map(str, cur_num))) #int list to string
    
    if(swap_cnt == 0): #finished swapping
        if(cur_num not in result): #filter duplicates
            result.append(str_num)
        return
    
    for i in range(len(cur_num)):
        for j in range(i+1,len(cur_num)): #brute force
            cur_num[i], cur_num[j] = cur_num[j], cur_num[i] #swap
            str_num = ''.join(list(map(str, cur_num))) #int list to string
            if(str_num not in visited[swap_cnt - 1]): #filter duplicates on same level
                visited[swap_cnt - 1].append(str_num)
                recursive_all(cur_num, swap_cnt -1)
            cur_num[i], cur_num[j] = cur_num[j], cur_num[i]
            
                
T = int(input())

for test_case in range(1, T + 1):
    nums, swaps = input().split()
    swaps = int(swaps)
    nums = list(nums)
    result = []
    visited = [[] for i in range(swaps)] #swapping level ex) first swapped elements will be stored in visited[length -1] list
    #important to keep track of visited elements to shorten the execution time
    recursive_all(nums, swaps)
    max_result = max(result) #find maximum value among results
    
    print(f'#{test_case} {max_result}')
           