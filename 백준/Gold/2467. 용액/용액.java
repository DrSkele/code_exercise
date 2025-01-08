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
		
		int left = 0;
		int right = length - 1;
		
		while(left < right) {
			int sum = solutions[left] + solutions[right];
			
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				first = left;
				second = right;
			}
			
			if(sum > 0) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(solutions[first] + " " + solutions[second]);
	}
}