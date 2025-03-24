import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int nPipe;
	static int goal;
	static int[] length;
	static int[] cnt;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		nPipe = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		
		length = new int[nPipe];
		cnt = new int[nPipe];
		
		for(int i = 0; i < nPipe; i++) {
			tokens = new StringTokenizer(in.readLine());
			
			length[i] = Integer.parseInt(tokens.nextToken());
			cnt[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve(){
		int[] dp = new int[goal+1];
		
		dp[0] = 1;
		
		for(int i = 0; i < nPipe; i++) {
			for(int l = goal; l >= length[i]; l--) {
				for(int n = cnt[i]; n >= 1; n--) {
					int totalLength = length[i] * n;
					if(totalLength <= l) {
						dp[l] += dp[l-totalLength];
					}
				}
				
//				for(int a : dp) {
//					System.out.print(a + " ");
//				}
//				System.out.println();
			}
		}
		
		System.out.println(dp[goal]);
	}
	
}