import java.io.*;
import java.util.*;

public class Main {
	
	static int height;
	static int width;
	static int[][] matrix;
	static int paintCnt;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
		
		System.out.println(paintCnt);
		System.out.println(max);
	}
	
	static void init(BufferedReader in) throws IOException {
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
	
	static void solve() {
		max = 0;
		paintCnt = 0;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(matrix[y][x] == 1) {
					paintCnt++;
					max = Math.max(max, bfs(x, y));
				}
			}
		}
	}
	static final int[] dx = {+1, 0, -1, 0};
	static final int[] dy = {0, +1, 0, -1};
	static int bfs(int cx, int cy) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {cx, cy});
		matrix[cy][cx] = 0;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			cnt++;
			
			for(int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
				if(matrix[ny][nx] == 0) continue;
				
				matrix[ny][nx] = 0;
				q.add(new int[] {nx, ny});
			}
		}
		return cnt;
	}
}




