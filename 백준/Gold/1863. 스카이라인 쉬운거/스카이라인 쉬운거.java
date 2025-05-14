import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int nLine;
    static int[] lines;
    static void input(BufferedReader in) throws IOException {
    	nLine = Integer.parseInt(in.readLine());
    	
    	lines = new int[nLine];
    	for(int i = 0; i < lines.length; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		int x = Integer.parseInt(tokens.nextToken());
    		int y = Integer.parseInt(tokens.nextToken());
    		
    		lines[i] = y;
    	}
    }
    
    static void solve() {
    	
    	int cnt = 0;
    	
    	Stack<Integer> stack = new Stack<>();
    	
    	for(int i = 0; i < lines.length; i++) {
    		
    		int cur = lines[i];
    		
    		if(cur == 0) {
    			while(!stack.isEmpty()) {
    				//System.out.println(stack.peek());
    				stack.pop();
    				cnt++;
    			}
    		} else if(stack.isEmpty() || stack.peek() < cur) {
    			stack.push(cur);
    		} else {
    			while(!stack.isEmpty() && stack.peek() > cur) {
    				//System.out.println(stack.peek());
    				stack.pop();
    				cnt++;
    			}
    			
    			if(stack.isEmpty() || stack.peek() != cur) stack.add(cur);
    		}
    	}
    	
    	while(!stack.isEmpty()) {
    		//System.out.println(stack.peek());
			stack.pop();
			cnt++;
		}
    	
    	System.out.println(cnt);
    }
}