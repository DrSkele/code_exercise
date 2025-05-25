import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static int[] arr;
    static void input(BufferedReader in) throws IOException {
    	length = Integer.parseInt(in.readLine());
    	arr = new int[length+1];
    	for(int i = 1; i <= length; i++) {
    		arr[i] = Integer.parseInt(in.readLine());
    	}
    }
    
    static void solve() {
    	
    	Set<Integer> set = new TreeSet<>();
    	
    	for(int i = 1; i <= length; i++) {
    		if(set.contains(i)) continue;
    		
    		Set<Integer> temp = new TreeSet<>();
    		ArrayDeque<Integer> q = new ArrayDeque<>();
    		
    		q.add(arr[i]);
    		
    		while(!q.isEmpty()) {
    			int cur = q.poll();
    			temp.add(cur);
    			if(!temp.contains(arr[cur])) q.add(arr[cur]);
    		}
    		
    		if(temp.contains(i)) {
    			set.addAll(temp);
    		}
    	}
    	
    	ArrayList<Integer> result = new ArrayList<>(set);
    	
    	Collections.sort(result);
    	
    	StringBuilder str = new StringBuilder();
    	str.append(result.size()).append('\n');
    	for(int num : result) {
    		str.append(num).append('\n');
    	}
    	System.out.println(str.toString());
    }
    
}