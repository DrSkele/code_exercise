import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int height;
	static int width;
	static char[][] matrix;
	static int[][] visited;
	static int max;
	static void input(BufferedReader in) throws IOException {		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		matrix = new char[height][width];
		visited = new int[height][width];
		for(int i = 0; i < height; i++) {
			matrix[i] = in.readLine().toCharArray();
		}
		
		max = 0;
	}
	
	static void solve(){
		dfs(0, 0, 1 << (matrix[0][0] - 'A'), 1);
		
		System.out.println(max);
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void dfs(int y, int x, int check, int length) {
		max = Math.max(max, length);
		
		visited[y][x] = check;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
			
			int idx = 1 << (matrix[ny][nx] - 'A');
			
			if((check & idx) > 0) continue;
			if((check | idx) == visited[ny][nx]) continue;
			
			dfs(ny, nx, check | idx, length+1);
		}
	}
}