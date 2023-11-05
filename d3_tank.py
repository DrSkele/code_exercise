#swe 1873

def move(matrix, x_from, y_from, x_to, y_to, direction):
    if(y_to < 0 or y_to >= H or x_to < 0 or x_to >= W):
        matrix[y_from][x_from] = direction
        return False
    
    if(matrix[y_to][x_to] == '.'):
        matrix[y_to][x_to] = direction
        matrix[y_from][x_from] = '.'
        return True
    else:
        matrix[y_from][x_from] = direction
        return False

def is_hit(matrix, x, y):
    if(matrix[y][x] == '#'):
        return True
    elif(matrix[y][x] == '*'):
        matrix[y][x] = '.'
        return True
    return False
        
def command(matrix, x, y, cmd):
    if(cmd == 'U'):
        if(move(matrix, x, y, x, y-1, '^')):
            return x, y-1
    elif(cmd == 'D'):
        if(move(matrix, x, y, x, y+1, 'v')):
            return x, y+1
    elif(cmd == 'L'):
        if(move(matrix, x, y, x-1, y, '<')):
            return x-1, y
    elif(cmd == 'R'):
        if(move(matrix, x, y, x+1, y, '>')):
            return x+1, y
    else:
        tank = matrix[y][x]
        if(tank == '^'):
            if(y - 1 >= 0):
                for i in range(y-1, -1, -1):
                    if(is_hit(matrix, x, i)):
                        break
        elif(tank == 'v'):
            if(y + 1 < H):
                for i in range(y+1, H):
                    if(is_hit(matrix, x, i)):
                        break
        elif(tank == '<'):
            if(x - 1 >= 0):
                for i in range(x-1, -1, -1):
                    if(is_hit(matrix, i, y)):
                        break
        elif(tank == '>'):
            if(x + 1 < W):
                for i in range(x+1, W):
                    if(is_hit(matrix, i, y)):
                        break
    return x, y
            
def find_tank(matrix):
    for i in range(H):
        for j in range(W):
            cur = matrix[i][j]
            if(cur == '^' or cur == 'v' or cur == '<' or cur =='>'):
                return j, i

T = int(input())
for test_case in range(1, T + 1):
    
    H, W = map(int, input().split())
    matrix = []
    for _ in range(H):
        matrix.append(list(input()))
    cnt = int(input())
    cmd = input()

    x, y = find_tank(matrix)
    
    for i in range(len(cmd)):
        x, y = command(matrix, x, y, cmd[i])
    
    answer = ''
    for i in range(H):
        answer += ''.join(matrix[i])
        if(i < H-1):
            answer += '\n'
    print(f'#{test_case} {answer}')