import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static Set<Integer> all;
    static int N;
    static Set<Integer> set;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
    	all = new HashSet<>();
    	for(int i = 1; i <= 20; i++) all.add(i);
    	set = new HashSet<>();
    }

    static void solve(BufferedReader in) throws IOException {
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < N; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		String cmd = tokens.nextToken();
    		
    		
    		if(tokens.hasMoreTokens()) {
    			int num = Integer.parseInt(tokens.nextToken());
    			
    			if(cmd.equals("add")) {
    				set.add(num);
    			}
    			else if(cmd.equals("remove")) {
    				set.remove(num);
    			}
    			else if(cmd.equals("check")) {
    				str.append(set.contains(num) ? 1 : 0).append("\n");
    			}
    			else if(cmd.equals("toggle")) {
    				if(set.contains(num)) set.remove(num);
    				else set.add(num);
    			}
    			
    		} else {
    			if(cmd.equals("all")) set = new HashSet<>(all);
    			else if(cmd.equals("empty")) set.clear();
    		}
    	}
    	System.out.println(str.toString());
    }
}


