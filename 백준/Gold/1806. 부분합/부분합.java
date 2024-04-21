import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int length;
	static int goal;
	static int[] arr;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		length = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
		arr = new int[length];
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
	}
	
	static void solve() {
		
		int left = 0;
		int right = 0;
		int min = Integer.MAX_VALUE;
		int cnt = 1;
		int sum = arr[0];
		while(true) {
			//System.out.println(sum);
			if(sum < goal) {
				right++;
				if(right < length) {
					sum += arr[right];
					cnt++;
				} else {
					break;
				}
			} else {
				min = Math.min(min, cnt);
				
				sum -= arr[left];
				left++;
				cnt--;
			}
		}
		if(min == Integer.MAX_VALUE) min = 0;
		System.out.println(min);
	}
	
}


