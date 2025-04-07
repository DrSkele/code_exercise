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
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void solve(){
		
		int cnt = 0;
		boolean[][] visited = new boolean[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(matrix[i][j] == 1 || visited[i][j]) continue;
				
				traverse(i, j, visited);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	static void traverse(int y, int x, boolean[][] visited) {
		
		ArrayDeque<Coord> q = new ArrayDeque<>();
		
		q.add(new Coord(y, x));
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			Coord cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(ny < 0) ny += height;
				else if(ny >= height) ny %= height;
				
				if(nx < 0) nx += width;
				else if(nx >= width) nx %= width;
				
				if(matrix[ny][nx] == 1 || visited[ny][nx]) continue;
				
				q.add(new Coord(ny, nx));
				visited[ny][nx] = true;
			}
		}
		
	}
	
	static class Coord{
		int y;
		int x;
		public Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}