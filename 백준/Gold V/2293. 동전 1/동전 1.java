import java.io.*;
import java.util.*;

public class Main {
	static int variant;
	static Integer[] coins;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		variant = Integer.parseInt(tokens.nextToken());
		int goal = Integer.parseInt(tokens.nextToken());
		
		coins = new Integer[variant];
		for(int i = 0; i < variant; i++) {
			coins[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(coins);
		
		dp = new int[goal+1];
		
		dp[0] = 1;
		for(int i = 0; i < variant; i++) {
			for(int value = 1; value < goal+1; value++) {
				if(value >= coins[i]) dp[value] += dp[value-coins[i]];
			}
		}
		
		System.out.println(dp[goal]);
	}
}
