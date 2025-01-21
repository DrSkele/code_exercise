import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int height;
	static int width;
	static char[][] map;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		map = new char[height][width];
		for(int i = 0; i < height; i++) {
			map[i] = in.readLine().toCharArray();
		}
	}
	
	static void solve() {
		int cnt = 0;
		for(int y = 0; y < height; y++) {
			if(findPath(y, 0)) cnt++;
		}
		System.out.println(cnt);
	}
	
	static boolean findPath(int y, int x) {
		map[y][x] = 'x';
		for(int i = -1; i <= 1; i++) {
			int ny = y + i;
			
			if(ny < 0 || ny >= height || map[ny][x+1] != '.') continue;
			
			if(x+1 == width-1 || findPath(ny, x+1)) return true;
		}
		return false;
	}
}