import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve(in);		
	}
	
	static int length;
	static int[] answer;
	static void input(BufferedReader in) throws IOException {
		length = Integer.parseInt(in.readLine());
		answer = new int[length];
	}
	
	static void solve(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		
		for(int i = 0; i < length; i++) {
			int curHeight = i+1;
			int toLeft = Integer.parseInt(tokens.nextToken());
			
			int cnt = 0;
			
			for(int j = 0; j < length; j++) {
				if(cnt == toLeft && answer[j] == 0) {
					answer[j] = curHeight;
					break;
				}
				if(answer[j] > curHeight || answer[j] == 0) cnt++;
			}
		}
		
		StringBuilder str = new StringBuilder();
		for(int height : answer) {
			str.append(height).append(" ");
		}
		System.out.println(str.toString());
	}
}