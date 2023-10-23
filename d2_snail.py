
T = int(input())

for test_case in range(1, T + 1):
    
    size = int(input())
    value_list = [[0 for i in range(0, size)] for i in range(0, size)]
    
    x = 0 #horizontal coordinate
    y = 0 #vertical coordinate
    direction = [0, 1]
    
    for value in range(1, size * size + 1): 
        if(direction == [0, 1]): #to the right
            if(x == size -1 or value_list[y][x + 1] != 0):
                direction = [1, 0] #change direction
            
        elif(direction == [1,0]): #to the bottom
            if(y == size -1 or value_list[y+1][x] != 0):
                direction = [0, -1]
                
        elif(direction == [0, -1]): #to the left
            if (x == 0 or value_list[y][x - 1] != 0):
                direction = [-1, 0]
                
        elif(direction == [-1,0]): #to the top
            if(value_list[y-1][x] != 0):
                direction = [0, 1]
            
        value_list[y][x] = value
        #move the coordinate
        x += direction[1]
        y += direction[0]
            
    print(f'#{test_case}')
    for i in range(0, size):
        for j in range(0, size):
            print(value_list[i][j], end= ' ')
            if(j == size -1):
                print()