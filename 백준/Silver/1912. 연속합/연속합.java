import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static int[] arr;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		arr = new int[length];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	
	static void solve() {
		int[] dp = new int[length];
		dp[0] = arr[0];
		int max = dp[0];
		for(int i= 1; i < length; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}