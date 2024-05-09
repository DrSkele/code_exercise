import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		init();
		solve();
	}
	
	static int height;
	static int width;
	static int limit;
	static int[][] matrix;
	static int windTop;
	static int windBottom;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		limit = Integer.parseInt(tokens.nextToken());
		
		windTop = -1;
		windBottom = -1;
		matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				int val = Integer.parseInt(tokens.nextToken());
				if(val < 0) {
					if(windTop < 0) windTop = y;
					else windBottom = y;
				}
				matrix[y][x] = val;
			}
		}
	}
	
	static final int[] dy = { +1, 0, -1, 0 };
	static final int[] dx = { 0, +1, 0, -1 };
	static void solve() {
		for(int t = 0; t < limit; t++) {
			List<Dust> dusts = new ArrayList<>();
			
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(matrix[y][x] > 0) {
						dusts.add(new Dust(x, y, matrix[y][x]));
					}
				}
			}
			
			for(Dust dust : dusts) {
				
				for(int i = 0; i < 4; i++) {
					int nx = dust.x + dx[i];
					int ny = dust.y + dy[i];
					
					if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
					
					int move = dust.amount / 5;
					if(matrix[ny][nx] >= 0) {
						matrix[ny][nx] += move;
						matrix[dust.y][dust.x] -= move;
					}
				}
			}
			
			Coord wind = new Coord(0, windBottom);
			int dir = 0;
			while(dir < 4) {
				int nx = wind.x + dx[dir];
				int ny = wind.y + dy[dir];
				
				if(nx < 0 || nx >= width || ny < windBottom || ny >= height) {
					dir++;
					continue;
				}
				
				if(matrix[wind.y][wind.x] == 0 && matrix[ny][nx] > 0) {
					matrix[wind.y][wind.x] = matrix[ny][nx];
					matrix[ny][nx] = 0;
				} else if(matrix[wind.y][wind.x] < 0) {
					matrix[ny][nx] = 0;
				}
				wind.x = nx;
				wind.y = ny;
			}
			
			wind = new Coord(0, windTop);
			dir = 0;
			while(dir < 4) {
				int nx = wind.x + dx[dir];
				int ny = wind.y - dy[dir];
				
				if(nx < 0 || nx >= width || ny < 0 || ny > windTop) {
					dir++;
					continue;
				}
				
				if(matrix[wind.y][wind.x] == 0 && matrix[ny][nx] > 0) {
					matrix[wind.y][wind.x] = matrix[ny][nx];
					matrix[ny][nx] = 0;
				} else if(matrix[wind.y][wind.x] < 0) {
					matrix[ny][nx] = 0;
				}
				wind.x = nx;
				wind.y = ny;
			}
			
//			for(int y = 0; y < height; y++) {
//				for(int x = 0; x < width; x++) {
//					System.out.print(matrix[y][x] + " ");
//				}
//				System.out.println();
//			}
		}
		
		int sum = 0;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(matrix[y][x] > 0) sum += matrix[y][x];
			}
		}
		System.out.println(sum);
	}
	static class Dust extends Coord{
		int amount;
		public Dust(int x, int y, int amount) {
			super(x, y);
			this.amount = amount;
		}
	}
	static class Coord{
		int x;
		int y;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
