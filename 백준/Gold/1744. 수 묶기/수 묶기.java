import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static int[] nums;
	static Integer[] max;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		nums = new int[length];
		for(int i = 0; i < length; i++) {
			nums[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(nums);
		max = new Integer[length];
	}
	
	static void solve(){
		System.out.println(sum(length-1));
	}
	
	static int sum(int idx) {
		if(idx < 0) return 0;
		if(max[idx] != null) return max[idx];
		if(idx == 0) return nums[0];
		
		int mul = nums[idx] * nums[idx-1] + sum(idx-2);
		int sum = nums[idx] + sum(idx-1);
		
		return max[idx] = Math.max(mul, sum);
	}
}