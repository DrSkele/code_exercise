T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # ///////////////////////////////////////////////////////////////////////////////////
    length = int(input())
    buildings = list(map(int, input().split()))
    
    has_view = 0
    
    for num in range(2, length-2):
        neighbor = buildings[num-2 : num] + buildings[num+1:num+3]
        current_building = buildings[num]
        
        if(max(neighbor) > current_building):
            continue
        else:
            highest = max(neighbor)
            has_view += current_building - highest
            
    print(f'#{test_case} {has_view}')
            
        
        