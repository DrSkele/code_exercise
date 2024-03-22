import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();
	}
	
	static int V;
	static int E;
	static int start;
	static Map<Integer, List<Path>> map;
	static int[] visited;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		start = Integer.parseInt(in.readLine());
		map = new HashMap<>();
		visited = new int[V+1];
		for(int i = 0; i <= V; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < E; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());
			
			if(!map.containsKey(from)) {
				map.put(from, new ArrayList<>());
			}
			
			map.get(from).add(new Path(to, weight));
		}
	}
	
	public static void solve() {
		
		dijkstra(start);

		StringBuilder str = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			int val = visited[i];
			str.append(val == Integer.MAX_VALUE ? "INF" : val).append("\n");
		}
		System.out.println(str.toString());
	}
	
	public static void dijkstra(int start) {
		
		PriorityQueue<Path> pq = new PriorityQueue<Path>(new Comparator<Path>() {
			@Override
			public int compare(Path p1, Path p2) {
				if(p1.weight < p2.weight) return -1;
				else if(p1.weight > p2.weight) return 1;
				else return 0;
			}
		});
		
		pq.add(new Path(start, 0));
		visited[start] = 0;
		
		while(!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if(!map.containsKey(cur.end)) continue;
            if(visited[cur.end] < cur.weight) continue;
			
			for(Path path : map.get(cur.end)) {
				int newWeight = visited[cur.end] + path.weight;
				if(visited[path.end] <= newWeight) continue;
				
				pq.add(new Path(path.end, newWeight));
				visited[path.end] = newWeight;
			}
		}
		
	}
	static class Path{
		int end;
		int weight;
		public Path(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}
}
