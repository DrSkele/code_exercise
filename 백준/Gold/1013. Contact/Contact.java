import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();		
	}
	
	static int cnt;
	static String[] lines;
	static void input(BufferedReader in) throws IOException {
		cnt = Integer.parseInt(in.readLine());
		lines = new String[cnt];
		for(int i  = 0; i < cnt; i++) {
			lines[i] = in.readLine();
		}
	}
	
	static void solve() {
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < cnt; i++) {
			String line = lines[i];
			
			int idx = line.length() - 1;
			boolean isPattern = true;
			while(idx >= 0) {
				if(line.charAt(idx) == '1') {
					int cnt1 = 0;
					
					while(idx >= 0) {
						if(line.charAt(idx) == '1') {
							cnt1++;
							idx--;
						} else {
							break;
						}
					}
					
					int cnt0 = 0;
					
					while(idx >= 0) {
						if(line.charAt(idx) == '0') {
							cnt0++;
							idx--;
						} else {
							break;
						}
					}
					
					if(cnt1 == 1 && cnt0 == 1) continue;
					if(idx >= 0 && cnt1 >= 1 && cnt0 >= 2) {
						idx--;
						continue;
					}
					
					isPattern = false;
					break;
				} else {
					isPattern = false;
					break;
				}
			}
			
			str.append(isPattern ? "YES" : "NO").append("\n");
		}
		
		System.out.println(str.toString());
	}
}