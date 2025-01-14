import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int width;
	static int height;
	static char[] result;
	static char[][] ladder;
	static void input(BufferedReader in) throws IOException {
		width = Integer.parseInt(in.readLine());
		height = Integer.parseInt(in.readLine());
		result = in.readLine().toCharArray();
		ladder = new char[height][width];
		
		for(int i = 0; i < height; i++) {
			ladder[i] = in.readLine().toCharArray();
		}
	}
	
	static void solve() {
		char[] start = new char[width];
		for(int i = 0; i < width; i++) {
			start[i] = (char)((int)'A' + i);
		}
		
		for(int i = 0; i < height; i++) {
			char[] cmd = ladder[i];
			
			if(cmd[0] == '?') break;
			
			for(int j = 0; j < width-1; j++) {
				if(cmd[j] == '-') {
					char temp = start[j];
					start[j] = start[j+1];
					start[j+1] = temp;
				}
			}
		}
		
		for(int i = height - 1; i >= 0; i--) {
			char[] cmd = ladder[i];
			
			if(cmd[0] == '?') break;
			
			for(int j = 0; j < width-1; j++) {
				if(cmd[j] == '-') {
					char temp = result[j];
					result[j] = result[j+1];
					result[j+1] = temp;
				}
			}
		}
		
//		for(char ch : start) {
//			System.out.print(ch);
//		}
//		System.out.println();
//		for(char ch : result) {
//			System.out.print(ch);
//		}
//		System.out.println();
		
		char[] cmd = new char[width-1];
		for(int i = 0; i < width-1; i++) {
			if(start[i] == result[i+1] && start[i+1] == result[i]) {
				cmd[i] = '-';
			} else {
				cmd[i] = '*';
			}
		}
		
		for(int i = 0; i < width-1; i++) {
			if(cmd[i] == '-') {
				char temp = start[i];
				start[i] = start[i+1];
				start[i+1] = temp;
			}
		}
		
		boolean isValid = true;
		for(int i = 0; i < width; i++) {
			if(start[i] != result[i]) {
				isValid = false;
				break;
			}
		}
		
		if(isValid) {
			for(char ch : cmd) {
				System.out.print(ch);
			}
		} else {
			for(int i = 0; i < width-1; i++) {
				System.out.print('x');
			}
		}
	}
}