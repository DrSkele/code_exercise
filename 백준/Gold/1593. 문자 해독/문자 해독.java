import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int wordLength;
	static int muralLength;
	static int[] upper;
	static int[] lower;
	static String mural;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		wordLength = Integer.parseInt(tokens.nextToken());
		muralLength = Integer.parseInt(tokens.nextToken());
		
		upper = new int['Z'-'A'+1];
		lower = new int['z'-'a'+1];
		String word = in.readLine();
		for(char ch : word.toCharArray()) {
			if('A' <= ch && ch <= 'Z') {
				upper[ch-'A']++;
			} else {
				lower[ch-'a']++;
			}
		}
		mural = in.readLine();
	}
	
	static void solve() {
		for(int i = 0; i < wordLength; i++) {
			char ch = mural.charAt(i);
			
			if('A' <= ch && ch <= 'Z') {
				upper[ch-'A']--;
			} else {
				lower[ch-'a']--;
			}
		}
		int cnt = isWord() ? 1 : 0;
		
		for(int i = wordLength; i < muralLength; i++) {
			char next = mural.charAt(i);
			char prev = mural.charAt(i - wordLength);
			
			if('A' <= next && next <= 'Z') {
				upper[next-'A']--;
			} else {
				lower[next-'a']--;
			}
			
			if('A' <= prev && prev <= 'Z') {
				upper[prev-'A']++;
			} else {
				lower[prev-'a']++;
			}
			
			if(isWord()) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static boolean isWord() {
		boolean isWord = true;
		for(int i = 0; i < upper.length; i++) {
			if(upper[i] != 0 || lower[i] != 0) {
				isWord = false;
				break;
			}
		}
		return isWord;
	}
}