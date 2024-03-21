import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int N;
	static Star[] stars;
	static boolean[] connected;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		stars = new Star[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			
			stars[i] = new Star(Double.parseDouble(tokens.nextToken()), Double.parseDouble(tokens.nextToken()));
		}
		connected = new boolean[N];
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		PriorityQueue<Line> pq = new PriorityQueue<>(new Comparator<Line>() {
			@Override
			public int compare(Line l1, Line l2) {
				if(l1.length < l2.length) return -1;
				else if(l1.length > l2.length) return +1;
				else return 0;
			}
		});
		pq.add(new Line(0, 0));
		double sum = 0;
		int cnt = 0;
		while(!pq.isEmpty() && cnt < N) {
			Line l = pq.poll();
			if(connected[l.star]) continue;
			connected[l.star] = true;
			sum += l.length;
			cnt++;
			
			for(int i = 0; i < N; i++) {
				if(connected[i]) continue;
				
				double length = Math.sqrt(Math.pow(stars[l.star].x - stars[i].x, 2) + Math.pow(stars[l.star].y - stars[i].y, 2));
				
				pq.add(new Line(i, length));
			}
		}
		System.out.println(String.format("%.2f", sum));
	}
	
	static class Star{
		double x;
		double y;
		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Line{
		int star;
		double length;
		public Line(int star, double length) {
			this.star = star;
			this.length = length;
		}
	}
}
