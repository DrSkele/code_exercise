import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for(int t = 0; t < T; t++) {
			
			init(in);
			
			solve(in);
			
		}
		System.out.println(str.toString());
	}
	static StringTokenizer tokens;
	static int V;
	static int L;
	static int[] union;
	static void init(BufferedReader in) throws IOException{
		tokens = new StringTokenizer(in.readLine());
		V = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		union = new int[V];
		for(int i = 0; i < V; i++) {
			union[i] = i;
		}
	}
	
	static void solve(BufferedReader in) throws IOException {
		int sum = 0;
		for(int i = 0; i < L; i++) {
			tokens = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(tokens.nextToken()) - 1;
			int b = Integer.parseInt(tokens.nextToken()) - 1;
			
			if(find(a) == find(b)) continue;
			
			sum++;
			union(a, b);
		}
		str.append(sum).append("\n");
	}
	
	static int find(int c) {
		if(union[c] == c) return c;
		
		return union[c] = find(union[c]);
	}
	
	static void union(int a, int b) {
		int ua = find(a);
		int ub = find(b);
		
		union[ub] = ua;
	}
}


