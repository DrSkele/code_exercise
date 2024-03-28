from queue import PriorityQueue

class Graph:
    def __init__(self, V):
        self.V = V
        self.E = 0
        self.adj = [[] for _ in range(V + 1)]

    def addEdge(self, v, w, weight):
        self.adj[v].append((w, weight))

    def reverse(g):
        reverse_g = Graph(g.V)

        for v in range(1, g.V + 1):
            for w, weight in g.adj[v]:
                reverse_g.addEdge(w, v, weight)

        return reverse_g

def dijkstra(g, start):
    INF = 1000000000
    pq = PriorityQueue()

    distance = [INF for _ in range(g.V + 1)]
    
    distance[start] = 0
    pq.put((distance[start], start))

    while pq.qsize() > 0: 
        cur_dist, cur_location = pq.get()

        if distance[cur_location] < cur_dist:
            continue

        for next_location, weight in g.adj[cur_location]:
            new_next_dist = distance[cur_location] + weight
            
            if new_next_dist < distance[next_location]:
                distance[next_location] = new_next_dist
                pq.put((distance[next_location], next_location))


    return distance

if __name__ == "__main__":
    n, m, x = map(int, input().split())

    g = Graph(n)

    for _ in range(m):
        v1, v2, weight = map(int, input().split())
        g.addEdge(v1, v2, weight)

    reversed_g = g.reverse()

    come_distance = dijkstra(g, x)
    goback_distane = dijkstra(reversed_g, x)

    total_distance = [0 for _ in range(g.V + 1)]

    max_distance = 0
    for i in range(1, g.V + 1):
        total_distance[i] = come_distance[i] + goback_distane[i]

        if total_distance[i] > max_distance:
            max_distance = total_distance[i]

    print(max_distance)

    