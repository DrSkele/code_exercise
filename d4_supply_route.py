#swe 1249
from collections import deque

def bfs(graph, graph_size, root_x, root_y): #breadth first search
    directions = [[0,1],[1,0],[0, -1],[-1,0]] 
    visited = [[1e8 for i in range(graph_size)] for j in range(graph_size)] #goal is to find the minimum cost, thus not visited nodes are initialized to big numbers
    
    coordinates = deque()
    coordinates.append([root_x, root_y])
    visited[root_x][root_y] = graph[root_x][root_y]
    
    while coordinates:
        
        cur_x, cur_y = coordinates.popleft()
        
        for next_dir in directions:
            next_x = cur_x + next_dir[0]
            next_y = cur_y + next_dir[1]
            
            if(next_x < 0 or next_x >= graph_size or next_y < 0 or next_y >= graph_size): #outside of the matrix size
                continue
                
            next_sum = visited[cur_x][cur_y] + graph[next_x][next_y] 
            if(next_sum < visited[next_x][next_y]): #if current path is minimum number
                visited[next_x][next_y] = next_sum
                coordinates.append([next_x, next_y])
            
    return visited[graph_size-1][graph_size-1] #return the end node(exit)
        


T = int(input())
for test_case in range(1, T + 1):

    n = int(input())
    graph = [list(map(int, list(input()))) for _ in range(n)]
    
    print(f'#{test_case} {bfs(graph, n, 0, 0)}')
