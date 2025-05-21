import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        solve(in);
    }
    
    static void solve(BufferedReader in) throws IOException {
    	int cnt = Integer.parseInt(in.readLine());
    	Map<String, Integer> order = new HashMap();
    	Map<String, String> map = new HashMap<>();
    	
    	int max = 0;
    	String first = "";
    	String second = "";
    	
    	for(int i = 0; i < cnt; i++) {
    		String str = in.readLine();
    		
    		if(!order.containsKey(str)) order.put(str, i);
    		
    		for(int s = str.length(); s > 0; s--) {
    			String sub = str.substring(0, s);
    			
    			if(!map.containsKey(sub)) map.put(sub, str);
    			else {
    				if(!map.get(sub).equals(str) 
    						&& (sub.length() > max 
    								|| (sub.length() == max && order.get(map.get(sub)) < order.get(first)))) {
    					first = map.get(sub);
    					second = str;
    					max = sub.length();
    				}
    				break;
    			}
    		}
    	}
    	
    	System.out.println(first);
    	System.out.println(second);
    }
}