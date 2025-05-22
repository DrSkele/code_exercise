import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve(in);
    }
    
    static int nTower;
    static void input(BufferedReader in) throws IOException {
    	nTower = Integer.parseInt(in.readLine());
    }
    
    static void solve(BufferedReader in) throws IOException {
    	
    	StringBuilder str = new StringBuilder();
    	
    	Stack<Tower> stack = new Stack<>();
    	
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	for(int i = 1; i <= nTower; i++) {
    		int cur = Integer.parseInt(tokens.nextToken());
    		
    		while(!stack.isEmpty() && stack.peek().height < cur) {
				stack.pop();
			}
			
			if(stack.isEmpty()) {
				str.append("0 ");
			} else {
				str.append(stack.peek().idx).append(" ");
			}
			stack.add(new Tower(i, cur));
    	}
    	
    	System.out.println(str.toString());
    }
    
    static class Tower{
    	int idx;
    	int height;
    	public Tower(int idx, int height) {
    		this.idx = idx;
    		this.height = height;
    	}
    }
}