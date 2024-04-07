
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static final int INF = 300_000_000;
	
	static int vertex;
	static int edge;
	static Map<Integer, List<Path>> map;
	static int stopby1;
	static int stopby2;
	static int[] path;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		vertex = Integer.parseInt(tokens.nextToken());
		edge = Integer.parseInt(tokens.nextToken());
		map = new HashMap<>();
		for(int i = 0; i < edge; i++) {
			tokens = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(tokens.nextToken());
			int v2 = Integer.parseInt(tokens.nextToken());
			int weight = Integer.parseInt(tokens.nextToken());
			
			if(map.containsKey(v1) == false) {
				map.put(v1, new ArrayList<Path>());
			}
			if(map.containsKey(v2) == false) {
				map.put(v2, new ArrayList<Path>());
			}
			map.get(v1).add(new Path(v2, weight));
			map.get(v2).add(new Path(v1, weight));
		}
		tokens = new StringTokenizer(in.readLine());
		stopby1 = Integer.parseInt(tokens.nextToken());
		stopby2 = Integer.parseInt(tokens.nextToken());
		path = new int[vertex+1];
	}
	
	static void solve() {
		
		if(edge == 0) {
			System.out.print(-1);
			return;
		}
		
		int min = Integer.MAX_VALUE;
		
		int oneToStop1 = dijkstra(1, stopby1);
		boolean passedStop2 = passedby(1, stopby1, stopby2);
		
		int oneToStop2 = dijkstra(1, stopby2);
		boolean passedStop1 = passedby(1, stopby2, stopby1);
		
		int stopToStop = dijkstra(stopby1, stopby2);
		
		int stop1ToN = dijkstra(stopby1, vertex);
		boolean throughStop2 = passedby(stopby1, vertex, stopby2);
		
		int stop2ToN = dijkstra(stopby2, vertex);
		boolean throughStop1 = passedby(stopby2, vertex, stopby1);
		
		if(passedStop2) {
			min = Math.min(min, oneToStop1 + stop1ToN);
		} else {
			if(throughStop2) {
				min = Math.min(min, oneToStop1 + stop1ToN);
			} else {
				min = Math.min(min, oneToStop1 + stopToStop + stop2ToN);
			}
		}
		if(passedStop1) {
			min = Math.min(min, oneToStop2 + stop2ToN);
		} else {
			if(throughStop1) {
				min = Math.min(min, oneToStop2 + stop2ToN);
			} else {
				min = Math.min(min, oneToStop2 + stopToStop + stop1ToN);
			}
		}
		System.out.println(min >= INF ? -1 : min);
	}
	
	static int dijkstra(int start, int end) {
		
		if(start == end) return 0;
		
		int[] minWeight = new int[vertex+1];
		Arrays.fill(minWeight, INF);
		PriorityQueue<Path> q = new PriorityQueue<>(new Comparator<Path>() {
			@Override
			public int compare(Path p1, Path p2) {
				if(p1.weight < p2.weight) return -1;
				else if(p2.weight < p1.weight) return 1;
				else return 0;
			}
		});
		q.add(new Path(start, 0));
		path[start] = 0;
		
		while(!q.isEmpty()) {
			Path cur = q.poll();
			
			if(cur.vert == end) break;
			
			for(Path next : map.get(cur.vert)) {
				
				if(0 < minWeight[next.vert] && minWeight[next.vert] <= cur.weight + next.weight) continue;
				
				int val = cur.weight + next.weight;
				q.add(new Path(next.vert, val));
				minWeight[next.vert] = val;
				path[next.vert] = cur.vert;
			}
		}
		return minWeight[end];
	}
	
	static boolean passedby(int end, int start, int checkPoint) {
		boolean passedby = false;
		while(start > 0) {
			if(start == checkPoint) passedby = true;
			if(start == end) break;
			start = path[start];
		}
		return passedby;
	}
	
	static class Path{
		int vert;
		int weight;
		public Path(int vert, int weight) {
			this.vert = vert;
			this.weight = weight;
		}
	}
}


