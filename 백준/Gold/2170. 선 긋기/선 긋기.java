import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int lines;
	static PriorityQueue<Line> pq;
	static void input(BufferedReader in) throws IOException {
		lines = Integer.parseInt(in.readLine());
		
		pq = new PriorityQueue<Line>(new Comparator<Line>() {
			public int compare(Line l1, Line l2) {
				if(l1.x < l2.x) return -1;
				else if(l1.x > l2.x) return 1;
				else return 0;
			}
		});
		
		for(int i = 0; i < lines; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			pq.add(new Line(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
	}
	
	static void solve(){
		Line first = pq.poll();
		int right = first.x;
		int left = first.y;
		int sum = 0;
		
		while(!pq.isEmpty()) {
			Line cur = pq.poll();
			
			if(left < cur.x) {
				sum += left - right;
				right = cur.x;
				left = cur.y;
			} else if(cur.y > left) {
				left = cur.y;
			}
		}
		
		sum += left - right;
		
		System.out.println(sum);
	}
	
	static class Line{
		int x;
		int y;
		public Line(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}