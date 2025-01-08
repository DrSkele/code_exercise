import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int numOfCable;
	static int required;
	static long[] cables;
	static long maxLength;
	static void input(BufferedReader in) throws IOException {	
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		numOfCable = Integer.parseInt(tokens.nextToken());
		required = Integer.parseInt(tokens.nextToken());
		
		cables = new long[numOfCable];
		maxLength = 0;
		for(int i = 0; i < numOfCable; i++) {
			cables[i] = Long.parseLong(in.readLine());
			maxLength = Math.max(maxLength, cables[i]);
		}
	}
	
	static void solve() {
		System.out.println(findUpperBound());
	}
	
	static long findUpperBound() {
		long result = 0;
		long left = 1;
		long right = maxLength;
		
		while(left <= right) {
			long mid = left + (right - left)/2;
			
			if(countCables(mid) >= required) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return result;
	}
	
	static int countCables(long length) {
		int cnt = 0;
		for(int i = 0; i < cables.length; i++) {
			cnt += cables[i] / length;
		}
		return cnt;
	}
}