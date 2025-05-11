import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int nth;
    static Set<Long> set;
    static void input(BufferedReader in) throws IOException {
    	nth = Integer.parseInt(in.readLine());
    	set = new HashSet<>();
    }
    
    static void solve() {
    	descending(9, 0);
    	
    	ArrayList<Long> result = new ArrayList<>(set);
    	Collections.sort(result);
    	
    	long answer = nth <= result.size() ? result.get(nth-1) : -1L;
    	
    	System.out.println(answer);
    }
    
    static void descending(int num, long cur) {
    	set.add(cur);
    	
    	if(num < 0) return;
    	
    	descending(num-1, cur);
    	descending(num-1, cur*10+num);
    }
}