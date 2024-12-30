import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int height;
	static int width;
	static int sy;
	static int sx;
	static int dir;
	static int[][] matrix;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(in.readLine());
		sy = Integer.parseInt(tokens.nextToken());
		sx = Integer.parseInt(tokens.nextToken());
		dir = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[height][width];
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				matrix[y][x] = Integer.parseInt(tokens.nextToken()); 
			}
		}
	}
	
	static void solve() {
		
		int cnt = 0;
		
		int curX = sx;
		int curY = sy;
		int curDir = dir;
		
		while(true) {
			
			
			if(matrix[curY][curX] == 0) {
				matrix[curY][curX] = -1;
				cnt++;
				continue;
			} 

			//System.out.println(curY + " " + curX);
			
			if(dirtyNearby(curX, curY)) {
				// rotate
				curDir = (curDir + 3) % 4;
				
				int nx = curX + dx[curDir];
				int ny = curY + dy[curDir];
				//if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
				if(matrix[ny][nx] != 0) continue;
				
				curX = nx;
				curY = ny;
			} else {
				int nx = curX + dx[(curDir + 2) % 4];
				int ny = curY + dy[(curDir + 2) % 4];
				if(matrix[ny][nx] == 1) break;
				
				curX = nx;
				curY = ny;
			}
		}
		
		System.out.println(cnt);
	}
	
	static int[] dx = { 0, +1, 0, -1 };
	static int[] dy = { -1, 0, +1, 0 };
	static boolean dirtyNearby(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
			
			if(matrix[ny][nx] == 0) return true;
		}
		
		return false;
	}
}