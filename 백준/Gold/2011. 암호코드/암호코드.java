import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);			
	}
	
	static String code;
	public static void init(BufferedReader in) throws IOException{
		code = in.readLine();
	}
	
	public static void solve(BufferedReader in) throws IOException {
		
		if(code.charAt(0) == '0') {
			System.out.println(0);
			return;
		}
		
		long[] dp = new long[code.length()+1];
		
		dp[0] = 1;
		dp[1] = 1;
		
		boolean isValid = true;
		
		for(int i = 2; i < code.length()+1; i++) {
			
			int num = Integer.parseInt(code.substring(i-2, i));
			
			if(num == 0 || (code.charAt(i-1) == '0' && num > 20)) {
				isValid = false;
				break;
			}
			if(num <= 26 && code.charAt(i-1) == '0') {
				dp[i] = dp[i-2];
			} else if(num > 26 || code.charAt(i-2) == '0') {
				dp[i] = dp[i-1];
			} else {
				dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000;
			}
		}
		
		System.out.println(isValid ? dp[code.length()] % 1_000_000 : 0);
	}
}
