#baekjoon 1012

import sys
sys.stdin = open('input.txt', 'r')

from collections import deque

def bfs(matrix, visited, x, y): #bfs using recursion -> due to the recursion limit(only allows recursive operations to 1000 times), this solution has failed
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if(nx < 0 or nx > width -1 or ny < 0 or ny > height -1 or matrix[ny][nx] == 0):
            continue
        
        if([nx, ny] not in visited):
            visited.append([nx, ny])
            bfs(matrix, visited, nx, ny)
               
def bfs2(matrix,visited, x, y): #bfs without recursion. This way, recursion limit is not a problem
    
    q = deque()
    q.append([x, y])

    while(q):
        node = q.popleft()

        for i in range(4):
            nx = node[0] + dx[i]
            ny = node[1] + dy[i]
        
            if(nx < 0 or nx > width -1 or ny < 0 or ny > height -1 or matrix[ny][nx] == 0):
                continue
            
            next_node = [nx, ny]
            if(next_node not in visited):
                visited.append(next_node)
                q.append(next_node)

T = int(input())

dx = [-1, +1, 0, 0]
dy = [0, 0, -1, +1]

for test_case in range(1, T+1):
    width, height, num = map(int, input().split())
    
    matrix = [[0 for _ in range(width)] for _ in range(height) ]
    visited = []
    root = []
    
    for i in range(num):
        x, y = map(int, input().split())
        matrix[y][x] = 1

    for y in range(height):
        for x in range(width):
            if(matrix[y][x] == 0 or [x, y] in visited):
                continue
            else:
                visited.append([x, y])
                root.append([x, y])
                bfs2(matrix, visited, x, y)
                
    print(len(root))
        