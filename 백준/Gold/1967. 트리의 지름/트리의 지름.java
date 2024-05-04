import java.io.*;
import java.util.*;

// 무방향 그래프
// BFS 모든 지점

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int N;
    static List<Edge>[] map;
    static boolean[] visited;
    static int max;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(in.readLine());
    	map = new ArrayList[N+1];
    	for(int i = 0; i < N+1; i++) {
    		map[i] = new ArrayList<Edge>();
    	}
    	
    	visited = new boolean[N+1];
    	for(int i = 0; i < N-1; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		int cost = Integer.parseInt(tokens.nextToken());
    		
    		map[from].add(new Edge(to, cost));
    		map[to].add(new Edge(from, cost));
    	}
    	
    	max = 0;
    }

    static void solve(){
    	for(int i = 1; i <= N; i++) {
    		visited = new boolean[N+1];
    		visited[i] = true;
    		dfs(i, 0);
    	}
    	System.out.println(max);
    }
    
    static void dfs(int dest, int cost) {
    	for(Edge next : map[dest]) {
    		if(visited[next.dest]) continue;
    		
    		visited[next.dest] = true;
    		dfs(next.dest, cost + next.cost);
    	}
    	max = Math.max(max, cost);
    }
    
    static class Edge{
    	int dest;
    	int cost;
    	public Edge(int dest, int cost) {
    		this.dest = dest;
    		this.cost = cost;
    	}
    }
}
