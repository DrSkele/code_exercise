import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static String[] strings;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		strings = new String[length];
		
		for(int i = 0; i < length; i++) {
			strings[i] = in.readLine();
		}
	}
	
	static void solve(){
		StringBuilder str = new StringBuilder();
		
		for(String line : strings) {
			int cnt = isPalindrome(line, 0);
			
			if(cnt > 2) cnt = 2;
			
			str.append(cnt).append("\n");
		}
		
		System.out.println(str.toString());
	}
	
	static int isPalindrome(String line, int depth) {
		if(depth >= 2) return depth;
		
		int left = 0;
		int right = line.length()-1;
		
		while(left < right) {
			char front = line.charAt(left);
			char back = line.charAt(right);
			
			if(front == back) {
				left++;
				right--;
			} else {
				return Math.min(isPalindrome(line.substring(left+1, right+1), depth+1), isPalindrome(line.substring(left, right), depth+1));
			}
		}
		return depth;
	}
}