import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int N;
	static int[][] matrix;
	static int cntWhite;
	static int cntBlue;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		matrix = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		cntWhite = 0;
		cntBlue = 0;
	}
	static void solve() {
		int val = split(0,0,N);
		if(val == 1) cntBlue++;
		else if(val == 0) cntWhite++;
		System.out.println(cntWhite);
		System.out.println(cntBlue);
	}
	static int split(int startX, int startY, int size) {
		if(size == 1) return matrix[startY][startX];
		
		int midX = startX+(size/2);
		int midY = startY+(size/2);
		
		int[] sq = new int[4];
		sq[0] = split(startX, startY, size/2);
		sq[1] = split(midX, startY, size/2);
		sq[2] = split(startX, midY, size/2);
		sq[3] = split(midX, midY, size/2);
		
		if(sq[0] == sq[1] && sq[0] == sq[2] && sq[0] == sq[3]) {
			return sq[0];
		} else {
			//System.out.println(sq[0] + ", " + sq[1]+ ", " + sq[2]+ ", " + sq[3] + ": " + size);
			for(int i = 0; i < 4; i++) {
				if(sq[i] == 0) cntWhite++;
				if(sq[i] == 1) cntBlue++;
			}
			return 4;
		}
	}
}


