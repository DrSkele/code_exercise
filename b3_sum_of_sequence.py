#swe 2817
def dp(idx, cur_val):
    if(idx == len(nums)): #hitting the end of the list
        return 0
    
    added_val = cur_val + nums[idx]
    
    if(added_val > goal): #sum of the sequence is too big
        return dp(idx+1, cur_val)
    elif(added_val == goal): #one case matching the goal and seek for the next
        return 1 + dp(idx+1, cur_val)
    else:
        return dp(idx+1, cur_val) + dp(idx+1, added_val) #a case without current value and another with current value
    
T = int(input())
for test_case in range(1, T + 1):
    
    N, goal = map(int, input().split())
    nums = list(map(int, input().split()))
    
    print(f'#{test_case} {dp(0, 0)}')
    