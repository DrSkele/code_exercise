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
	static int[][] matrix;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[height][width];
		
		for(int i = 0; i < height; i++) {
			tokens = new StringTokenizer(in.readLine());
			for(int j = 0; j < width; j++) {
				matrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}
	

	static int[][] state;
	static void solve() {
		int cnt = 0;
		
		do {
			cnt++;
			
			state = new int[height][width];
			
			exposure();
			
		} while(melt());
		
		System.out.println(cnt);
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void exposure() {
		
		boolean[][] visited = new boolean[height][width];
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
				if(visited[ny][nx]) continue;
				
				if(matrix[ny][nx] == 1) {
					state[ny][nx]++;
				} else {
					q.add(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
			}
		}
	}
	
	static boolean melt() {
		boolean hasCheese = false;
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(matrix[y][x] == 0) continue;
				if(state[y][x] >= 2) matrix[y][x] = 0;
				else hasCheese = true;
			}
		}
		
		return hasCheese;
	}
}