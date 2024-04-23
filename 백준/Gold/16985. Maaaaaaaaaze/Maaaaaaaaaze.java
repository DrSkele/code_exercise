import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int rot = 4;
	static int size = 5;
	static int[][][] maze;
	static int[][][][] matrix;
	static int[][][] visited;
	static boolean[] stacked;
	static int min;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		matrix = new int[rot][size][size][size];
		maze = new int[size][size][size];
		visited = new int[size][size][size];
		stacked = new boolean[size];
		for(int z = 0; z < size; z++) {
			for(int y = 0; y < size; y++) {				
				StringTokenizer tokens = new StringTokenizer(in.readLine());
				for(int x = 0; x < size; x++) {
					matrix[0][z][y][x] = Integer.parseInt(tokens.nextToken());
				}
			}
		}
		min = Integer.MAX_VALUE;
	}
	static void solve() {
		for(int i = 1; i < rot; i++) {
			for(int z = 0; z < size; z++) {
				matrix[i][z] = spin(i-1, z);
			}
		}
		combination(0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	static void combination(int idx) {
		if(idx == size) {

			//if(maze[0][0][0] == 1) System.out.println("ok");
			
			int route = traverse();
			if(route > 0) {
				min = Math.min(min, route);
			}
			
			return;
		}
		for(int i = 0; i < size; i++) {
			
			if(stacked[i]) continue;
			stacked[i] = true;
			for(int r = 0; r < rot; r++) {
				maze[idx] = matrix[r][i];
				combination(idx+1);
			}
			stacked[i] = false;
		}
	}
	
	static int[][] spin(int rot, int floor) {
		int[][] plane = new int[size][size];
		
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				plane[y][x] = matrix[rot][floor][size-1-x][y];
			}
		}
		return plane;
	}
	
//	static void clear() {
//		for(int z = 0; z < size; z++) {
//			for(int y = 0; y < size; y++) {				
//				for(int x = 0; x < 5; x++) {
//					visited[z][y][x] = 0;
//				}
//			}
//		}
//	}
	
	static final int[] dx = { -1, 0, 0, +1, 0, 0 };
	static final int[] dy = { 0, -1, 0, 0, +1, 0 };
	static final int[] dz = { 0, 0, -1, 0, 0, +1 };
	static int traverse() {
		
		if(maze[0][0][0] == 0 || maze[size-1][size-1][size-1] == 0) return 0;
		
		ArrayDeque<Coord> q = new ArrayDeque<>();
		q.add(new Coord(0, 0, 0));
		
		visited = new int[size][size][size];
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			Coord cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int z = cur.z;
			int cnt = visited[z][y][x];
			
			if(x == size-1 && y == size-1 && z == size-1) {
				break;
			}
			
			for(int i = 0; i < dx.length; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				
				if(nx < 0 || nx >= size || ny < 0 || ny >= size || nz < 0 || nz >= size) continue;
				if(0 < visited[nz][ny][nx] && visited[nz][ny][nx] <= cnt+1) continue;
				if(maze[nz][ny][nx] == 0) continue;
				
				q.add(new Coord(nz, ny, nx));
				visited[nz][ny][nx] = cnt+1;
			}
		}
		return visited[size-1][size-1][size-1]-1;
	}
	static class Coord{
		int x;
		int y;
		int z;
		public Coord(int z, int y, int x) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}


