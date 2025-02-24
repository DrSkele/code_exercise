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
	static int[][] matrix;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			char[] line = in.readLine().toCharArray();
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Character.getNumericValue(line[x]);
			}
		}
	}
	
	static void solve() {
		int max = 0;
		
		int[][] dp = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(matrix[y][x] == 0) continue;
				
				if(y > 0 && x > 0) {
					int min = matrix[y-1][x-1] == 1 ? dp[y-1][x-1] : 0;
					min = Math.min(min, matrix[y-1][x-1] == 1 ? dp[y][x-1] : 0);
					min = Math.min(min, matrix[y-1][x-1] == 1 ? dp[y-1][x] : 0);
					dp[y][x] = min + 1;
				} else {
					dp[y][x] = 1;
				}
				max = Math.max(max, dp[y][x]);
			}
		}
		
		System.out.println(max * max);
	}
}