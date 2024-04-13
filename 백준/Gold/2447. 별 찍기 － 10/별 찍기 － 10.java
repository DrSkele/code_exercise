
import java.io.*;
import java.util.*;

// x 중앙 스킵
// y 중앙 스킵

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static boolean[][] matrix;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		matrix = new boolean[N][N];
	}
	
	static void solve() {
		cube(0, 0, N);
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				str.append(matrix[i][j] ? "*" : " ");
			}
			str.append("\n");
		}
		System.out.println(str.toString());
	}
	
	static void cube(int startX, int startY, int size) {
		if(size == 1) {
			matrix[startY][startX] = true;
			return;
		}
		int oneThird = size/3;
		
		cube(startX, startY, oneThird);
		cube(startX + oneThird, startY, oneThird);
		cube(startX + oneThird*2, startY, oneThird);
		
		cube(startX, startY + oneThird, oneThird);
		
		cube(startX + oneThird*2, startY + oneThird, oneThird);
		
		cube(startX, startY + oneThird*2, oneThird);
		cube(startX + oneThird, startY + oneThird*2, oneThird);
		cube(startX + oneThird*2, startY + oneThird*2, oneThird);
	}
	
}


