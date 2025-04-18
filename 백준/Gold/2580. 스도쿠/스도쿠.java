import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int[][] matrix;
	static ArrayList<int[]> empty;
	static void input(BufferedReader in) throws IOException {
		
		matrix = new int[9][9];
		empty = new ArrayList<>();
		
		for(int i = 0; i < 9; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < 9; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
				if(matrix[i][j] == 0) empty.add(new int[] {i, j});
			}
		}
	}
	
	static void solve(){
		fill(0);
	}
	
	static void fill(int idx) {
		if(idx == empty.size()) {
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					str.append(matrix[i][j]).append(' ');
				}
				str.append("\n");
			}
			System.out.println(str.toString());
			System.exit(0);
		}
		
		int[] cur = empty.get(idx);
		int y = cur[0];
		int x = cur[1];
		
		for(int i = 1; i <= 9; i++) {
			if(checkRow(y, i) && checkColumn(x, i) && checkBox(y, x, i)) {
				matrix[y][x] = i;
				fill(idx+1);
				matrix[y][x] = 0;
			}
		}
	}
	
	static boolean checkRow(int y, int val) {
		for(int i = 0; i < 9; i++) {
			if(matrix[y][i] == val) return false;
		}
		return true;
	}
	
	static boolean checkColumn(int x, int val) {
		for(int i = 0; i < 9; i++) {
			if(matrix[i][x] == val) return false;
		}
		return true;
	}
	
	static boolean checkBox(int y, int x, int val) {
		int startY = (y/3)*3;
		int startX = (x/3)*3;
		
		for(int i = startY; i < startY+3; i++) {
			for(int j = startX; j < startX+3; j++) {
				if(matrix[i][j] == val) return false;
			}
		}
		return true;
	}
}