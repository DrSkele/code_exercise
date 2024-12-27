import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();
	}
	
	static int height;
	static int width;
	static int cnt;
	static int[][] matrix;
	static List<Integer> answer;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		matrix = new int[height][width];
		
		for(int i = 0; i < cnt; i++) {
			tokens = new StringTokenizer(in.readLine());
			int sx = Integer.parseInt(tokens.nextToken());
			int sy = Integer.parseInt(tokens.nextToken());
			int ex = Integer.parseInt(tokens.nextToken());
			int ey = Integer.parseInt(tokens.nextToken());
			
			for(int y = sy; y < ey; y++) {
				for(int x = sx; x < ex; x++) {
					matrix[y][x] = 1;
				}
			}
		}
		
//		for(int y = 0; y < height; y++) {
//			for(int x = 0; x < width; x++) {
//				System.out.print(matrix[y][x]);
//			}
//			System.out.println();
//		}
		
		answer = new ArrayList<>();
	}
	
	static void solve() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(matrix[y][x] == 1) continue;
				
				bfs(x, y);
			}
		}
		
		Collections.sort(answer);
		StringBuilder str = new StringBuilder();
		for(int area : answer) {
			str.append(area).append(" ");
		}
		
		System.out.println(answer.size());
		System.out.println(str.toString());
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void bfs(int ix, int iy) {
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {ix, iy});
		matrix[iy][ix] = 1;
		
		int area = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			area++;
			
			for(int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
				if(matrix[ny][nx] == 1) continue;
				
				q.add(new int[] {nx, ny});
				matrix[ny][nx] = 1;
			}
		}
		
		answer.add(area);
	}
}