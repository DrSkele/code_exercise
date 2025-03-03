import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve(in);		
	}
	
	static int size;
	static int cnt;
	static int[][] dp;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		dp = new int[size+1][size+1];
		
		for(int i = 1; i < size+1; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 1; j < size+1; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + Integer.parseInt(tokens.nextToken());
			}
		}
	}
	
	static void solve(BufferedReader in) throws IOException {
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < cnt; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(tokens.nextToken())-1;
			int y1 = Integer.parseInt(tokens.nextToken())-1;
			int x2 = Integer.parseInt(tokens.nextToken());
			int y2 = Integer.parseInt(tokens.nextToken());
			
			int sum = dp[x2][y2] - dp[x2][y1] - dp[x1][y2] + dp[x1][y1];
			
			str.append(sum).append("\n");
		}
		
		System.out.println(str.toString());
	}
}