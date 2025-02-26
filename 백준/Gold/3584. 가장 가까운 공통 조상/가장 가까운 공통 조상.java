import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < T; t++) {
			input(in);
			solve();			
		}
	}
	
	static int nNode;
	static int[] parents;
	static int child1;
	static int child2;
	static void input(BufferedReader in) throws IOException {
		nNode = Integer.parseInt(in.readLine());
		
		parents = new int[nNode+1];
		
		for(int i = 0; i < nNode-1; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			int parent = Integer.parseInt(tokens.nextToken());
			int child = Integer.parseInt(tokens.nextToken());
			
			parents[child] = parent;
		}
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		child1 = Integer.parseInt(tokens.nextToken());
		child2 = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve() {
		
		boolean[] visited = new boolean[nNode+1];
		
		int idx = child1;
		while(idx != 0) {
			visited[idx] = true;
			idx = parents[idx];
		}
		
		idx = child2;
		while(idx != 0) {
			if(visited[idx]) break;
			idx = parents[idx];
		}
		
		System.out.println(idx);
	}
	
}