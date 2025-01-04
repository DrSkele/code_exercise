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
	static int goal;
	static int[][] matrix; // room info
	static List<Coord> thermo; // rooms to check
	static List<Heater> heaters;
	static boolean[][][] walls; // direction of walls
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		height = Integer.parseInt(tokens.nextToken());
		width = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		
		matrix = new int[height][width];
		thermo = new ArrayList<>();
		heaters = new ArrayList<>();
		
		// x : vertical
		// y : horizontal
		for(int y = 0; y < height; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < width; x++) {
				int val = Integer.parseInt(tokens.nextToken());
				switch(val) {
				case 1:
					heaters.add(new Heater(y, x, 0));
					break;
				case 2:
					heaters.add(new Heater(y, x, 2));
					break;
				case 3:
					heaters.add(new Heater(y, x, 3));
					break;
				case 4:
					heaters.add(new Heater(y, x, 1));
					break;
				case 5:
					thermo.add(new Coord(y, x));
					break;
				}
			}
		}
		walls = new boolean[height][width][4];
		int cnt = Integer.parseInt(in.readLine());
		for(int i = 0; i < cnt; i++) {
			tokens = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(tokens.nextToken())-1;
			int x = Integer.parseInt(tokens.nextToken())-1;
			int dir = Integer.parseInt(tokens.nextToken());
			
			if(dir == 0) {
				walls[y][x][3] = true;
				walls[y-1][x][1] = true;
			} else {
				walls[y][x][0] = true;
				walls[y][x+1][2] = true;
			}
		}
	}
	
	static int[] dx = { +1, 0, -1, 0 }; // right, down, left, up
	static int[] dy = { 0, +1, 0, -1,};
	static void solve() {
		
		int cnt = 0;
		
		while(cnt <= 100) {
			blowWind();
			entropy();
			extractHeat();
			cnt++;
			if(checkTemp()) break;			
		}
		
		System.out.println(cnt);
	}
	
	static void blowWind() {
		for(Heater heater : heaters) {
			int hy = heater.y;
			int hx = heater.x;
			int dir = heater.dir;
			
			ArrayDeque<Wind> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[height][width];
			
			q.add(new Wind(hy + dy[dir], hx + dx[dir], 5));
			
			while(!q.isEmpty()) {
				Wind cur = q.poll();
				int y = cur.y;
				int x = cur.x;
				
				if(visited[y][x]) continue;
				else {
					matrix[y][x] += cur.heat;
					visited[y][x] = true;
				}
				
				if(cur.heat == 1) continue;
				
				int my = y + dy[dir];
				int mx = x + dx[dir];
				
				int ry = my + dy[(dir + 1) % 4];
				int rx = mx + dx[(dir + 1) % 4];
				
				int ly = my + dy[(dir + 3) % 4];
				int lx = mx + dx[(dir + 3) % 4];
				
				// no walls in the middle
				if(inMatrix(my, mx) && !walls[y][x][dir]) {
					q.add(new Wind(my, mx, cur.heat - 1));
				}
				// no walls on the right
				if(inMatrix(ry, rx) && !walls[y][x][(dir + 1) % 4] && !walls[ry][rx][(dir + 2) % 4]) {
					q.add(new Wind(ry, rx, cur.heat - 1));
				}
				// no walls on the left
				if(inMatrix(ly, lx) && !walls[y][x][(dir + 3) % 4] && !walls[ly][lx][(dir + 2) % 4]) {
					q.add(new Wind(ly, lx, cur.heat - 1));
				}
			}
		}
		
		printMatrix();
	}
	
	static void entropy() {
		int[][] nextMatrix = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int cur = matrix[y][x];
				
				nextMatrix[y][x] += cur;
				
				for(int i = 0; i < 4; i++) {
					// has wall on the side
					if(walls[y][x][i]) continue;
					
					int ny = y + dy[i];
					int nx = x + dx[i];
					
					if(!inMatrix(ny, nx)) continue;
					
					int next = matrix[ny][nx];
					int diff = (next - cur)/4;
					
					nextMatrix[y][x] += diff;
				}
			}
		}
		
		matrix = nextMatrix;
		
		printMatrix();
	}
	
	static void extractHeat() {
		for(int x = 0; x < width; x++) {
			if(matrix[0][x] == 0) continue;
			matrix[0][x] -= 1;
		}
		for(int x = 0; x < width; x++) {
			if(matrix[height-1][x] == 0) continue;
			matrix[height-1][x] -= 1;
		}
		for(int y = 1; y < height - 1; y++) {
			if(matrix[y][0] == 0) continue;
			matrix[y][0] -= 1;
		}
		for(int y = 1; y < height - 1; y++) {
			if(matrix[y][width-1] == 0) continue;
			matrix[y][width-1] -= 1;
		}
		
		printMatrix();
	}
	
	static boolean checkTemp() {
		boolean isAbove = true;
		for(Coord room : thermo) {
			int temp = matrix[room.y][room.x];
			if(temp < goal) {
				isAbove = false;
				break;
			}
		}
		
		return isAbove;
	}
	
	static void printMatrix() {
//		for(int y = 0; y < height; y++) {
//			for(int x = 0; x < width; x++) {
//				System.out.print(matrix[y][x] +  " ");
//			}
//			System.out.println();
//		}
	}
	
	static boolean inMatrix(int y, int x) {
		return 0 <= y && y < height && 0 <= x && x < width;
	}
	
	static class Coord{
		int y;
		int x;
		public Coord(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Heater extends Coord{
		int dir;
		public Heater(int y, int x, int dir) {
			super(y, x);
			this.dir = dir;
		}
	}
	
	static class Wind extends Coord{
		int heat;
		public Wind(int y, int x, int heat) {
			super(y, x);
			this.heat = heat;
		}
	}
}