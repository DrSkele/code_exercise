import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for(int t = 0; t < T; t++) {
			input(in);
			solve();			
		}
		
	}
	
	static int height;
	static int width;
	static int cnt;
	static char[][] matrix;
	static int[][] visited;
	static List<int[]> fires;
	static int[] start;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		width = Integer.parseInt(tokens.nextToken());
		height = Integer.parseInt(tokens.nextToken());
		matrix = new char[height][width];
		visited = new int[height][width];
		fires = new ArrayList<>();
		
		for(int y = 0; y < height; y++) {
			matrix[y] = in.readLine().toCharArray();
			for(int x = 0; x < width; x++) {
				if(matrix[y][x] == '@') start = new int[] {x, y};
				else if(matrix[y][x] == '*') fires.add(new int[]{x, y});
			}
		}
	}
	
	static void solve() {
		spreadFire();
		
//		for(int y = 0; y < height; y++) {
//			for(int x = 0; x < width; x++) {
//				System.out.print(visited[y][x] + " ");
//			}
//			System.out.println();
//		}
		
		int time = navigate();
		System.out.println(time > 0 ? time : "IMPOSSIBLE");
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void spreadFire() {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		for(int[] fire : fires) {
			q.add(fire);
			int x = fire[0];
			int y = fire[1];
			
			visited[y][x] = 1;
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = visited[y][x];
			
			for(int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
				if(matrix[ny][nx] == '#' || visited[ny][nx] > 0) continue;
				
				q.add(new int[] {nx, ny});
				visited[ny][nx] = time + 1;
			}
		}
	}
	
	static int navigate() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.add(start);
		int sx = start[0];
		int sy = start[1];
		visited[sy][sx] = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = visited[y][x];
			
			for(int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= width || ny < 0 || ny >= height) return time;
				if(matrix[ny][nx] == '#' || (visited[ny][nx] > 0 && time + 1 >= visited[ny][nx])) continue;
				
				q.add(new int[] {nx, ny});
				visited[ny][nx] = time + 1;
			}
		}
		
		return -1;
	}
}