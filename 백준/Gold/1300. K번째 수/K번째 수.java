import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int N;
	static int goal;
	static void input(BufferedReader in) throws IOException {		
		N = Integer.parseInt(in.readLine());
		goal = Integer.parseInt(in.readLine());
	}
	
	static void solve() {
		System.out.println(findNumber());
	}
	
	static int findNumber() {
		int result = 0;
		
		int left = 1;
		int right = goal;
		
		while(left <= right) {
			int mid = left + (right - left) / 2;
			int idx = countSmaller(mid);
			
			if(idx >= goal) {
				result = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return result;
	}
	
	static int countSmaller(int num) {
		int cnt = 0;
		
		for(int i = 1; i <= N; i++) {
			cnt += Math.min(num / i, N);
		}
		
		return cnt;
	}
}