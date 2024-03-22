import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve();
	}
	
	static final int size = 100_001;
	static int N;
	static int M;
	static int[] visited;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		visited = new int[size];
		for(int i = 0; i < size; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
	}
	
	public static void solve() {
		
		dijkstra(N, M);
		
	}
	
	public static void dijkstra(int start, int end) {
		
		Queue<Point> q = new PriorityQueue<Point>(Comparator.comparing(Point::getValue));
		
		q.add(new Point(start, 0));
		visited[start] = 0;
		
		int min = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(0 < cur.coord && visited[cur.coord - 1] > cur.value + 1) {
				q.add(new Point(cur.coord - 1, cur.value + 1));
				visited[cur.coord - 1] = cur.value + 1;
			}
			if(cur.coord < size - 1 && visited[cur.coord + 1] > cur.value + 1) {
				q.add(new Point(cur.coord + 1, cur.value + 1));
				visited[cur.coord + 1] = cur.value + 1;
			}
			if(cur.coord * 2 < size && visited[cur.coord * 2] > cur.value) {
				q.add(new Point(cur.coord * 2, cur.value));
				visited[cur.coord * 2] = cur.value;
			}
		}
		
		System.out.println(visited[end]);
	}
	
	static class Point{
		int coord;
		int value;
		public Point(int coord, int value) {
			this.coord = coord;
			this.value = value;
		}
		
		public int getValue() {return value;}
	}
}
