import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nOrder;
	static int nBurger;
	static int nFry;
	static int[] burgers;
	static int[] fries;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nOrder = Integer.parseInt(tokens.nextToken());
		nBurger = Integer.parseInt(tokens.nextToken());
		nFry = Integer.parseInt(tokens.nextToken());
		
		burgers = new int[nOrder];
		fries = new int[nOrder];
		
		for(int i = 0; i < nOrder; i++) {
			tokens = new StringTokenizer(in.readLine());
			burgers[i] = Integer.parseInt(tokens.nextToken());
			fries[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		int length = nBurger*1000 + nFry + 1;
		int[] dp = new int[length];
		
		for(int i = 0; i < nOrder; i++) {
			for(int b = nBurger; b >= burgers[i]; b--) {
				for(int f = nFry; f >= fries[i]; f--) {
					int cur = b*1000 + f;
					int idx = (b-burgers[i])*1000 + f-fries[i];
					
					dp[cur] = Math.max(dp[cur], dp[idx] + 1);
				}
			}
		}
		
		System.out.println(dp[length-1]);
	}
	
}