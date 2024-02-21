import java.io.*;
import java.util.*;

public class Main {
	
	static int height;
	static int width;
	static char[][] matrix;
	static int[][] dp;
	static boolean[][] visited;
	static boolean infinite;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		matrix = new char[height][width];
		dp = new int[height][width];
		visited = new boolean[height][width];
		
		infinite = false;
		
		for(int i = 0; i < height; i++) {
			String line = in.readLine();
			for(int j = 0; j < width; j++) {
				matrix[i][j] = line.charAt(j);
			}
		}
		
		//bfs();
		
		visited[0][0] = true;
		max = dfs(0,0);
		
//		for(int i = 0; i < height; i++) {
//			for(int j = 0; j < width; j++) {
//				System.out.print(visited[i][j]);
//				max = Math.max(max, visited[i][j]);
//			}
//			System.out.println();
//		}
		
		System.out.println(infinite ? -1 : max);
	}
	
	static int[] dx = {+1, 0, -1, 0};
	static int[] dy = {0, +1, 0, -1};
	
	static int dfs(int x, int y) {
		if(infinite) return 0;
		
		if(dp[y][x] == 0) {
			char ch = matrix[y][x];
			
			if(Character.isDigit(ch)) {
				int maxVal = 0;
				int num = Character.getNumericValue(ch);
				
				for(int i = 0; i < dx.length; i++) {
					int nx = x + dx[i] * num;
					int ny = y + dy[i] * num;
					
					if(nx < 0 || nx >= width || ny < 0 || ny >= height) {
						maxVal = Math.max(maxVal, 1);
						continue;
					}
					
					if(visited[ny][nx]) {
						infinite = true;
						break;
					} else {
						visited[ny][nx] = true;
						maxVal = Math.max(maxVal, dfs(nx, ny) + 1);
						visited[ny][nx] = false;
					}
				}
				dp[y][x] = maxVal;
			} else {
				dp[y][x] = 0;
			}
			
		}
		return dp[y][x];
	}
//	
//	static void bfs() {
//		
//		Queue<int[]> q = new LinkedList<>();
//		
//		int[] coord = {0, 0};
//		q.add(coord);
//		visited[0][0] = 1;
//		
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			int x = cur[0];
//			int y = cur[1];
//			char ch = matrix[y][x];
//			int val = visited[y][x];
//			
//			//System.out.println(y + ", "+ x);
//			
//			if(Character.isDigit(ch)) {
//				int num = Character.getNumericValue(ch);
//				for(int i = 0; i < dx.length; i++) {
//					int nx = x + dx[i] * num;
//					int ny = y + dy[i] * num;
//					
//					if(nx < 0 || nx >= width || ny < 0 || ny >= height) {
//						max = Math.max(max, val+1);
//						continue;
//					}
//					
//					if(visited[ny][nx] > 0) {
//						infinite = true;
//						break;
//					} else {
//						q.add(new int[] {nx, ny});
//						visited[ny][nx] = val+1;
//					}
//				}				
//			} else {
//				max = Math.max(max, val);
//			}
//			if(infinite) break;
//		}
//	}
}