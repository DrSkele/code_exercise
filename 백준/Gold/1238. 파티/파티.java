
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int INF = 10_000_000;
	static int town;
	static int road;
	static int party;
	static int[] come;
	static int[] back;
	static Map<Integer, List<Path>> map;
	static Map<Integer, List<Path>> inverse;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		town = Integer.parseInt(tokens.nextToken());
		road = Integer.parseInt(tokens.nextToken());
		party = Integer.parseInt(tokens.nextToken());
		come = new int[town+1];
		back = new int[town+1];
		for(int i = 1; i <= town; i++) {
			come[i] = INF;
			back[i] = INF;
		}
		map = new HashMap<>();
		inverse = new HashMap<>();
		for(int i = 0; i < road; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int cost = Integer.parseInt(tokens.nextToken());
			
			if(map.containsKey(from) == false) {
				map.put(from, new ArrayList<Path>());
			}
			map.get(from).add(new Path(to, cost));
			
			if(inverse.containsKey(to) == false) {
				inverse.put(to, new ArrayList<Path>());
			}
			inverse.get(to).add(new Path(from, cost));
		}
	}
	
	static void solve() {
		
		ArrayDeque<Path> q = new ArrayDeque<>();
		
		q.add(new Path(party, 0));
		come[party] = 0;
		
		while(!q.isEmpty()) {
			Path cur = q.poll();
			
			if(map.containsKey(cur.dest) == false) continue;
			
			for(Path next : map.get(cur.dest)) {
				int val = cur.cost + next.cost;
				if(come[next.dest] <= val) continue;
				
				come[next.dest] = val;
				q.add(new Path(next.dest, val));
			}
		}
		
		q.clear();
		
		q.add(new Path(party, 0));
		back[party] = 0;
		
		while(!q.isEmpty()) {
			Path cur = q.poll();
			
			if(inverse.containsKey(cur.dest) == false) continue;
			
			for(Path next : inverse.get(cur.dest)) {
				int val = cur.cost + next.cost;
				if(back[next.dest] <= val) continue;
				
				back[next.dest] = val;
				q.add(new Path(next.dest, val));
			}
		}
		
		int max = 0;
		
		for(int i = 1; i <= town; i++) {
			int val = come[i] + back[i];
			if(val >= INF) continue;
			max = Math.max(max, val);
		}
		
		System.out.println(max);
	}
	
	static class Path{
		int dest;
		int cost;
		public Path(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}


