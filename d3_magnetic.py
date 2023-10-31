#swe 1220
T = 10

for test_case in range(1, T + 1):
     
    size = int(input())
    matrix = []
     
    for i in range(size):
        matrix.append(list(input().split()))
     
    cnt = 0
     
    for i in range(size):
        last = '0'
         
        for j in range(size):
            current = matrix[j][i]
            if(current == '1'):
                last = current
            elif(current == '2' and last == '1'): #1->2 change check
                cnt += 1
                last = current
                 
    print(f'#{test_case} {cnt}')