#swe 1860

T = int(input())
for test_case in range(1, T + 1):
    
    N, time, make = map(int, input().split())
    customers = sorted(list(map(int, list(input().split())))) #order by desc
    
    is_possible = 'Possible'
    
    for i in range(1, N+1):
        made = int(customers[i - 1] / time) * make #amount of buns made on the time of arrival
        if(made < i):
            is_possible = 'Impossible'
            break
    
    print(f'#{test_case} {is_possible}')