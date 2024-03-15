import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve(in);
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
		for(int i = 1; i <= L; i++) {
			tokens = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			if(find(a) == find(b)) {
				System.out.println(i);
				return;
			}
			
			union(a, b);
		}
		System.out.println(0);
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


