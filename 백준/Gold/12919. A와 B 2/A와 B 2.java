import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static String first;
	static String second;
	static boolean isSuccess;
	static void input(BufferedReader in) throws IOException {
		first = in.readLine();
		second = in.readLine();
	}
	
	static void solve(){
		removeChar(second);
		System.out.println(isSuccess ? 1 : 0);
	}
	
	static void removeChar(String str) {
		if(str.length() == first.length()) {
			if(str.equals(first)) isSuccess = true;
			return;
		}
		
		if(str.charAt(str.length()-1) == 'A') {
			removeChar(str.substring(0, str.length()-1));
		}
		if(str.charAt(0) == 'B') {
			StringBuffer sb = new StringBuffer(str.substring(1, str.length()));
			removeChar(sb.reverse().toString());
		}
	}
}