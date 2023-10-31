#swe 2814
def dfs(graph, visited, node, N):
    if(node in visited): #dead end
        return N
    else:
        value = 0
        for new_node in graph[node]:
            visited.append(node)
            result = dfs(graph, visited, new_node, N + 1) #returns the length of the path
            visited.remove(node)
            if(result > value): #if it's the longest path
                value = result
        return value
         
T = int(input())
for test_case in range(1, T + 1):
     
    N, M = map(int, input().split())
    graph = {} 
     
    for i in range(M):
        x, y = map(int, input().split()) #the path does not have direction, thus x->y and y->x both has to be added to the graph
        if(x in graph): #add x as key and y as value
            if(y not in graph[x]):
                graph[x] = graph[x] + [y]
        else:
            graph[x] = [y]
             
        if(y in graph): #add y as key and x as value
            if(x not in graph[y]):
                graph[y] = graph[y] + [x]
        else:
            graph[y] = [x]
     
    answer = 1
    for i in graph:
        visited = []
        result = dfs(graph, visited, i, 0) #returns the longest path value
        if(result > answer):
            answer = result
    print(f'#{test_case} {answer}')