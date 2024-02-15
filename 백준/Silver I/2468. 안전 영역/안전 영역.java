import java.io.*;
import java.util.*;

public class Main {
	
	static int size;
	static int[][] matrix;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(in.readLine());
		StringTokenizer tokens;
		
		matrix = new int[size][size];
		
		int maxHeight = 0;
		for(int y = 0; y < size; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < size; x++) {
				int cur = Integer.parseInt(tokens.nextToken());
				if(cur > maxHeight) maxHeight = cur;
				matrix[y][x] = cur;
			}
		}
		
		int result = 0;
		for(int h = maxHeight - 1; h >= 0; h--) {
			result = Math.max(result, atHeight(h));
		}
		
		System.out.println(result);
	}
	
	static int atHeight(int height) {
		visited = new boolean[size][size];
		
		int cnt = 0;
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				if(matrix[y][x] <= height) continue;
				if(!visited[y][x]) {
					cnt++;
					bfs(x, y, height);
				}
			}
		}
		return cnt;
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	
	static void bfs(int cx, int cy, int height) {

		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {cx, cy});
		visited[cy][cx] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
				if(visited[ny][nx]) continue;
				if(matrix[ny][nx] <= height) continue;
				
				q.add(new int[] {nx, ny});
				visited[ny][nx] = true;
			}
		}
	}
}
