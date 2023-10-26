#swe 2806
def isValid(visited, xy): #check if the queen can be placed
    for queen in visited:
        if(queen[0] == xy[0] or queen[1] == xy[1] or abs(queen[0] - xy[0]) == abs(queen[1] - xy[1])):
            return False
    return True

def dfs(rowNum):
    if(rowNum < 0): #last row
        placed.append(len(queens))
        return
    
    for i in range(N):
        coord = [rowNum, i]
        if(isValid(queens, coord)):
            queens.append(coord)
            dfs(rowNum-1)
            queens.pop()
        
T = int(input())
for test_case in range(1, T + 1):
    
    N = int(input())
    
    queens = []
    placed = []
    if(N > 1):
        dfs(N-1)
    else:
        placed.append(1)
    
    print(f'#{test_case} {len(placed)}')
    