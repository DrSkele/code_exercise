import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();	
	}
	
	static final long inf = 300_000_000_000L;
	
	static int city;
	static int bus;
	static Route[] route;
	static long[] dist;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		city = Integer.parseInt(tokens.nextToken());
		bus = Integer.parseInt(tokens.nextToken());
		route = new Route[bus];
		for(int i = 0; i < bus; i++) {
			tokens = new StringTokenizer(in.readLine());
			int origin = Integer.parseInt(tokens.nextToken())-1;
			int destination = Integer.parseInt(tokens.nextToken())-1;
			int cost = Integer.parseInt(tokens.nextToken());
			route[i] = new Route(origin, destination, cost);
		}
		dist = new long[city];
		dist[0] = 0;
		for(int i = 1; i < city; i++) {
			dist[i] = inf;
		}
	}
	
	public static void solve() {
		for(int i = 0; i < city-1; i++) {
			
			for(int j = 0; j < bus; j++) {
				Route cur = route[j];
				if(dist[cur.origin] >= inf) continue;
				if(dist[cur.destination] > dist[cur.origin] + cur.cost) {
					dist[cur.destination] = dist[cur.origin] + cur.cost;
				}
			}
		}
		
		boolean hasLoop = false;
		for(int j = 0; j < bus; j++) {
			Route cur = route[j];
			if(dist[cur.origin] >= inf) continue;
			if(dist[cur.destination] > dist[cur.origin] + cur.cost) {
				hasLoop = true;
				break;
			}
		}
		
		if(hasLoop) {
			System.out.println(-1);
		} else {
			StringBuilder str = new StringBuilder();
			for(int i = 1; i < city; i++) {
				long path = dist[i] < inf ? dist[i] : -1;
				str.append(path).append("\n");
			}
			System.out.println(str.toString());
		}
	}
	
	static class Route{
		int origin;
		int destination;
		int cost;
		public Route(int origin, int destination, int cost) {
			this.origin = origin;
			this.destination = destination;
			this.cost = cost;
		}
	}
}

