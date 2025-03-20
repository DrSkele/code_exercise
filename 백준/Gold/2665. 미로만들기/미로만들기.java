import java.util.*;
import java.io.*;

public class Main {
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
		
		for(int i = 0; i < size; i++) {
			matrix[i] = in.readLine().toCharArray();
		}
	}
	
	static int[] dx = { +1, 0, -1, 0 };
	static int[] dy = { 0, +1, 0, -1 };
	static void solve(){
		
		boolean[][] visited = new boolean[size][size];
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		pq.add(new Path(0, 0));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if(cur.y == size-1 && cur.x == size-1) {
				System.out.println(cur.cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if(ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
				if(visited[ny][nx]) continue;
				
				Path next = new Path(ny, nx);
				visited[ny][nx] = true;
				
				if(matrix[ny][nx] == '0') next.cnt = cur.cnt+1;
				else next.cnt = cur.cnt;
				
				pq.add(next);
			}
		}
		
		System.out.println(0);
	}
	
	static class Path implements Comparable<Path>{
		int cnt = 0;
		int y;
		int x;
		public Path(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		public int compareTo(Path other) {
			return this.cnt - other.cnt;
		}
	}
}