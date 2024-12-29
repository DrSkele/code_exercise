import java.io.*;
import java.util.*;

class Main{
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		str = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int i = 0; i < T; i++) {
			input(in);
			solve();					
		}
		
		System.out.println(str.toString());
	}
	
	static char[] cmds;
	static int length;
	static ArrayDeque<Integer> deque;
	static boolean reverse;
	static void input(BufferedReader in) throws IOException {
		cmds = in.readLine().toCharArray();
		length = Integer.parseInt(in.readLine());
		String line = in.readLine();
		String[] nums = line.substring(1, line.length()-1).split(",");
		deque = new ArrayDeque<>();
		for(String num : nums) {
			if(num.length() > 0) deque.add(Integer.parseInt(num));
		}
		reverse = false;
	}
	
	static void solve() {
		for(char cmd : cmds) {
			switch(cmd) {
			case 'R' :
				reverse = !reverse;
				break;
			case 'D' :
				if(deque.isEmpty()) {
					str.append("error\n");
					return;
				}
				
				if(reverse) {
					deque.pollLast();
				} else {
					deque.poll();
				}
				break;
			}
		}
		
		str.append("[");
		while(!deque.isEmpty()) {
			int cur;
			if(reverse) cur = deque.pollLast();
			else cur = deque.poll();
			
			str.append(cur);
			
			if(!deque.isEmpty()) str.append(",");
		}
		str.append("]\n");
	}
}