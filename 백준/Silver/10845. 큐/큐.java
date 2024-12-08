import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	init(in);
    	solve(in);
    }
    
    static int N;
    static ArrayDeque<Integer> q;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	q = new ArrayDeque<>();
    }

    static void solve(BufferedReader in) throws IOException {
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < N; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		
    		while(tokens.hasMoreTokens()) {
    			String cmd = tokens.nextToken();
    			switch(cmd) {
    			case "push" : 
    				q.add(Integer.parseInt(tokens.nextToken()));
    				break;
    			case "pop" : 
    				if(q.isEmpty()) str.append(-1);
    				else str.append(q.pop());
    				str.append("\n");
    				break;
    			case "size" : 
    				str.append(q.size());
    				str.append("\n");
    				break;
    			case "empty" : 
    				if(q.isEmpty()) str.append(1);
    				else str.append(0);
    				str.append("\n");
    				break;
    			case "front" : 
    				if(q.isEmpty()) str.append(-1);
    				else str.append(q.peek());
    				str.append("\n");
    				break;
    			case "back" : 
    				if(q.isEmpty()) str.append(-1);
    				else str.append(q.peekLast());
    				str.append("\n");
    				break;
    				default : break;
    			}
    		}
    	}
    	
    	System.out.println(str.toString());
    }
    
}


