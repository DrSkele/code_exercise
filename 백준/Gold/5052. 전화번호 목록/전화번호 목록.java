import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < T; i++) {
			input(in);
			solve();					
		}
	}
	
	static int length;
	static Set<String> nums;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		nums = new TreeSet<>();
		for(int i = 0; i < length; i++) {
			nums.add(in.readLine());
		}
	}
	
	static void solve() {
		boolean isValid = true;
		Loop: for(String num : nums) {
			for(int i = 1; i < num.length(); i++) {
				if(nums.contains(num.substring(0, i))) {
					isValid = false;
					break Loop;
				}
			}
		}
		System.out.println(isValid ? "YES" : "NO");
	}
}