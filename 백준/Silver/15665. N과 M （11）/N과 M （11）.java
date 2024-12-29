import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int N;
	static int M;
	static int[] arr;
	static int[] temp;
	static StringBuilder str;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N];
		temp = new int[M];
		
		str = new StringBuilder();
		
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(arr);
	}
	
	static void solve() {
		combination(0, 0);
		System.out.println(str.toString());
	}
	
	static void combination(int idx, int length) {
		if(length == M) {
			for(int num : temp) {
				str.append(num).append(" ");
			}
			str.append("\n");
			return;
		}
		
		int prev = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == prev) continue;
			prev = arr[i];
			temp[idx] = arr[i];
			combination(idx + 1, length + 1);
		}
	}
}