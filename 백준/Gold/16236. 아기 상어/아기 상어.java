
import java.io.*;
import java.util.*;

// 다익스트라로 먹을 수 있는 가장 가까운 물고기 탐색
// 상단 좌측 우선이므로 dx dy를 상단 좌측부터 시작
// 먹은 갯수 카운트 -> 그거 따라 크기 업데이트


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[][] matrix;
	static int fish;
	static Shark shark;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		matrix = new int[N][N];
		fish = 0;
		for(int y = 0; y < N; y++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				int val = Integer.parseInt(tokens.nextToken());
				if(0 < val) {
					if(val == 9) {
						shark = new Shark(x, y);
						val = 0;
					} else {
						fish++;
						
					}
				}
				matrix[y][x] = val;
			}
		}
	}
	
	static void solve() {
		
		int sum = 0;
		int cnt = 0;
		for(int i = 0; i < fish; i++) {
			int time = hunt();
			if(time == 0) break;
			
			sum += time;
			cnt++;
			if(cnt == shark.size) {
				cnt = 0;
				shark.size++;
			}
		}
		System.out.println(sum);
	}
	
	static final int[] dx = { 0, -1, +1, 0 };
	static final int[] dy = { -1, 0, 0, +1 };
	static int hunt() {
		
		int[][] visited = new int[N][N];
		PriorityQueue<Path> q = new PriorityQueue<>(new Comparator<Path>() {
			@Override
			public int compare(Path p1, Path p2) {
				if(p1.cost < p2.cost) return -1;
				else if(p1.cost > p2.cost) return 1;
				else {
					if(p1.y < p2.y) return -1;
					else if(p1.y > p2.y) return 1;
					else {
						if(p1.x < p2.x) return -1;
						else if(p1.x > p2.x) return 1;
						else return 0;
					}
				}
			}
		});
		
		q.add(new Path(shark.x, shark.y, 0));
		visited[shark.y][shark.x] = 1;
		
		while(!q.isEmpty()) {
			Path cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int fish = matrix[y][x];
			int val = visited[y][x];
			
			if(0 < fish && fish < shark.size) {
				shark.x = x;
				shark.y = y;
				matrix[y][x] = 0;
				//System.out.println(x + " " + y + " : " + (visited[y][x] - 1));
				return visited[y][x]-1;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				int nVal = visited[ny][nx]; 
				if(0 < nVal && nVal <= val + 1) continue;
				
				int nfish = matrix[ny][nx];
				if(nfish > shark.size) continue;
				
				q.add(new Path(nx, ny, val+1));
				visited[ny][nx] = val + 1;
			}
		}
		return 0;
	}
	static class Path{
		int x;
		int y;
		int cost;
		public Path(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	
	static class Shark{
		public int x;
		public int y;
		public int size;
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
			size = 2;
		}
	}
}


