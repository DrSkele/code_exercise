import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve(in);
	}
	
	static int V;
	static int E;
	static Map<Integer, List<Edge>> map;
	static boolean[] visited;
	static PriorityQueue<Edge> pq;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		map = new HashMap<>();
		visited = new boolean[V];
		pq = new PriorityQueue<>();
		
		for(int i = 0; i < E; i++) {
			tokens = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(tokens.nextToken())-1;
			int v2 = Integer.parseInt(tokens.nextToken())-1;
			int weight = Integer.parseInt(tokens.nextToken());
			
			if(!map.containsKey(v1)) map.put(v1, new ArrayList<Edge>());
			if(!map.containsKey(v2)) map.put(v2, new ArrayList<Edge>());
			
			map.get(v1).add(new Edge(v2, weight));
			map.get(v2).add(new Edge(v1, weight));
		}
	}
	
	static void solve(BufferedReader in) throws IOException {
		visited[0] = true;
		for(Edge e : map.get(0)) {
			pq.add(e);
		}
		int sum = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(visited[cur.v]) continue;
			
			sum += cur.weight;
			visited[cur.v] = true;
			
			for(Edge e : map.get(cur.v)) {
				if(visited[e.v]) continue;
				pq.add(e);
			}
		}
		System.out.println(sum);
	}
	
	static class Edge implements Comparable<Edge>{
		public int v;
		public int weight;
		
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			int result = 0;
			if(weight < e.weight) result = -1;
			else if (weight > e.weight) result = 1;
			return result;
		}
	}
}


