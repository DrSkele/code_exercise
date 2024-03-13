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
	static boolean[] visited;
	static int[] perm;
	static StringBuilder str;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		arr = new int[N];
		visited = new boolean[N];
		perm = new int[M];
		tokens = new StringTokenizer(in.readLine());
		for(int i =0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(arr);
		str = new StringBuilder();
	}
	
	static void solve(BufferedReader in) throws IOException {
		permutation(0);
		System.out.println(str);
	}
	
	static void permutation(int idx) {
		if(idx >= M) {
			for(int n : perm) {
				str.append(n).append(" ");
			}
			str.append("\n");
			return;
		}
		int prev = -1;
		for(int i = 0; i < arr.length; i++) {
			if(visited[i] || prev == arr[i]) continue;
			
			perm[idx] = arr[i];
			visited[i] = true;
			permutation(idx+1);
			visited[i] = false;
			prev = arr[i];
		}
	}
}


