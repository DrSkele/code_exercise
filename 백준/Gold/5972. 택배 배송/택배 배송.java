import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int nBarn;
    static int nRoad;
    static Map<Integer, Integer>[] road;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	
    	nBarn = Integer.parseInt(tokens.nextToken());
    	nRoad = Integer.parseInt(tokens.nextToken());
    	
    	road = new Map[nBarn+1];
    	
    	for(int i = 0; i <= nBarn; i++) {
    		road[i] = new HashMap<>();
    	}
    	
    	for(int i = 0; i < nRoad; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		int cost = Integer.parseInt(tokens.nextToken());
    		
    		if(road[from].containsKey(to)) road[from].put(to, Math.min(road[from].get(to), cost));
    		else road[from].put(to, cost);
    		
    		if(road[to].containsKey(from)) road[to].put(from, Math.min(road[to].get(from), cost));
    		else road[to].put(from, cost);
    	}
    }
    
    static void solve() {
    	
    	PriorityQueue<Path> pq = new PriorityQueue<>();
    	boolean[] visited = new boolean[nBarn+1];
    	
    	pq.add(new Path(1, 0));
    	
    	while(!pq.isEmpty()) {
    		Path cur = pq.poll();
    		
    		if(visited[cur.dest]) continue;
    		visited[cur.dest] = true;
    		
    		if(cur.dest == nBarn) {
    			System.out.println(cur.cost);
    			break;
    		}
    		
    		for(int next : road[cur.dest].keySet()) {
    			if(visited[next]) continue;
    			
    			int nextCost = road[cur.dest].get(next);
    			
    			pq.add(new Path(next, cur.cost + nextCost));
    		}
    	}
    }
    
    static class Path implements Comparable<Path> {
    	int dest;
    	int cost;
    	public Path(int d, int c) {
    		this.dest = d;
    		this.cost = c;
    	}
    	
    	public int compareTo(Path other) {
    		return Integer.compare(cost, other.cost);
    	}
    }
}