import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int length;
	static int[] solutions;
	static int min;
	static int first;
	static int second;
	static void input(BufferedReader in) throws IOException {	
		length = Integer.parseInt(in.readLine());
		solutions = new int[length];
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			solutions[i] = Integer.parseInt(tokens.nextToken());
		}
		
		first = 0;
		second = 0;
		min = Integer.MAX_VALUE;
	}
	
	static void solve() {
		for(int i = 0; i < length; i++) {
			int left = i + 1;
			int right = length - 1;
			
			while(left <= right) {
				int mid = (left + right)/2;
				
				int sum = solutions[i] + solutions[mid];
				
				if(Math.abs(sum) < min) {
					min = Math.abs(sum);
					first = i;
					second = mid;
				}
				
				if(sum < 0) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		System.out.println(solutions[first] + " " + solutions[second]);
	}
}