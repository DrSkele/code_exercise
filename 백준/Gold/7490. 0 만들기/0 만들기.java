import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int test;
    static int[] num;
    static void input(BufferedReader in) throws IOException {
    	test = Integer.parseInt(in.readLine());
    	
    	num = new int[test];
    	for(int i = 0; i < test; i++) {
    		num[i] = Integer.parseInt(in.readLine());
    	}
    }
    
    static void solve() {
    	StringBuilder str = new StringBuilder();
    	
    	for(int t = 0; t < test; t++) {
    		char[] op = new char[num[t]-1];
    		makeNum(0, op, str);
    		str.append('\n');
    	}
    	
    	System.out.println(str.toString());
    }
    
    static void makeNum(int idx, char[] op, StringBuilder str) {
    	if(idx == op.length) {
    		int sum = 0;
    		
    		ArrayDeque<Integer> arr = new ArrayDeque<>();
    		ArrayDeque<Character> ops = new ArrayDeque<>();
    		arr.add(1);
    		for(int i = 0; i < op.length; i++) {
    			if(op[i] == ' ') {
    				int last = arr.pollLast();
    				arr.add(last * 10 + (i+2));
    			} else {
    				ops.add(op[i]);
    				arr.add(i+2);
    			}
    		}
    		
    		sum += arr.poll();
    		while(!arr.isEmpty()) {
    			switch(ops.poll()) {
    			case '+' : sum += arr.poll(); break;
    			default : sum -= arr.poll(); break;
    			}
    		}
    		
    		if(sum == 0) {
    			str.append(1);
    			for(int i = 0; i < op.length; i++) {
    				str.append(op[i]).append(i+2);
    			}
    			str.append('\n');
    		}
    		return;
    	}
    	
    	op[idx] = ' ';
    	makeNum(idx+1, op, str);
    	op[idx] = '+';
    	makeNum(idx+1, op, str);
    	op[idx] = '-';
    	makeNum(idx+1, op, str);
    }
}