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
	static int length;
	static int[][] matrix;
	static int cnt;
	static void init(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		size = Integer.parseInt(tokens.nextToken());
		length = Integer.parseInt(tokens.nextToken());
		matrix = new int[size][size];
		cnt = 0;
		
		for(int i = 0; i < size; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}
	
	static void solve(int t) {
		
		for(int i = 0; i < size; i++) {
			int prev = matrix[i][0];
			int runway = 1;
			
			boolean build = true;
			for(int j = 1; j < size; j++) {
				int cur = matrix[i][j];
				
				if(cur > prev) { //step up
					if(cur - prev == 1) {
						if(runway >= length) {
							prev = cur;
							runway = 1;
						} else {
							build = false;
							break;
						}
					} else {						
						build = false;
						break;
					}
				}else if(cur < prev) { //step down
					if(prev - cur == 1) {
						runway = 1;
						prev = cur;
						int need = j+length;
						while(j+1 < need && j+1 < size) {
							int next = matrix[i][j+1];
							
							if(next == prev) runway++;
							j++;
						}
						if(runway >= length) {
							runway -= length;
						} else {
							build = false;
							break;
						}
					} else {
						build = false;
						break;
					}
				} else {
					runway++;
				}
			}
			if(build) cnt++;
		}
		
		for(int i = 0; i < size; i++) {
			int prev = matrix[0][i];
			int runway = 1;
			
			boolean build = true;
			for(int j = 1; j < size; j++) {
				int cur = matrix[j][i];
				
				if(cur > prev) { //step up
					if(cur - prev == 1) {
						if(runway >= length) {
							prev = cur;
							runway = 1;
						} else {
							build = false;
							break;
						}
					} else {						
						build = false;
						break;
					}
				}else if(cur < prev) { //step down
					if(prev - cur == 1) {
						runway = 1;
						prev = cur;
						int need = j+length;
						while(j+1 < need && j+1 < size) {
							int next = matrix[j+1][i];
							
							if(next == prev) runway++;
							j++;
						}
						if(runway >= length) {
							runway -= length;
						} else {
							build = false;
							break;
						}
					} else {
						build = false;
						break;
					}
				} else {
					runway++;
				}
			}
			if(build) cnt++;
		}
		
		System.out.println(String.format("#%d %d", t, cnt));
		
	}
}
