import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static String line;
	static void input(BufferedReader in) throws IOException {
		line = in.readLine();
	}
	
	static Set<String> set;
	static void solve() {
		int max = 0;
		int len = line.length();
		
		for(int i = 0; i < len; i++) {
			max = Math.max(max, getPI(line.substring(i, len)));
		}
		
		System.out.println(max);
	}
	
	static int getPI(String str) {
		int j = 0;
		int len = str.length();
		int max = 0;
		int[] pi = new int[len];
		
		for(int i = 1; i < len; i++) {
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j - 1];
			}
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}
		}
		
		return max;
	}
}