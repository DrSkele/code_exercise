import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nCity;
	static int nRoad;
	static int nPave;
	static ArrayList<Road>[] map;
	static long[][] minCost;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		nCity = Integer.parseInt(tokens.nextToken());
		nRoad = Integer.parseInt(tokens.nextToken());
		nPave = Integer.parseInt(tokens.nextToken());
		
		map = new ArrayList[nCity+1];
		for(int i = 1; i < nCity+1; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < nRoad; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			
			map[from].add(new Road(to, cost));
			map[to].add(new Road(from, cost));
		}
		
		minCost = new long[nPave+1][nCity+1];
		for(int i = 0; i < nPave+1; i++) {
			for(int j = 0; j < nCity+1; j++) {
				minCost[i][j] = Long.MAX_VALUE;
			}
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		djikstra();
		
		long min = Long.MAX_VALUE;
		
		for(int i = 0; i < nPave+1; i++) {
			min = Math.min(min, minCost[i][nCity]);
		}
		
		System.out.println(min);
	}
	
	public static void djikstra() {
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		pq.add(new Path(1, 0, 0));
		
		while(!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if(minCost[cur.paved][cur.dest] <= cur.cost) continue;
			else minCost[cur.paved][cur.dest] = cur.cost;
			
			for(Road next : map[cur.dest]) {
				if(cur.cost + next.cost < minCost[cur.paved][next.dest]) {
					pq.add(new Path(next.dest, cur.cost + next.cost, cur.paved));
				}
				
				if(cur.paved < nPave && cur.cost < minCost[cur.paved+1][next.dest]) {
					pq.add(new Path(next.dest, cur.cost, cur.paved+1));
				}
			}
		}
	}
	
	static class Road{
		int dest;
		long cost;
		public Road(int d, long c) {
			dest = d;
			cost = c;
		}
	}
	
	static class Path extends Road implements Comparable<Path>{
		int paved;
		public Path(int d, long c, int p) {
			super(d, c);
			paved = p;
		}
		
		public int compareTo(Path p) {
			if(cost < p.cost) return -1;
			else if(cost > p.cost) return 1;
			else return 0;
		}
	}
}
