import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve(in);
		
		System.out.println(str.toString());
	}
	
	static int size;
	static int[][] matrix;
	static boolean[] visited;
	static void init(BufferedReader in) throws IOException{
		size = Integer.parseInt(in.readLine());
		StringTokenizer tokens;
		matrix = new int[size][size];
		for(int y = 0; y < size; y++) {
			tokens = new StringTokenizer(in.readLine());
			for(int x = 0; x < size; x++) {
				matrix[y][x] = Integer.parseInt(tokens.nextToken());
			}
		}
		visited = new boolean[size];
	}
	
	static void solve(BufferedReader in) throws IOException {
		
		System.out.println(traverse());
	}
	
	static long traverse() {
		
		PriorityQueue<Flow> pq = new PriorityQueue<>();
		
		pq.add(new Flow(0, 0));
		
		long sum = 0;
		while(!pq.isEmpty()) {
			Flow cur = pq.poll();
			int p = cur.p;
			
			if(visited[p]) continue;
			
			visited[p] = true;
			sum += cur.cost;
			
			for(int i = 0; i < size; i++) {
				if(visited[i]) continue;
				
				int cost = matrix[p][i];
				
				pq.add(new Flow(i, cost));
			}
		}
		return sum;
	}
	
	static class Flow implements Comparable<Flow>{
		int p;
		int cost;
		public Flow(int p, int cost) {
			this.p = p;
			this.cost = cost;
		}
		@Override
		public int compareTo(Flow f) {
			int result = 0;
			if(cost < f.cost) result = -1;
			else if(cost > f.cost) result = 1;
			return result;
		}
	}
}


