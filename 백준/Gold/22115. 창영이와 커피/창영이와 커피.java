import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nCoffee;
	static int goal;
	static int[] caffeine;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nCoffee = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		caffeine = new int[nCoffee];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nCoffee; i++) {
			caffeine[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static final int max = 1_000_000;
	static void solve(){
		int[] dp = new int[goal+1];
		
		Arrays.fill(dp, max);
		dp[0] = 0;
		
		for(int i = 0; i < nCoffee; i++) {
			for(int c = goal; c >= caffeine[i]; c--) {
				if(dp[c - caffeine[i]] + 1 < max) {
					dp[c] = Math.min(dp[c], dp[c - caffeine[i]] + 1);
				}
			}
		}
		
		System.out.println(dp[goal] != max ? dp[goal] : -1);
	}
	
}