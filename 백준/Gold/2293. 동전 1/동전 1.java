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
	
	static int num;
	static int total;
	static int[] coins;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		num = Integer.parseInt(tokens.nextToken());
		total = Integer.parseInt(tokens.nextToken());
		coins = new int[num+1];
		for(int i = 1; i < num+1; i++) {
			coins[i] = Integer.parseInt(in.readLine());
		}
	}
	
	static void solve() {
		int[][] dp = new int[num+1][total+1];
		
		for(int i = 0; i < num+1; i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 1; i < num+1; i++) {
			int coin = coins[i];
			for(int j = 1; j < total+1; j++) {
				if(j < coin) dp[i][j] = dp[i-1][j];
				else dp[i][j] = dp[i-1][j] + dp[i][j-coin];
			}
		}
		
//		for(int i = 0; i < num+1; i++) {
//			for(int j = 0; j < total+1; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(dp[num][total]);
	}
	
}