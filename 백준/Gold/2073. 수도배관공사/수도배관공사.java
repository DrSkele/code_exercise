import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int dist;
	static int nPipe;
	static int[] length;
	static int[] capacities;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		dist = Integer.parseInt(tokens.nextToken());
		nPipe = Integer.parseInt(tokens.nextToken());
		
		length = new int[nPipe];
		capacities = new int[nPipe];
		
		for(int i = 0; i < nPipe; i++) {
			tokens = new StringTokenizer(in.readLine());
			length[i] = Integer.parseInt(tokens.nextToken());
			capacities[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		int[] dp = new int[dist+1];
		
		dp[0] = 1 << 24;
		
		for(int i = 0; i < nPipe; i++) {
			int size = length[i];
			for(int d = dist; d >= size; d--) {
				if(dp[d - size] > 0) {
					dp[d] = Math.max(dp[d], Math.min(dp[d-size], capacities[i]));
				}
			}
			
//			for(int d : dp) {
//				System.out.print(d);
//			}
//			System.out.println();
		}
		
		System.out.println(dp[dist]);
	}
	
}