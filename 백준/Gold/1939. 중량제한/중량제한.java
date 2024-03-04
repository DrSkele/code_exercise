import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);

		solve();
	}
	
	static int islands;
	static int bridges;
	static ArrayList<Bridge>[] map;
	static boolean[] visited;
	static int start;
	static int end;
	static int maxLimit;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		islands = Integer.parseInt(tokens.nextToken());
		bridges = Integer.parseInt(tokens.nextToken());
		map = new ArrayList[islands+1];
		visited = new boolean[islands+1];
		
		for(int i = 0; i < bridges; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int limit = Integer.parseInt(tokens.nextToken());
			if(map[from] == null) map[from] = new ArrayList<>();
			map[from].add(new Bridge(to, limit));
			if(map[to] == null) map[to] = new ArrayList<>();
			map[to].add(new Bridge(from, limit));
			maxLimit = Math.max(maxLimit, limit);
		}
		
		tokens = new StringTokenizer(in.readLine());
		start = Integer.parseInt(tokens.nextToken());
		end = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve() {
		
		int first = 1;
		int last = maxLimit+1;
		int result = 0;
		
		while(first < last) {
			int mid = (first + last) / 2;
			visited = new boolean[islands+1];
			if(bfs(start, end, mid)) {
				first = mid+1;
				result = mid;
			} else {
				last = mid;
			}
		}
		
		System.out.println(result);
	}
	
	static boolean bfs(int startPoint, int endPoint, int max) {
		Queue<Integer> q = new LinkedList<>();
		q.add(startPoint);
		visited[startPoint] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == endPoint) return true;
			
			for(Bridge bridge : map[cur]) {
				int next = bridge.conTo;
				int limit = bridge.limit;
				
				if(visited[next] || limit < max) continue;
				
				q.add(next);
				visited[next] = true;
			}
		}
		return false;
	}
	
	static class Bridge{
		public int conTo;
		public int limit;
		public Bridge(int conTo, int limit) {
			this.conTo = conTo;
			this.limit = limit;
		}
	}
}
