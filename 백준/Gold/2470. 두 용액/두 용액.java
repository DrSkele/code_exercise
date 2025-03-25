import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int cnt;
	static long[] solutions;
	static void input(BufferedReader in) throws IOException {		
		cnt = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		solutions = new long[cnt];
		for(int i = 0; i < cnt; i++) {
			solutions[i] = Long.parseLong(tokens.nextToken());
		}
		Arrays.sort(solutions);
	}
	
	static long min;
	static long s1;
	static long s2;
	static void solve(){
		
		min = Long.MAX_VALUE;
		
		for(int i = 0; i < cnt; i++) {
			findMin(i);
		}
		
		System.out.println(s1 + " " + s2);
	}
	
	static void findMin(int idx) {
		
		long first = solutions[idx];
		
		int left = idx+1;
		int right = cnt-1;
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			
			long second = solutions[mid];
			
			if(Math.abs(first + second) < min) {
				min = Math.abs(first+second);
				s1 = first;
				s2 = second;
			}
			
			if(first + second < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
	
}