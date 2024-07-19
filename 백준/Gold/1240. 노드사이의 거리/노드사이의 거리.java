import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static int vert;
    static int pair;
    static ArrayList<Route>[] tree;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	vert = Integer.parseInt(tokens.nextToken());
    	pair = Integer.parseInt(tokens.nextToken());
    	tree = new ArrayList[vert+1];
    	for(int i = 0; i <= vert; i++) {
    		tree[i] = new ArrayList<>();
    	}
    	for(int i = 0; i < vert-1; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		int dist = Integer.parseInt(tokens.nextToken());
    		
    		tree[from].add(new Route(to, dist));
    		tree[to].add(new Route(from, dist));
    	}
    }

    static void solve(BufferedReader in) throws IOException {
    	
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < pair; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		str.append(getDist(from, to)).append("\n");
    	}
    	System.out.println(str.toString());
    }
    
    static int getDist(int from, int to) {
    	
    	ArrayDeque<Route> q = new ArrayDeque<>();
    	
    	boolean[] visited = new boolean[vert+1];
    	q.add(new Route(from, 0));
    	visited[from] = true;
    	
    	while(!q.isEmpty()) {
    		Route cur = q.poll();
    		
    		if(cur.to == to) return cur.dist;
    		
    		for(Route route : tree[cur.to]) {
    			if(visited[route.to]) continue;
    			
    			visited[route.to] = true;
    			q.add(new Route(route.to, cur.dist + route.dist));
    		}
    	}
    	
    	return 0;
    }
    
    static class Route{
    	int to;
    	int dist;
    	public Route(int to, int dist) {
    		this.to = to;
    		this.dist = dist;
    	}
    }
}


