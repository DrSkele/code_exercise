import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int numOfSolutions;
	static long[] solutions;
	static void input(BufferedReader in) throws IOException {
		numOfSolutions = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		solutions = new long[numOfSolutions];
		for(int i = 0; i < numOfSolutions; i++) {
			solutions[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(solutions);
	}
	
	static void solve() {
		long min = Long.MAX_VALUE;
		long[] answer = new long[3];
		
		for(int i = 0; i < solutions.length; i++) {
			long first = solutions[i];
			
			int left = 0;
			int right = solutions.length - 1;
			
			while(left < right) {
				long sum = first + solutions[left] + solutions[right];
				
				if(left != i && right != i && Math.abs(sum) < min) {
					min = Math.abs(sum);
					answer[0] = first;
					answer[1] = solutions[left];
					answer[2] = solutions[right];
				}
				
				if(sum < 0) {
					left++;
				} else {
					right--;
				}
			}
		}
		
		Arrays.sort(answer);
		
		for(long solution : answer) {
			System.out.print(solution + " ");
		}
	}
}