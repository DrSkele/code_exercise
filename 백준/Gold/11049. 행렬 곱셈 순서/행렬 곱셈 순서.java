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
	
	static int length;
	static int[][] matrix;
	static int[][] dp;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		
		matrix = new int[length][2];
		for(int i = 0; i < length; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			matrix[i][0] = Integer.parseInt(tokens.nextToken());
			matrix[i][1] = Integer.parseInt(tokens.nextToken());
		}
		
		dp = new int[length][length];
	}
	
	static void solve() {
		for(int len = 1; len < length; len++) {
			for(int start = 0; start + len < length; start++) {
				int min = Integer.MAX_VALUE;
				for(int mid = 0; mid < len; mid++) {
					// 시작점~중간점 까지의 최소값 + 중간점+1~끝점 까지의 최소값 + 시작점0 * 중간점1 * 끝점1 = ((시작점, ...,중간점), (중간점+1, ..., 끝점))
					int cur = dp[start][start+mid] + dp[start+mid+1][start+len] + matrix[start][0] * matrix[start+mid][1] * matrix[start+len][1];
					min = Math.min(min, cur);
				}
				dp[start][start+len] = min;
			}
		}
		
		System.out.println(dp[0][length-1]);
	}
	
}