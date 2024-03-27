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
	static int box;
	static int limit;
	
	static int[][] matrix;
	static int[][] collected;
	static int[] row;
	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		box = Integer.parseInt(tokens.nextToken());
		limit = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[size][size];
		collected = new int[size][size-box+1];
		row = new int[size];
		for(int i = 0; i < size; i++){
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}
	
	static void solve(int t) {
		int length = size-box+1;
		for(int i = 0; i < size; i++){
			int max = 0;
			for(int j = 0; j < length; j++) {
				int honey = collect(j, i);
				collected[i][j] = honey;
				if(max < honey) max = honey;
			}
			row[i] = max;
		}
		
		int max = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < length; j++) {
				int cur = collected[i][j];
				int pick = 0;
				for(int k = j+box; k < length; k++) {
					pick = Math.max(pick, collected[i][k]);
				}
				for(int r = 0; r < size; r++) {
					if(r == i) continue;
					pick = Math.max(pick, row[r]);
				}
				max = Math.max(max, cur+pick);
			}
		}
		System.out.println(String.format("#%d %d", t, max));
	}
	
	static int collect(int x, int y) {
		int[][] max = new int[box+1][limit+1];
		
		for(int i = 1; i <= box; i++) {
			for(int j = 1; j <= limit; j++) {
				int cur = matrix[y][x-1+i];
				
				if(cur > j) max[i][j] = max[i-1][j];
				else max[i][j] = Math.max(max[i-1][j], max[i-1][j-cur] + cur*cur);
			}
		}
		return max[box][limit];
	}
}
