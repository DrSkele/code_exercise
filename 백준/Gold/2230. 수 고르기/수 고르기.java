import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int N;
	static int M;
	static int[] arr;
	static int min;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		min = Integer.MAX_VALUE;
		
		Arrays.sort(arr);
	}
	
	static void solve() {
		
		int left = 0;
		int right = 0;
		
		while(left < N && right < N) {
			int onLeft = arr[left];
			int onRight = arr[right];
			int diff = onRight - onLeft;
			if(diff >= M) {
				min = Math.min(min, diff);
				left++;
			} else {
				right++;
			}
		}
		
		System.out.println(min);
	}
}