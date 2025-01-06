import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int child;
	static int treat;
	static int[] sticks;
	static int shared;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		child = Integer.parseInt(tokens.nextToken());
		treat = Integer.parseInt(tokens.nextToken());
		sticks = new int[treat];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < treat; i++) {
			sticks[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(sticks);
		shared = 0;
	}
	
	static void solve() {
		int length = findLength();
		
		System.out.println(shared >= child ? length : 0);
	}
	
	static int findLength() {
		int left = 1;
		int right = sticks[sticks.length - 1];
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			int cnt = 0;
			for(int i = sticks.length - 1; i >= 0; i--) {
				int cur = sticks[i];
				if(cur < mid) break;
				
				cnt += cur / mid;
			}
			
			if(cnt >= child) {
				shared = cnt;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}
}