import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		init(in);
		
		solve(in);
	}
	
	static int size;
	static int erase;
	static ArrayList<Integer> num;
	public static void init(BufferedReader in) throws IOException{
		StringTokenizer tokens = new StringTokenizer(in.readLine());
		size = Integer.parseInt(tokens.nextToken());
		erase = Integer.parseInt(tokens.nextToken());
		num = new ArrayList<>();
	}
	
	public static void solve(BufferedReader in) throws IOException {
		String line = in.readLine();
		
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < size; i++) {
			int val = Character.getNumericValue(line.charAt(i));
			if(erase == 0) stack.push(val);
			else {
				if(!stack.isEmpty()) {
					if(val <= stack.peek()) {
						stack.push(val);
					} else {
						while(erase > 0 && !stack.isEmpty() && stack.peek() < val) {
							stack.pop();
							erase--;
						}
						stack.push(val);
					}
				} else {
					stack.push(val);
				}
			}
		}
		while(erase > 0) {
			stack.pop();
			erase--;
		}
		StringBuilder str = new StringBuilder();
		while(!stack.isEmpty()) {
			str.append(stack.pollLast());
		}
		System.out.println(str.toString());
	}
	
}
