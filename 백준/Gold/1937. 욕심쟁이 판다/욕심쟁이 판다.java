import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int size;
	static int[][] forest;
	static int[][] eaten;
	static void input(BufferedReader in) throws IOException {
		size = Integer.parseInt(in.readLine());
		
		forest = new int[size][size];
		eaten = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < size; j++) {
				forest[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
	}
	
	static void solve(){
		int max = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				max = Math.max(max, eat(i, j));
			}
		}
		System.out.println(max);
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static int eat(int y, int x) {
		int val = eaten[y][x];
		
		if(val == 0) {
			val = 1;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
				if(forest[y][x] <= forest[ny][nx]) continue;
				
				val = Math.max(val, eat(ny, nx) + 1);
			}
			
			eaten[y][x] = val;
		}
		
		return val;
	}
}