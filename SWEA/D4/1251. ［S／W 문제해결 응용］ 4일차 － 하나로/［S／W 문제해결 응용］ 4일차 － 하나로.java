import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t, in);
		}
	}
	
	static int N;
	static int[] ix;
	static int[] iy;
	static double E;
	static boolean[] visited;
	static void init(BufferedReader in) throws IOException {
		N = Integer.parseInt(in.readLine());
		ix = new int[N];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			ix[i] = Integer.parseInt(tokens.nextToken());
		}
		iy = new int[N];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			iy[i] = Integer.parseInt(tokens.nextToken());
		}
		E = Double.parseDouble(in.readLine());
		visited = new boolean[N];
	}
	
	static void solve(int t, BufferedReader in) throws IOException {
		
		PriorityQueue<Tunnel> pq = new PriorityQueue<>();
		
		double sum = 0;
		pq.add(new Tunnel(0, 0));
		int cnt = 0;
		
		while(!pq.isEmpty() && cnt < N) {
			Tunnel cur = pq.poll();
			if(visited[cur.island]) continue;
			visited[cur.island] = true;
			cnt++;
			sum += cur.cost;
			
			for(int i = 0; i < N; i++) {
				if(visited[i]) continue;
				
				double dist = getDist(cur.island, i);
				double cost = E * (dist * dist);
				
				pq.add(new Tunnel(cost, i));
			}
		}
		System.out.println(String.format("#%d %.0f", t, sum));
	}
	
	static double getDist(int a, int b) {
		return Math.sqrt( Math.pow((ix[a] - ix[b]), 2) + Math.pow((iy[a] - iy[b]),2));
	}
	
	static class Tunnel implements Comparable<Tunnel>{
		double cost;
		int island;
		
		public Tunnel(double cost, int island) {
			this.cost = cost;
			this.island = island;
		}
		
		@Override
		public int compareTo(Tunnel t) {
			if(cost < t.cost) return -1;
			else if(cost > t.cost) return 1;
			return 0;
		}
	}
}
