import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int length;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
	}
	
	static void solve(){
		goodSequence("");
	}
	
	static void goodSequence(String str) {
		if(str.length() == length) {
			System.out.println(str);
			System.exit(0);
		}
		
		for(int i = 1; i <= 3; i++) {
			
			String temp = str+i;
			boolean isValid = true;
			for(int j = 1; j <= temp.length()/2; j++) {
				String prev = temp.substring(temp.length()-j*2, temp.length()-j);
				String next = temp.substring(temp.length()-j, temp.length());
				if(prev.equals(next)) {
					isValid = false;
					break;
				}
			}
			
			if(isValid) goodSequence(temp);
		}
	}
}