import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int t = 1; t <= T; t++) {
			init(in);
			
			solve(t);
		}
	}
	
	static int idx;
	static String str;
	static Set<String> subString;
	static void init(BufferedReader in) throws IOException {
		idx = Integer.parseInt(in.readLine());
		str = in.readLine();
		subString = new HashSet<>();
		
		for(int i = 1; i <= str.length(); i++) {
			for(int j = 0; j <= str.length()-i; j++) {
				String sub = str.substring(j, j+i);
				subString.add(sub);
			}
		}
	}
	
	static void solve(int t) throws IOException {
		String[] strArr = new String[subString.size()];
		strArr = subString.toArray(strArr);
		Arrays.sort(strArr);
		
//		for(String s : strArr) {
//			System.out.println(s);
//		}
		
		System.out.println(String.format("#%d %s", t, strArr[idx-1]));
	}
	
}
