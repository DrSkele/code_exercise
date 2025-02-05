import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		
		str = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			init(in);
			
			solve(in);			
		}
		
		System.out.println(str.toString());
	}
	
	static int nRoad;
	static int nCross;
	static int nDest;
	static int start;
	static int cross1;
	static int cross2;
	static int crossDist;
	static ArrayList<ArrayList<Path>> road;
	static int[] dest;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nCross = Integer.parseInt(tokens.nextToken());
		nRoad = Integer.parseInt(tokens.nextToken());
		nDest = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(in.readLine());
		start = Integer.parseInt(tokens.nextToken());
		cross1 = Integer.parseInt(tokens.nextToken());
		cross2 = Integer.parseInt(tokens.nextToken());
		
		road = new ArrayList<>();
		for(int i = 0; i <= nCross; i++) {
			road.add(new ArrayList<>());
		}
		
		for(int i = 0; i < nRoad; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int length = Integer.parseInt(tokens.nextToken());
			
			if((from == cross1 && to == cross2) || (from == cross2 && to == cross1)) crossDist = length;
			
			road.get(from).add(new Path(to, length));
			road.get(to).add(new Path(from, length));
		}
		
		dest = new int[nDest];
		
		for(int i = 0; i < nDest; i++) {
			dest[i] = Integer.parseInt(in.readLine());
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		startToDest();
		cross1ToDest();
		cross2ToDest();
		
		ArrayList<Integer> answer = new ArrayList<>();
		
		for(int i = 0; i < nDest; i++) {
			int fromStart = destDistance[i];
			
			int minPath = Math.min(startToCross1 + cross2Distance[i], startToCross2 + cross1Distance[i]) + crossDist;
			
			//System.out.println(dest[i] + " " + startToCross1 + " " + cross2Distance[i]);
			//System.out.println(fromStart + " " + minPath);
			
			if(fromStart != 0 && minPath <= fromStart) answer.add(dest[i]);
		}
		
		Collections.sort(answer);
		
		for(int idx : answer) {
			str.append(idx).append(" ");
		}
		str.append("\n");
	}
	
	// 시작점으로부터의 최소값 구하기
	static int[] destDistance;
	static int startToCross1;
	static int startToCross2;
	static void startToDest() {
		destDistance = new int[nDest];
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		boolean[] check = new boolean[nCross+1];
		int[] visited = new int[nCross+1];
		
		pq.add(new Path(start, 0));
		
		while(!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if(check[cur.dest]) continue;
			visited[cur.dest] = cur.length;
			check[cur.dest] = true;
			
			for(Path next : road.get(cur.dest)) {
				if(check[next.dest]) continue;
				
				pq.add(new Path(next.dest, cur.length + next.length));
			}
		}
		
//		for(int i : visited) {
//			System.out.println(i);
//		}
		
		for(int i = 0; i < nDest; i++) {
			destDistance[i] = visited[dest[i]];
		}
		startToCross1 = visited[cross1];
		startToCross2 = visited[cross2];
	}
	
	static int[] cross1Distance;
	static void cross1ToDest() {
		cross1Distance = new int[nDest];
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		boolean[] check = new boolean[nCross+1];
		int[] visited = new int[nCross+1];
		
		pq.add(new Path(cross1, 0));
		
		while(!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if(check[cur.dest]) continue;
			visited[cur.dest] = cur.length;
			check[cur.dest] = true;
			
			for(Path next : road.get(cur.dest)) {
				if(check[next.dest]) continue;
				
				pq.add(new Path(next.dest, cur.length + next.length));
			}
		}
		
		for(int i = 0; i < nDest; i++) {
			cross1Distance[i] = visited[dest[i]];
		}
	}
	
	static int[] cross2Distance;
	static void cross2ToDest() {
		cross2Distance = new int[nDest];
		
		PriorityQueue<Path> pq = new PriorityQueue<>();
		
		boolean[] check = new boolean[nCross+1];
		int[] visited = new int[nCross+1];
		
		pq.add(new Path(cross2, 0));
		
		while(!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if(check[cur.dest]) continue;
			visited[cur.dest] = cur.length;
			check[cur.dest] = true;
			
			for(Path next : road.get(cur.dest)) {
				if(check[next.dest]) continue;
				
				pq.add(new Path(next.dest, cur.length + next.length));
			}
		}
		
		for(int i = 0; i < nDest; i++) {
			cross2Distance[i] = visited[dest[i]];
		}
	}
	
	static class Path implements Comparable<Path>{
		int dest;
		int length;
		public Path(int d, int l) {
			dest = d;
			length = l;
		}
		
		public int compareTo(Path p) {
			if(this.length < p.length) return -1;
			else if(this.length > p.length) return 1;
			else return 0;
		}
	}
}
