import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t);
		}
	}
	
	static int size;
	static int[][] matrix;
	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		matrix = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int value = Integer.parseInt(tokens.nextToken());
				if(i == j) continue;
				matrix[i][j] = value > 0 ? value : 100_000;
			}
		}
	}
	
	static void solve(int t) {
		for(int n = 0; n < size; n++) {
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					int direct = matrix[i][j];
					int detour = matrix[i][n] + matrix[n][j];
					matrix[i][j] = Math.min(direct, detour);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < size; i++) {
			int sum = 0;
			for(int j = 0; j < size; j++) {
				sum += matrix[i][j];
			}
			min = Math.min(min, sum);
		}
		System.out.println(String.format("#%d %d",t, min));
	}
}
