import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		init();
		
		solve();
	}
	
	static int N;
	static void init() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		Stack<Boolean> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			String line = in.readLine();
			for(int j = 0; j < line.length(); j++) {
				char cur = line.charAt(j);
				if(cur == '(') {
					stack.add(true);
				} else {
					if(stack.isEmpty()) {
						stack.add(false);
					} else {
						if(stack.peek())
							stack.pop();
					}
				}
			}
			System.out.println(stack.isEmpty() ? "YES" : "NO");
			stack.clear();
		}
	}
	
	static void solve() {
		
	}
	
}


