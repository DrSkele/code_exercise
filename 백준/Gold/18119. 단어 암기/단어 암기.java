import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input(in);
		solve(in);
	}
	
	static int nWord;
	static int nCmd;
	static int[] words;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		nWord = Integer.parseInt(tokens.nextToken());
		nCmd = Integer.parseInt(tokens.nextToken());
		
		words = new int[nWord];
		
		for(int i = 0; i < nWord; i++) {
			int mask = 0;
			String word = in.readLine();
			for(int j = 0; j < word.length(); j++) {
				mask |= (1 << (word.charAt(j) - 'a'));
			}
			words[i] = mask;
//			for(int k = 0; k < 26; k++) {
//				System.out.print((mask & (1 << k)) > 0 ? 1 : 0);
//			}
//			System.out.println();
		}
	}
	
	static void solve(BufferedReader in) throws IOException {
		
		StringBuilder str = new StringBuilder();
		int mask = (1 << 26) - 1;
		
		for(int i = 0; i < nCmd; i++) {
			String line = in.readLine();
			char cmd = line.charAt(0);
			char alphabet = line.charAt(2);
			
			switch(cmd) {
			case '1' : 
				mask &= ~(1 << (alphabet - 'a'));
				break;
			default :
				mask |= 1 << (alphabet - 'a');
				break;
			}
			
			
			int cnt = 0;
			for(int j = 0; j < nWord; j++) {
				if((words[j] & mask) == words[j]) cnt++;
			}
			str.append(cnt).append("\n");
		}
		
		System.out.println(str.toString());
	}
}