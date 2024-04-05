
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int city;
	static int bus;
	static Map<Integer, List<Route>> map;
	static int[] cost;
	static int[] prev;
	static int start;
	static int end;
	static void init(BufferedReader in) throws IOException{
		city = Integer.parseInt(in.readLine());
		bus = Integer.parseInt(in.readLine());
		map = new HashMap<>();
		for(int i = 0 ; i< bus; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int origin = Integer.parseInt(tokens.nextToken());
			int dest = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			if(map.containsKey(origin) == false) {
				map.put(origin, new ArrayList<Route>());
			}
			map.get(origin).add(new Route(dest, cost));
		}
		cost = new int[city+1];
		prev = new int[city+1];
		for(int i = 0; i <= city; i++) {
			cost[i] = -1;
		}
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		start = Integer.parseInt(tokens.nextToken());
		end = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve() {
		dijkstra();
		StringBuilder str = new StringBuilder();
		System.out.println(cost[end]);
		int cnt = 1;
		int next = end;
		Stack<Integer> stack = new Stack<>();
		stack.add(next);
		while(next != start) {
			next = prev[next];
			cnt++;
			stack.add(next);
		}
		while(stack.isEmpty() == false) {
			str.append(stack.pop()).append(" ");
		}
		System.out.println(cnt);
		System.out.println(str.toString());
	}
	
	static void dijkstra() {
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
			
			if(map.containsKey(cur.dest) == false) continue;
			
			for(Route r : map.get(cur.dest)) {
				if(0 <= cost[r.dest] && cost[r.dest] <= cost[cur.dest] + r.cost ) continue;
				
				q.add(new Route(r.dest, cost[cur.dest] + r.cost));
				cost[r.dest] = cost[cur.dest] + r.cost;
				prev[r.dest] = cur.dest;
			}
		}
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


