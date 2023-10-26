#swe 2805

T = int(input())

for test_case in range(1, T + 1):
    
    size = int(input())
    farm = []
    for i in range(size):
        farm.append(list(map(int, list(input()))))
    
    value = 0
    mid = size // 2 #middle value
    
    for i in range(size):
        for j in range(size):
            if(abs(mid - i) <= j and j < size - abs(mid - i)): #diamond shape
                value += farm[i][j]
            
    print(f'#{test_case} {value}')
