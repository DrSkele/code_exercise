import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nJewel;
	static int maxWeight;
	static int[] weights;
	static int[] durabilities;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nJewel = Integer.parseInt(tokens.nextToken());
		maxWeight = Integer.parseInt(tokens.nextToken());
		
		weights = new int[nJewel];
		durabilities = new int[nJewel];
		
		for(int i = 0; i < nJewel; i++) {
			tokens = new StringTokenizer(in.readLine());
			weights[i] = Integer.parseInt(tokens.nextToken());
			durabilities[i] = Integer.parseInt(tokens.nextToken());	
		}
	}
	
	static void solve(){
		int[] dp = new int[maxWeight+1];
		
		for(int i = 0; i < nJewel; i++) {
			for(int j = maxWeight; j >= weights[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - weights[i]] + durabilities[i]);
			}
		}
		
		int max = 0;
		for(int val : dp) {
			max = Math.max(max, val);
		}
		
		System.out.println(max);
	}
	
}