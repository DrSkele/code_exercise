import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static int total;
	static int goal;
	static void input(BufferedReader in) throws IOException {
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		total = Integer.parseInt(tokens.nextToken());
		goal = Integer.parseInt(tokens.nextToken());
	}
	
	static void solve(){
		int aCnt = 0;
		int bCnt = total;
		while(aCnt < total && aCnt * bCnt < goal) {
			aCnt++;
			bCnt--;
		}
		if(aCnt * bCnt < goal) {
			System.out.println(-1);
			return;
		}
		
		char[] string = new char[total];
		Arrays.fill(string, 'B');
		if(goal > 0) {			
			for(int i = 0; i < aCnt-1; i++) {
				string[i] = 'A';
			}
			
			int remain = goal - (aCnt-1)*bCnt;
			string[total - remain - 1] = 'A';
		}
		
		StringBuilder str = new StringBuilder();
		for(char ch : string) {
			str.append(ch);
		}
		
		System.out.println(str.toString());
	}
}