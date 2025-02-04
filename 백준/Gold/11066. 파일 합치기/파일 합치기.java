import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < T; t++) {
			init(in);
			
			solve(in);			
		}
	}
	
	static int length;
	static int[] files;
	static int[][] dp;
	static int[] sum;
	public static void init(BufferedReader in) throws IOException{
		length = Integer.parseInt(in.readLine());
		files = new int[length+1];
		dp = new int[length+1][length+1];
		sum = new int[length+1];
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		for(int i = 1; i <= length; i++) {
			files[i] = Integer.parseInt(tokens.nextToken());
			dp[i][i] = files[i];
			sum[i] = sum[i-1] + files[i];
		}
		
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		for(int s = 1; s <= length; s++) {
			for(int i = 1; i + s <= length; i++) {
				int j = i + s;
				
				dp[i][j] = Integer.MAX_VALUE;
				
				for(int m = i; m < j; m++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m+1][j] + sum[j] - sum[i-1]);					
				}
			}
		}
		
//		for(int i = 1; i <= length; i++) {
//			for(int j = 1; j <= length; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(dp[1][length] - sum[length]);
	}
}
