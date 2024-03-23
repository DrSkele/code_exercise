
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static PriorityQueue<Meeting> booked;
	static void init(BufferedReader in) throws IOException{
		int N = Integer.parseInt(in.readLine());
		
		booked = new PriorityQueue<Meeting>(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting m1, Meeting m2) {
				if(m1.end < m2.end) return -1;
				else if(m2.end < m1.end) return 1;
				else {
					if(m1.start < m2.start) return -1;
					else if(m2.start < m1.start) return 1;
					else return 0;
				}
			}
		});
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			booked.add(new Meeting(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
	}
	
	static void solve() throws IOException {
		int latest = booked.poll().end;
		int cnt = 1;
		while(!booked.isEmpty()) {
			Meeting cur = booked.poll();
			if(cur.start < latest) continue;
			
			cnt++;
			latest = cur.end;
		}
		System.out.println(cnt);
	}
	
	static class Meeting{
		int start;
		int end;
		int streak;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
			streak = 1;
		}
	}
}


