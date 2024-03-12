import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve(in);
	}
	
	static int N;
	static int M;
	static int[] arr;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		arr = new int[N+1];
		for(int i =0; i <= N; i++) {
			arr[i] = i;
		}
	}
	
	static void solve(BufferedReader in) throws IOException {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < M; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			String cmd = tokens.nextToken();
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			if(cmd.equals("0")) {
				union(a, b);
			} else {
				str.append((find(a) == find(b)) ? "YES" : "NO").append("\n"); 
			}
		}
		System.out.println(str.toString());
	}
	
	static int find(int a) {
		if(arr[a] == a) {
			return a;
		}
		return arr[a] = find(arr[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		arr[b] = a;
	}
}


