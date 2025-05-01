import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();      
    }
    
    static String string;
    static String bomb;
    static void input(BufferedReader in) throws IOException {
        string = in.readLine();
        bomb = in.readLine();
    }
    
    static void solve() {
    	
    	Stack<Character> stack = new Stack<>();
    	
    	for(int i = 0; i < string.length(); i++) {
    		stack.add(string.charAt(i));
    		
    		if(stack.size() >= bomb.length() && find(stack)) {
    			for(int j = 0; j < bomb.length(); j++) {
    				stack.pop();
    			}
    		}
    	}
    	
    	StringBuilder str = new StringBuilder();
    	for(char ch : stack) {
    		str.append(ch);
    	}
    	
    	System.out.println(str.length() > 0 ? str.toString() : "FRULA");
    }
    
    static boolean find(Stack<Character> str) {
    	boolean isSame = true;
    	for(int i = 0; i < bomb.length(); i++) {
    		if(str.get(str.size() - bomb.length() + i) != bomb.charAt(i)) {
    			isSame = false;
    			break;
    		}
    	}
    	
    	return isSame;
    }
}