import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int N;
    static List<Edge>[] map;
    static boolean[] visited;
    static int max;
    static int maxDest;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(in.readLine());
    	map = new ArrayList[N+1];
    	for(int i = 0; i < N+1; i++) {
    		map[i] = new ArrayList<Edge>();
    	}
    	
    	visited = new boolean[N+1];
    	for(int i = 0; i < N; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		
    		int from = Integer.parseInt(tokens.nextToken());
    		int to;
    		while((to = Integer.parseInt(tokens.nextToken())) != -1){
    			int cost = Integer.parseInt(tokens.nextToken());
    			map[from].add(new Edge(to, cost));
    		}
    	}
    	
    	max = 0;
    	maxDest = 0;
    }

    static void solve(){
    	visited = new boolean[N+1];
    	dfs(1, 0);
    	
    	visited = new boolean[N+1];
    	dfs(maxDest, 0);
    	
    	System.out.println(max);
    }
    
    static void dfs(int dest, int cost) {
    	if(cost > max) {
    		max = cost;
    		maxDest = dest;
    	}
    	visited[dest] = true;
    	for(Edge next : map[dest]) {
    		if(visited[next.dest]) continue;
    		
    		dfs(next.dest, cost + next.cost);
    	}
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
