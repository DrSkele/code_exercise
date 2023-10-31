#swe 1225

'''
    12345000
    45000123
    00123450
    23450001
    50001234
    01234500
    34500012
    00012345
'''
 
from collections import deque
 
def cycle(nums):
    is_zero = False
    cut = 1
     
    dq = deque(nums)
     
    while(not is_zero):
        temp = dq.popleft() - cut
        if(temp <= 0):
            is_zero = True
            dq.append(0)
            break
        else :
            cut += 1
            if(cut > 5):
                cut = 1
            dq.append(temp)
      
    return list(dq)
 
T = 10
for test_case in range(1, T + 1):
    n = int(input())
    nums = list(map(int, list(input().split())))
     
    cnts = []
     
    for i in nums:
        division = int(i / 15) 
        if(division > 0):
            division -= 1
         
        cnts.append(division)
         
    minimum = min(cnts)
    nums = [i - 15*minimum for i in nums] #reduce the number to minimum. this will minimize the loop for the cycle process
     
    result = ' '.join(map(str, cycle(nums))) #cycle through the process
    print(f'#{test_case} {result}')