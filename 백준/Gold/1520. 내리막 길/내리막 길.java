import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int height;
	static int width;
	static int[][] matrix;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(tokens.nextToken());
			}
		}
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static long[][] visited;
	static void solve() {
		visited = new long[height][width];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				visited[y][x] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	static long dfs(int y, int x) {
		if(y == height - 1 && x == width - 1) {
			return 1;
		}
		
		long cnt = 0;
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny < 0 || ny >= height || nx < 0 || nx >= width || matrix[ny][nx] >= matrix[y][x]) continue;
			
			if(visited[ny][nx] >= 0) {
				cnt += visited[ny][nx];
				continue;
			}
			
			long value = dfs(ny, nx);
			cnt += value;
		}
		
		visited[y][x] = cnt;
		return cnt;
	}
}