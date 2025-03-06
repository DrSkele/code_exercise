import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int height;
	static int width;
	static int[][] dp;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		dp = new int[height+1][width+1];
		
		for(int i = 1; i < height+1; i++) {
			char[] line = in.readLine().toCharArray();
			for(int j = 1; j < width+1; j++) {
				if(line[j-1] == '0') continue;
				
				int min = Integer.MAX_VALUE;
				
				min = Math.min(min, dp[i-1][j]);
				min = Math.min(min, dp[i][j-1]);
				min = Math.min(min, dp[i-1][j-1]);
				
				dp[i][j] = min + 1;
			}
		}
	}
	
	static void solve(){
		int max = 0;
		
		for(int i = 1; i < height+1; i++) {
			for(int j = 1; j < width+1; j++) {
				//System.out.print(dp[i][j]);
				max = Math.max(max, dp[i][j]);
			}
			//System.out.println();
		}
		
		System.out.println(max * max);
	}
}