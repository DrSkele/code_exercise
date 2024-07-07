import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        int T = Integer.parseInt(in.readLine());
        for(int t = 0; t < T; t++) {
        	init(in);
        	solve();        	
        }
        
    }
     
    static int node;
    static int road;
    static int hole;
    static int[][] map;
    static final int INF = 1_000_000_000;
    static int[] dist;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	node = Integer.parseInt(tokens.nextToken());
    	road = Integer.parseInt(tokens.nextToken());
    	hole = Integer.parseInt(tokens.nextToken());
    	map = new int[node+1][node+1];
    	dist = new int[node+1];
    	for(int i = 0; i < dist.length; i++) {
    		dist[i] = INF;
    	}
    	for(int i = 0; i < node+1; i++) {
    		for(int j = 0; j < node+1; j++) {
    			map[i][j] = INF;
    		}
    	}
    	
    	for(int i = 0; i < road; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		int cost = Integer.parseInt(tokens.nextToken());
    		
    		if(map[from][to] > cost) map[from][to] = cost;
    		if(map[to][from] > cost) map[to][from] = cost;
    	}
    	for(int i = 0; i < hole; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		int cost = -(Integer.parseInt(tokens.nextToken()));
    		
    		if(map[from][to] > cost) map[from][to] = cost;
    	}
    }

    static void solve(){
    	
    	dist[1] = 0;
    	
    	for(int i = 0; i < node-1; i++) {
    		for(int from = 1; from < node+1; from++) {
    			for(int to = 1; to < node+1; to++) {
    				int curCost = map[from][to];
    				if(curCost == INF) continue;
    				
    				int curDist = dist[from];
    				int nextDist = dist[to];
    				
    				if(curDist + curCost < nextDist) {
    					dist[to] = curDist + curCost;
    				}
    			}
    		}
    	}
    	
    	boolean hasLoop = false;
    	Loop : for(int from = 1; from < node+1; from++) {
			for(int to = 1; to < node+1; to++) {
				int curCost = map[from][to];
				if(curCost == INF) continue;
				
				int curDist = dist[from];
				int nextDist = dist[to];
				
				if(curDist + curCost < nextDist) {
					hasLoop = true;
					break Loop;
				}
			}
		}
    	
//    	for(int i = 0; i < dist.length; i++) {
//    		System.out.println(dist[i]);
//    	}
    	
    	System.out.println(hasLoop ? "YES" : "NO");
    }
    
    static class Route{
    	int dest;
    	int cost;
    	public Route(int dest, int cost) {
    		this.dest = dest;
    		this.cost = cost;
    	}
    	
    }
}


