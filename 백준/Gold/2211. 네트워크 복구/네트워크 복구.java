import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int nComputer;
	static int nLines;
	static ArrayList<Line>[] line; 
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nComputer = Integer.parseInt(tokens.nextToken());
		nLines = Integer.parseInt(tokens.nextToken());
		
		line = new ArrayList[nComputer+1];
		for(int i = 0; i < nComputer+1; i++) {
			line[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < nLines; i++) {
			tokens = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int time = Integer.parseInt(tokens.nextToken());
			
			line[from].add(new Line(to, time));
			line[to].add(new Line(from, time));
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		originalNetwork();
		System.out.println(repairedLine());
	}
	
	static int[] originalTime;
	static int[] origin;
	static void originalNetwork() {
		
		originalTime = new int[nComputer+1];
		origin = new int[nComputer+1];
		Arrays.fill(originalTime, Integer.MAX_VALUE);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.add(1);
		originalTime[1] = 0;
		origin[1] = 0;
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			int time = originalTime[cur];
			
			for(Line next : line[cur]) {
				if(originalTime[next.dest] <= time + next.time) continue;
				
				pq.add(next.dest);
				originalTime[next.dest] = time + next.time;
				origin[next.dest] = cur;
			}
		}
		
//		for(int t : origin) {
//			System.out.println(t);
//		}
	}
	
	static String repairedLine() {
		StringBuilder str = new StringBuilder();
		
		str.append(nComputer-1).append("\n");
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.add(1);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(Line next : line[cur]) {
				if(origin[next.dest] != cur) continue;
				
				q.add(next.dest);
				str.append(cur).append(" ").append(next.dest).append("\n");
			}
		}
		
		return str.toString();
	}
	
	static class Line{
		int dest;
		int time;
		public Line(int d, int t) {
			dest = d;
			time = t;
		}
	}
}
