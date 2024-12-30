import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int days;
	static Meeting[] meetings;
	static int[] max;
	static void input(BufferedReader in) throws IOException {
		days = Integer.parseInt(in.readLine());
		meetings = new Meeting[days];
		for(int i = 0; i < days; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine());
			int time = Integer.parseInt(tokens.nextToken());
			int pay = Integer.parseInt(tokens.nextToken());
			
			meetings[i] = new Meeting(time, pay);
		}
		max = new int[days+1];
	}
	
	static void solve() {
		for(int d = days-1; d >= 0; d--) {
			Meeting cur = meetings[d];
			int time = cur.time;
			int pay = cur.pay;
			
			int remainingTime = days - d;
			if(time > remainingTime) {
				max[d] = max[d+1];
			} else {
				max[d] = Math.max(max[d+1], pay + max[d + time]);				
			}
		}
		
//		for(int i = 0; i < days; i++) {
//			System.out.print(max[i] + " ");
//		}
		
		System.out.println(max[0]);
	}
	
	static class Meeting{
		int time;
		int pay;
		
		public Meeting(int t, int p) {
			time = t;
			pay = p;
		}
	}
}