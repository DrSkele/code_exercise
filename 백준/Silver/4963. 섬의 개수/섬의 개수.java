import java.io.*;
import java.util.*;

public class Main{
	
	static int[] dx = { +1, +1, 0, -1, -1, -1, 0, +1 };
	static int[] dy = { 0, +1, +1, +1, 0, -1, -1, -1 };
	
	static int width;
	static int height;
	static boolean[][] matrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			width = Integer.parseInt(tokens.nextToken());
			height = Integer.parseInt(tokens.nextToken());
			
			if(width == 0 && height == 0) break;
			
			matrix = new boolean[height][width];
			
			for(int y = 0; y < height; y++) {
				tokens = new StringTokenizer(in.readLine());
				for(int x = 0; x < width; x++) {
					matrix[y][x] = tokens.nextToken().charAt(0) == '1';
				}
			}
			
			int cnt = 0;
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(matrix[y][x]) {
						cnt++;
						bfs(x, y);
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	static void bfs(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		matrix[y][x] = false;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for(int i = 0; i < 8; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
				if(!matrix[ny][nx]) continue;
				
				q.add(new int[] {nx, ny});
				matrix[ny][nx] = false;
			}
		}
	}
}