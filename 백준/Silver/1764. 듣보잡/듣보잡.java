import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int N;
	static int M;
	static Set<String> set;
	static PriorityQueue<String> q;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		set = new HashSet<>();
		q = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			set.add(in.readLine());
		}
		for(int i = 0; i < M; i++) {
			String name = in.readLine();
			if(set.contains(name)) {
				q.add(name);
			}
		}
	}
	static void solve() {
		StringBuilder str = new StringBuilder();
		str.append(q.size()).append("\n");
		while(!q.isEmpty()) {
			str.append(q.poll()).append("\n");
		}
		System.out.println(str.toString());
	}
}


