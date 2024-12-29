import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int length;
	static int[] fruits;
	static int[] inStick;
	static int max;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		fruits = new int[length];
		for(int i = 0; i < length; i++) {
			fruits[i] = Integer.parseInt(tokens.nextToken())-1;
		}
		inStick = new int[9];
		max = 0;
	}
	
	static void solve() {
		int front = 0;
		int end = 0;
		
		inStick[fruits[end]]++;
		
		while(front < length) {
			int cnt = checkStick();
			if(cnt <= 2) {
				max = Math.max(max, end-front + 1);
			}
			
			if((cnt < 2 && end + 1 < length) 
					|| (cnt == 2 && end + 1 < length && inStick[fruits[end+1]] > 0)) {
				end++;
				int newFruit = fruits[end];
				inStick[newFruit]++;
			} else {
				int prevFruit = fruits[front];
				inStick[prevFruit]--;
				front++;
			}
		}
		
		System.out.println(max);
	}
	
	static int checkStick() {
		int cnt = 0;
		for(int i = 0; i < inStick.length; i++) {
			if(inStick[i] > 0) cnt++;
		}
		return cnt;
	}
}