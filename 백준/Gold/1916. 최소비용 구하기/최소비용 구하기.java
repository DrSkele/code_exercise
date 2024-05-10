import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		init();
		solve();
	}
	
	static long inf = 100_000_000_000L;
	static int cities;
	static int buses;
	static ArrayList<Route>[] path;
	static long[] cost;
	static int start;
	static int end;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		cities = Integer.parseInt(in.readLine());
		buses = Integer.parseInt(in.readLine());
		
		path = new ArrayList[cities+1];
		cost = new long[cities+1];
		for(int i = 0; i < cities+1; i++) {
			path[i] = new ArrayList<>();
			cost[i] = inf;
		}
		StringTokenizer tokens;
		for(int i = 0; i < buses; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			
			path[from].add(new Route(to, cost));
		}
		tokens = new StringTokenizer(in.readLine());
		start = Integer.parseInt(tokens.nextToken());
		end = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve() {
		
		PriorityQueue<Route> q = new PriorityQueue<Route>(new Comparator<Route>() {
			@Override
			public int compare(Route r1, Route r2) {
				if(r1.cost < r2.cost) return -1;
				else if(r1.cost > r2.cost) return 1;
				else return 0;
			}
		});
		
		q.add(new Route(start, 0));
		cost[start] = 0; 
		
		while(!q.isEmpty()) {
			Route cur = q.poll();
			
			if(cur.dest == end) break;
			
			for(Route next : path[cur.dest]) {
				if(cost[next.dest] <= cur.cost + next.cost) continue;
				
				q.add(new Route(next.dest, cur.cost + next.cost));
				cost[next.dest] = cur.cost + next.cost;
			}
		}
		
		System.out.println(cost[end]);
	}
	
	static class Route{
		int dest;
		long cost;
		public Route(int dest, long cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
