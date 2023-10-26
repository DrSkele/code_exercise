#swe 1289
T = int(input())
 
for test_case in range(1, T + 1):
     
    memory = input()
    last = '0'
    cnt = 0
     
    for i in range(len(memory)):
        if(memory[i] != last): #check for value change
            cnt += 1
            last = memory[i]
             
    print(f'#{test_case} {cnt}')