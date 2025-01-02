import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int size;
	static char[][] matrix;
	static void input(BufferedReader in) throws IOException {
		size = Integer.parseInt(in.readLine());
		matrix = new char[size][size];
		
		for(int y = 0; y < size; y++) {
			matrix[y] = in.readLine().toCharArray();
		}
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void solve() {
		
		int max = 0;
		
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				char cur = matrix[y][x];
				
				for(int i = 0; i < dx.length; i++) {
					max = Math.max(max, countCandy(cur, x, y, i));
				}
			}
		}
		
		System.out.println(max);
	}
	
	static int countCandy(char cur, int x, int y, int dir) {
		int cnt = 1;
		boolean swapped = false;
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		while(nx >= 0 && nx < size && ny >= 0 && ny < size) {
			char next = matrix[ny][nx];
			
			if(next == cur) {
				cnt++;
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}
			
			if(swapped) {
				break;
			}
			
			int rx = nx + dx[(dir + 1) % 4];
			int ry = ny + dy[(dir + 1) % 4];
			
			if(!(rx < 0 || rx >= size || ry < 0 || ry >= size) && matrix[ry][rx] == cur) {
				cnt++;
				swapped = true;
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}
			
			int lx = nx + dx[(dir + 3) % 4];
			int ly = ny + dy[(dir + 3) % 4];
			
			if(!(lx < 0 || lx >= size || ly < 0 || ly >= size) && matrix[ly][lx] == cur) {
				cnt++;
				swapped = true;
				nx += dx[dir];
				ny += dy[dir];
				continue;
			}
			
			int fx = nx + dx[dir];
			int fy = ny + dy[dir];
			
			if(!(fx < 0 || fx >= size || fy < 0 || fy >= size) && matrix[fy][fx] == cur) {
				cnt++;
				swapped = true;
			}
			
			break;
		}
		
		return cnt;
	}
}