import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		int n = Integer.parseInt(in.readLine());
		int[] arr = new int[n];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		
		boolean[][] matrix = new boolean[n+1][n];
		
		for(int i = 0; i < n; i++) {
			matrix[0][i] = true;
		}
		
		for(int l = 1; l <= n; l++) { //길이
			for(int s = 0; s <= n-l; s++) { //시작 문자
				if(l <= 1) { // 길이 1 이하
					matrix[l][s] = true;
				} else { // 길이 2 이상
					int endIdx = s + l - 1;
					
					if(arr[s] == arr[endIdx]) {
						matrix[l][s] = matrix[l-2][s+1];
					}
				}
			}
		}
		// 11
//		
//		for(int i = 0; i < n+1; i++) {
//			for(int j = 0; j < n; j++) {
//				System.out.print(matrix[i][j] ? 1 + " ": 0 + " ");
//			}
//			System.out.println();
//		}
		
		StringBuilder str = new StringBuilder();
		
		int m = Integer.parseInt(in.readLine());
		for(int i = 0; i < m; i++) {
			tokens = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(tokens.nextToken())-1;
			int end = Integer.parseInt(tokens.nextToken())-1;
			
			int length = end - start + 1;
			
			str.append(matrix[length][start] ? 1 : 0).append("\n");
		}
		System.out.print(str.toString());
	}
	
}
