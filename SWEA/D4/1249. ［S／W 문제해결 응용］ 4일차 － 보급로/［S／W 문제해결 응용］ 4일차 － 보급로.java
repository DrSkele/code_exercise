import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t, in);
		}
	}
	
	static int size;
	static int[][] matrix;
	static int[][] visited;
	static void init(BufferedReader in) throws IOException {
		size = Integer.parseInt(in.readLine());
		matrix = new int[size][size];
		visited = new int[size][size];
		for(int i = 0; i < size; i++) {
			String line = in.readLine();
			for(int j = 0; j < size; j++) {
				matrix[i][j] = line.charAt(j) - '0';
				visited[i][j] = -1;
			}
		}
	}
	
	static void solve(int t, BufferedReader in) throws IOException {
		
		bfs(0, 0);
		
		int result = visited[size-1][size-1];
		
		System.out.println(String.format("#%d %d", t, result));
	}
	
	static final int[] dx = { +1, 0, -1, 0 };
	static final int[] dy = { 0, +1, 0, -1 };
	static void bfs(int sx, int sy) {
		
		PriorityQueue<Path> q = new PriorityQueue<Path>(Comparator.comparing(Path::getLength));
		
		q.add(new Path(sx, sy, 0));
		visited[sy][sx] = 0;
		
		while(!q.isEmpty()) {
			Path cur = q.poll();
			if(cur.x == size-1 && cur.y == size-1) break;
			int val = visited[cur.y][cur.x];
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
				
				int nVal = visited[ny][nx];
				int addVal = matrix[ny][nx];
				if(nVal != -1 && val + addVal >= nVal) continue;
				
				q.add(new Path(nx, ny, val + addVal));
				visited[ny][nx] = val + addVal;
			}
		}
	}
	
	static class Path{
		int x;
		int y;
		int length;
		public Path(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}
		
		public int getLength() {return length;}
	}
}
