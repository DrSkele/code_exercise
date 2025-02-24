import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve();
	}
	
	static int length;
	static int nChar;
	static char[] chars;
	static char[] key;
	static StringBuilder str;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		length = Integer.parseInt(tokens.nextToken());
		nChar = Integer.parseInt(tokens.nextToken());
		chars = new char[nChar];
		
		tokens = new StringTokenizer(in.readLine());
		for(int i = 0; i < nChar; i++) {
			chars[i] = tokens.nextToken().charAt(0);
		}
		
		Arrays.sort(chars);
		key = new char[length];
		str = new StringBuilder();
	}
	
	static void solve() {
		combination(0, 0, 0, 0);
		System.out.println(str.toString());
	}
	
	static void combination(int idx, int vowel, int conson, int len) {
		if(len == length) {
			if(vowel >= 1 && conson >= 2) {
				for(char ch : key) {
					str.append(ch);
				}
				str.append("\n");
			}
			
			return;
		}
		
		for(int i = idx; i < nChar; i++) {
			boolean isVowel = isVowel(chars[i]);
			
			key[len] = chars[i];
			combination(i+1, isVowel ? vowel + 1 : vowel, isVowel ? conson : conson+1, len+1);
		}
	}
	
	static boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch =='u';
	}
}