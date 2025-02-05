import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static int start;
	static int dest;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		start = Integer.parseInt(tokens.nextToken());
		dest = Integer.parseInt(tokens.nextToken());
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		int[] coord = new int[100_001];
		
		Arrays.fill(coord, -1);
		
		PriorityQueue<Warp> q = new PriorityQueue<>();
		
		q.add(new Warp(start, 0));
		coord[start] = 0;
		
		while(!q.isEmpty()) {
			Warp cur = q.poll();
			
			//System.out.println(cur.dest + " " + cur.time);
			
			if(cur.dest == dest) break;
			
			if(cur.dest * 2 < coord.length && (coord[cur.dest * 2] == -1 || coord[cur.dest * 2] > cur.time)) {
				q.add(new Warp(cur.dest * 2, cur.time));
				coord[cur.dest * 2] = cur.time;
			}
			if(cur.dest-1 >= 0 && coord[cur.dest-1] == -1) {
				q.add(new Warp(cur.dest-1, cur.time+1));
				coord[cur.dest -1] = cur.time+1;
			}
			if(cur.dest+1 < coord.length && coord[cur.dest+1] == -1) {
				q.add(new Warp(cur.dest+1, cur.time+1));
				coord[cur.dest+1] = cur.time+1;
			}
		}
		
		System.out.println(coord[dest]);
	}
	
	static class Warp implements Comparable<Warp>{
		int dest;
		int time;
		public Warp(int d, int t) {
			dest = d;
			time = t;
		}
		
		public int compareTo(Warp w) {
			if(this.time < w.time) return -1;
			else if(this.time > w.time) return 1;
			else return 0;
		}
	}
}
