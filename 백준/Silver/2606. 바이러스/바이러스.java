
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int M;
	static boolean[] visited;
	static Map<Integer, ArrayList<Integer>> connection;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		visited = new boolean[N+1];
		connection = new HashMap<>();
		
		for(int i = 0; i < M; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			if(connection.containsKey(a) == false) {
				connection.put(a, new ArrayList<>());
			}
			if(connection.containsKey(b) == false) {
				connection.put(b, new ArrayList<>());
			}
			
			connection.get(a).add(b);
			connection.get(b).add(a);
		}
	}
	
	static void solve() {
		if(M == 0) System.out.println(0);
		else System.out.println(bfs(1));
	}
	
	static int bfs(int n) {
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(n);
		visited[n] = true;
		
		int cnt = 0;
		
		while(q.isEmpty() == false) {
			int cur = q.poll();
			
			for(int next : connection.get(cur)) {
				if(visited[next]) continue;
				
				q.add(next);
				visited[next] = true;
				cnt++;
			}
		}
		return cnt;
	}
	
}


