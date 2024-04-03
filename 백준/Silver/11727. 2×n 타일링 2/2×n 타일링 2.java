
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static final int mod = 10_007;
	static int N;
	static int[] dp;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		dp = new int[N+1];
		
		dp[1] = 1;
		if(N > 1) dp[2] = 3;
	}
	
	static void solve() {
		for(int i = 3; i <= N; i++) {
			dp[i] = dp[i-1]%mod + (2*dp[i-2])%mod;
		}
		System.out.println(dp[N]%mod);
	}
	
}


