
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int goal;
	static Set<Integer> set;
	static int[] dp;
	static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		set = new HashSet<>();
		for(int i = 0; i < N; i++) {
			int val = Integer.parseInt(in.readLine());
			if(val <= goal) set.add(val);
		}
		dp = new int[goal+1];
		for(int i = 1; i <= goal; i++) {
			dp[i] = -1;
		}
	}
	
	static void solve() {
		Integer[] arr = set.toArray(new Integer[0]);
		
		for(int i = 0; i < arr.length; i++) {
			int coin = arr[i];
			for(int m = 1; m <= goal; m++) {
				if(m < coin) continue;
				if(dp[m-coin] < 0) continue;
				
				if(dp[m] == -1) {
					 dp[m] = dp[m-coin] + 1;
				} else {					
					dp[m] = Math.min(dp[m], dp[m-coin] + 1);
				}
			}
		}
		int result = dp[goal] > 0 ? dp[goal] : -1;
		System.out.println(dp[goal]);
	}
	
}


