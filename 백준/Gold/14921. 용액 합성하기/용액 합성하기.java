import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int N;
	static int[] solutions;
	public static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		solutions = new int[N];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			solutions[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			
			int match = findMatch(i);
			
			if(Math.abs(match) < Math.abs(min)) min = match;
		}
		
		System.out.println(min);
	}
	
	public static int findMatch(int idx) {
		int result = Integer.MAX_VALUE;
		int left = idx+1;
		int right = N-1;
		while(left <= right) {
			int mid = left + (right - left)/2;
			int sum = solutions[idx] + solutions[mid];
			
			if(Math.abs(sum) < Math.abs(result)) result = sum;
			
			if(sum <= 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return result;
	}
}
