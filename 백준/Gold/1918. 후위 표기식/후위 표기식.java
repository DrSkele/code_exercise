import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve();
    }
    static String line;
    static void init(BufferedReader in) throws IOException {
        line = in.readLine();
    }

    static void solve(){
        char[] ops = line.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < ops.length; i++) {
        	char cur = ops[i];
        	switch(cur) {
        	case '+':
        	case '-':
        	case '*':
        	case '/':
        		while(!stack.isEmpty() && (priority(cur) <= priority(stack.peek()))) {
        			str.append(stack.pop());
        		}
        		stack.add(cur);
        		break;
        	case '(':
        		stack.add(cur);
        		break;
        	case ')':
        		while(!stack.isEmpty()) {
        			char pop = stack.pop();
        			if(pop == '(') break;
        			else str.append(pop);
        		}
        		break;
        	default :
        		str.append(cur);
        	}
        }
        while(!stack.isEmpty()) {
        	str.append(stack.pop());
        }
        
        System.out.println(str.toString());
    }
    
    static int priority(char op) {
    	if(op == '*' || op == '/') return 1;
    	else if(op == '+' || op == '-') return 0;
    	else return -1;
    }
}


