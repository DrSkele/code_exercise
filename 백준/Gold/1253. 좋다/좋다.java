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
	static int[] nums;
	static void input(BufferedReader in) throws IOException {	
		length = Integer.parseInt(in.readLine());
		nums = new int[length];
		
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < length; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		
		Arrays.sort(nums);
	}
	
	static void solve() {
		int cnt = 0;
		
		for(int i = 0; i < length; i++) {
			if(isGood(nums[i], i)) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static boolean isGood(int goal, int idx) {
		int left = idx == 0 ? idx + 1 : 0;
		int right = idx == length - 1 ? idx - 1 : length - 1;
		
		while(left < right) {
			int sum = nums[left] + nums[right];
			
			if(sum == goal) return true;
			
			if(sum < goal) {
				left++;
				if(left == idx) left++;
			} else {
				right--;
				if(right == idx) right--;
			}
		}
		
		return false;
	}
}