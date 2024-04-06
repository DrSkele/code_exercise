
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
	static int[] arr;
	static StringBuilder str;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		arr = new int[M];
		str = new StringBuilder();
	}
	
	static void solve() {
		permutation(0, 1);
		System.out.println(str.toString());
	}
	
	static void permutation(int idx, int prev) {
		if(idx == M) {
			for(int n : arr) {
				str.append(n).append(" ");
			}
			str.append("\n");
			return;
		}
		
		for(int n = prev; n <= N; n++) {
			arr[idx] = n;
			permutation(idx+1, n);
		}
	}
	
}


