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
			int left = 0;
			int right = line.length()-1;
			
			int cnt = 0;
			
			while(left <= right) {
				char front = line.charAt(left);
				char back = line.charAt(right);
				
				if(front == back) {
					left++;
					right--;
				} else if(cnt == 0) {
					if((line.charAt(left+1) == back && isPalindrome(line.substring(left+1, right+1)) || 
							front == line.charAt(right-1) && isPalindrome(line.substring(left, right))))  {
						cnt = 1;
					} else {
						cnt = 2;
					}
					break;
				} else {
					cnt = 2;
					break;
				}
			}
			
			str.append(cnt).append("\n");
		}
		
		System.out.println(str.toString());
	}
	
	static boolean isPalindrome(String line) {
		int left = 0;
		int right = line.length()-1;
		
		while(left <= right) {
			char front = line.charAt(left);
			char back = line.charAt(right);
			
			if(front == back) {
				left++;
				right--;
			} else {
				return false;
			}
		}
		return true;
	}
}