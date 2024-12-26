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
	static int[][] matrix;
	static List<int[]> camera;
	static int min;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		matrix = new int[height][width];
		camera = new ArrayList<>();
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				int num = Integer.parseInt(tokens.nextToken());
				matrix[y][x] = num;
				if(0 < num && num < 6) camera.add(new int[] {y, x});
			}
		}
		min = Integer.MAX_VALUE;
	}
	
	
	static void solve() {
		check(0);
		System.out.println(min);
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void check(int idx) {
		if(idx == camera.size()) {
			int cnt = 0;
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(matrix[y][x] == 0) cnt++;
				}
			}
			if(min > cnt) {
				min = cnt;
//				for(int y = 0; y < height; y++) {
//					for(int x = 0; x < width; x++) {
//						System.out.print(matrix[y][x]);
//					}
//					System.out.println();
//				}
//				
//				System.out.println();
			}
			return;
		}
		
		int y = camera.get(idx)[0];
		int x = camera.get(idx)[1];
		int num = matrix[y][x];
		
		if(num == 1) {
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				visible(nx, ny, dx[i], dy[i]);
				check(idx + 1);
				reverse(nx, ny, dx[i], dy[i]);
			}
		} else if(num == 2) {
			for(int i = 0; i < 2; i++) {
				int nx1 = x + dx[i];
				int ny1 = y + dy[i];
				int nx2 = x + dx[(i+2)%4];
				int ny2 = y + dy[(i+2)%4];
				
				visible(nx1, ny1, dx[i], dy[i]);
				visible(nx2, ny2, dx[(i+2)%4], dy[(i+2)%4]);
				check(idx + 1);
				reverse(nx1, ny1, dx[i], dy[i]);
				reverse(nx2, ny2, dx[(i+2)%4], dy[(i+2)%4]);
			}
		} else if(num == 3) {
			for(int i = 0; i < 4; i++) {
				int nx1 = x + dx[i];
				int ny1 = y + dy[i];
				int nx2 = x + dx[(i+1)%4];
				int ny2 = y + dy[(i+1)%4];
				
				visible(nx1, ny1, dx[i], dy[i]);
				visible(nx2, ny2, dx[(i+1)%4], dy[(i+1)%4]);
				check(idx + 1);
				reverse(nx1, ny1, dx[i], dy[i]);
				reverse(nx2, ny2, dx[(i+1)%4], dy[(i+1)%4]);
			}
		} else if(num == 4) {
			for(int i = 0; i < 4; i++) {
				int nx1 = x + dx[i];
				int ny1 = y + dy[i];
				int nx2 = x + dx[(i+1)%4];
				int ny2 = y + dy[(i+1)%4];
				int nx3 = x + dx[(i+2)%4];
				int ny3 = y + dy[(i+2)%4];
				
				visible(nx1, ny1, dx[i], dy[i]);
				visible(nx2, ny2, dx[(i+1)%4], dy[(i+1)%4]);
				visible(nx3, ny3, dx[(i+2)%4], dy[(i+2)%4]);
				check(idx + 1);
				reverse(nx1, ny1, dx[i], dy[i]);
				reverse(nx2, ny2, dx[(i+1)%4], dy[(i+1)%4]);
				reverse(nx3, ny3, dx[(i+2)%4], dy[(i+2)%4]);
			}
		} else {
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				visible(nx, ny, dx[i], dy[i]);
			}
			check(idx + 1);
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				reverse(nx, ny, dx[i], dy[i]);
			}
		}
	}
	
	static void visible(int nx, int ny, int dx, int dy) {
		while(nx >= 0 && nx < width && ny >= 0 && ny < height) {
			if(matrix[ny][nx] == 6) break;
			if(matrix[ny][nx] <= 0) matrix[ny][nx] += -1;
			
			nx += dx;
			ny += dy;
		}
	}
	
	static void reverse(int nx, int ny, int dx, int dy) {
		while(nx >= 0 && nx < width && ny >= 0 && ny < height) {
			if(matrix[ny][nx] == 6) break;
			if(matrix[ny][nx] <= -1) matrix[ny][nx] += 1;
			
			nx += dx;
			ny += dy;
		}
	}
}