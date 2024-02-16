import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static Class[] table;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		n = Integer.parseInt(in.readLine());
		
		table = new Class[n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			tokens = new StringTokenizer(in.readLine());
			long start = Long.parseLong(tokens.nextToken());
			long time = Long.parseLong(tokens.nextToken());
			
			table[i] = new Class(start, time);
		}
		
		Arrays.sort(table);
		
		int cnt = 1;
		PriorityQueue<Long> q = new PriorityQueue<>();
		
		q.add(table[0].time);
		
		for(int i = 1; i < n; i++) {
			Class cur = table[i];
			
			if(q.peek() <= cur.start) {
				q.poll();
				q.add(cur.time);
			} else {
				q.add(cur.time);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	
	static class Class implements Comparable<Class>{
		public long start;
		public long time;
		
		public Class(long start, long time) {
			this.start = start;
			this.time = time;
		}
		
		@Override
		public int compareTo(Class c) {
			int result = 0;
			if(this.start < c.start) result = -1;
			else if(c.start < this.start) result = 1;
			return result;
		}
	}
}
