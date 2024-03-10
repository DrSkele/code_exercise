import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		init(in);
		
		solve();
	}
	
	static int N;
	static int[] arr;
	static void init(BufferedReader in) throws IOException{
		N = Integer.parseInt(in.readLine());
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(arr);
	}
	
	static void solve() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(twoPoint(i, 0, N-1)) cnt++;
		}
		System.out.println(cnt);
	}
	
	static boolean twoPoint(int idx, int left, int right) {
		
		int goal = arr[idx];
		
		while(left < right) {
			if(left == idx) {
				left += 1;
				continue;
			}
			if(right == idx) {
				right -= 1;
				continue;
			}
			
			int sum = arr[left] + arr[right];
			if(sum < goal) {
				left += 1;
			} else if (sum > goal){
				right -= 1;
			} else {
				return true;
			}
		}
		return false;
	}
}


