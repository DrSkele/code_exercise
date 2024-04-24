import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int N;
	static int M;
	static Map<Integer, String> fromNum;
	static Map<String, Integer> fromName;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		fromNum = new HashMap<>();
		fromName = new HashMap<>();
		for(int i = 1; i <= N; i++) {
			String name = in.readLine();
			fromNum.put(i, name);
			fromName.put(name, i);
		}
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < M; i++) {
			String input = in.readLine();
			try {
				int toInt = Integer.parseInt(input);
				str.append(fromNum.get(toInt)).append("\n");
			} catch (Exception e) {
				str.append(fromName.get(input)).append("\n");
			}
		}
		System.out.println(str.toString());
	}
	static void solve() {
	}
}


