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
	static ArrayList<Edge> edges;
	static int[] leader;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		edges = new ArrayList<Edge>();
		leader = new int[V];
		for(int i = 0; i < V; i++) {
			leader[i] = i;
		}
		for(int i = 0; i < E; i++) {
			tokens = new StringTokenizer(in.readLine());
			edges.add(new Edge(
					Integer.parseInt(tokens.nextToken())-1,
					Integer.parseInt(tokens.nextToken())-1,
					Integer.parseInt(tokens.nextToken()))
					);
		}
		Collections.sort(edges);
	}
	
	static void solve(BufferedReader in) throws IOException {
		int sum = 0;
		for(int i = 0; i < E; i++) {
			Edge cur = edges.get(i);
			
			int leader1 = find(cur.v1);
			int leader2 = find(cur.v2);
			
			if(leader1 == leader2) continue;
			
			union(leader1, leader2);
			sum += cur.weight;
		}
		System.out.println(sum);
	}
	
	static int find(int a) {
		if(leader[a] == a) return a;
		
		return leader[a] = find(leader[a]);
	}
	
	static void union(int a, int b) {
		int leaderA = find(a);
		int leaderB = find(b);
		
		leader[leaderB] = leaderA;
	}
	
	static class Edge implements Comparable<Edge>{
		public int v1;
		public int v2;
		public int weight;
		
		public Edge(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			int result = 0;
			if(weight < e.weight) result = -1;
			else if(weight > e.weight) result = 1;
			return result;
		}
	}
}


