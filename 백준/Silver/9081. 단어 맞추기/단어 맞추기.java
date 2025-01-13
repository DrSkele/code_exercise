import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int T;
	static String[] words;
	static void input(BufferedReader in) throws IOException {
		T = Integer.parseInt(in.readLine());
		words = new String[T];
		for(int t = 0; t<T; t++) {
			words[t] = in.readLine();
		}
	}
	
	static void solve() {
		StringBuilder str = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			String cur = words[t];
			
			str.append(findNext(cur)).append("\n");
		}
		System.out.println(str.toString());
	}
	
	static String findNext(String word) {
		char[] chars = word.toCharArray();
		
		int toSwap = -1;
		for(int i = chars.length - 2; i >= 0; i--) {
			if(chars[i] < chars[i+1]) {
				toSwap = i;
				break;
			}
		}
		
		if(toSwap < 0) return word;
		
		int replace = -1;
		for(int i = chars.length - 1; i >= 0; i--) {
			if(chars[i] > chars[toSwap]) {
				replace = i;
				break;
			}
		}
		
		char temp = chars[toSwap];
		chars[toSwap] = chars[replace];
		chars[replace] = temp;
		
		Arrays.sort(chars, toSwap + 1, chars.length);
		
		StringBuilder str = new StringBuilder();
		for(char ch : chars) {
			str.append(ch);
		}
		
		return str.toString();
	}
}