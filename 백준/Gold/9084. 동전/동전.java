import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		
		for(int t = 0; t< T; t++) {
			init(in);
			
			solve();			
		}
		
	}
	
	static int N;
	static int[] coins;
	static int money;
	static int[] dp;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		coins = new int[N+1];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			coins[i] = Integer.parseInt(tokens.nextToken());
		}
		money = Integer.parseInt(in.readLine());
		dp = new int[money+1];
	}
	
	public static void solve() {
		dp[0] = 1;
		
		for(int i = 1; i < coins.length; i++) {
			int coin = coins[i];
			
			for(int m = 1; m <= money; m++) {
				if(m < coin) continue;
				dp[m] += dp[m-coin];			
			}
		}
		
		System.out.println(dp[money]);
	}
}

