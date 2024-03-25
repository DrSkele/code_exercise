import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;//Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t);
		}
	}
	
	static String testCase;
	static char[][] matrix;
	static void init(BufferedReader in) throws IOException {
		testCase = in.readLine();
		
		matrix = new char[100][100];
		
		for(int i = 0; i < 100; i++) {
			String line = in.readLine();
			
			for(int j = 0; j < 100; j++) {
				matrix[i][j] = line.charAt(j);
			}
		}
	}
	
	static void solve(int t) throws IOException {
		
		int max = 0;
		for(int i = 0; i < 99; i++) {
			for(int j = 0; j < 99; j++) {
				if(matrix[i][j] == matrix[i][j+1]) {
					int length = 0;
					int left = j;
					int right = j+1;
					while(0 <= left && right < 99) {
						if(matrix[i][left--] != matrix[i][right++]) break;
						length+=2;
					}
					max = Math.max(max, length);
				}
				if(0 <= j-1 && (matrix[i][j-1] == matrix[i][j+1])) {
					int length = 1;
					int left = j-1;
					int right = j+1;
					while(0 <= left && right < 99) {
						if(matrix[i][left--] != matrix[i][right++]) break;
						length+=2;
					}
					max = Math.max(max, length);
				}
				
				if(matrix[i][j] == matrix[i+1][j]) {
					int length = 0;
					int left = i;
					int right = i+1;
					while(0 <= left && right < 99) {
						if(matrix[left--][j] != matrix[right++][j]) break;
						length+=2;
					}
					max = Math.max(max, length);
				}
				if(0 <= i-1 && (matrix[i-1][j] == matrix[i+1][j])) {
					int length = 1;
					int left = i-1;
					int right = i+1;
					while(0 <= left && right < 99) {
						if(matrix[left--][j] != matrix[right++][j]) break;
						length+=2;
					}
					max = Math.max(max, length);
				}
			}
		}
		
		System.out.println(String.format("#%s %d", testCase, max));
	}
	
}
