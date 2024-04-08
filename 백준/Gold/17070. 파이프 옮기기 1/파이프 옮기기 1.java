
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int size;
	static int[][] matrix;
	static Pipe[][] pipe;
	static boolean[][] visited;
	static void init(BufferedReader in) throws IOException{
		size = Integer.parseInt(in.readLine());
		matrix = new int[size][size];
		pipe = new Pipe[size][size];
		visited = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
				pipe[i][j] = new Pipe();
			}
		}
	}
	
	static void solve() {
		
		pipe[0][1].hori = 1;
		
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				if(matrix[y][x] == 1) continue;
				Pipe cur = pipe[y][x];
				if(0 <= x - 1 && pipe[y][x-1].sumHori() > 0) {
					cur.hori = pipe[y][x-1].sumHori();
				}
				if(0 <= y - 1 && pipe[y-1][x].sumVert() > 0) {
					cur.vert = pipe[y-1][x].sumVert();
				}
				if((0 <= y-1 && 0 <= x-1) && (matrix[y-1][x] != 1 && matrix[y][x-1] != 1) && pipe[y-1][x-1].sumDiag() > 0) {
					cur.diag = pipe[y-1][x-1].sumDiag();
				}
			}
		}
		
//		for(int i = 0; i < size; i++) {
//			for(int j = 0; j < size; j++) {
//				System.out.print(pipe[i][j].sumDiag());
//			}
//			System.out.println();
//		}
		System.out.println(pipe[size-1][size-1].sumDiag());
	}
	
	static class Pipe{
		public int hori;
		public int vert;
		public int diag;
		
		public int sumHori() {
			return hori + diag;
		}
		
		public int sumVert() {
			return vert + diag;
		}
		
		public int sumDiag() {
			return hori + vert + diag;
		}
	}
}


