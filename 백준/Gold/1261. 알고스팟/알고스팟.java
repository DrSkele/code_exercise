import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();	
	}
	
	static int width;
	static int height;
	static int[][] matrix;
	static int[][] visited;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		width = Integer.parseInt(tokens.nextToken());
		height = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[height][width];
		visited = new int[height][width];
		
		for(int i = 0; i < height; i++) {
			String line = in.readLine();
			for(int j = 0; j < width; j++) {
				matrix[i][j] = line.charAt(j) == '1' ? 1 : 0;
			}
		}
	}
	
	public static void solve() {
		bfs(0,0);
		
		System.out.println(visited[height-1][width-1]-1);
	}
	
	static final int[] dx = { +1, 0, -1, 0 };
	static final int[] dy = { 0, +1, 0, -1 };
	
	static void bfs(int sx, int sy) {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {sx, sy});
		visited[sy][sx] = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int val = visited[y][x];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
				int nVal = visited[ny][nx];
				
				if(matrix[ny][nx] == 0) {					
					if(0 < nVal && nVal <= val) continue;
					visited[ny][nx] = val;
				} else {
					if(0 < nVal && nVal <= val + 1) continue;
					visited[ny][nx] = val + 1;
				}
				q.add(new int[] {nx, ny});
			}
		}
	}
}

