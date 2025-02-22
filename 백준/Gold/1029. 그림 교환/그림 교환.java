import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static int num;
	static int[][] matrix;
	static int[][] maxCnt;
	static void input(BufferedReader in) throws IOException {
		num = Integer.parseInt(in.readLine());
		matrix = new int[num][num];
		
		for(int i = 0; i < num; i++) {
			char[] line = in.readLine().toCharArray();
			for(int j = 0; j < num; j++) {
				matrix[i][j] = Character.getNumericValue(line[j]);
			}
		}
		
		maxCnt = new int[num][1 << num];
	}
	
	static void solve() {
		System.out.println(trade(0, 1, 0) + 1);
	}
	
	static int trade(int owner, int visited, int price) {
		
		int result = maxCnt[owner][visited];
		if(result > 0) return result;
		
		for(int i = 0; i < num; i++) {
			if(i == owner || matrix[owner][i] < price || (visited & (1 << i)) > 0) continue;
			
			result = Math.max(result, trade(i, visited | (1 << i), matrix[owner][i]) + 1);
		}
		
		maxCnt[owner][visited] = result;
		
		return result;
	}
}