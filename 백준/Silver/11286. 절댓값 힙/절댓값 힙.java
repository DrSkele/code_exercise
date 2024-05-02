import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int N;
    static PriorityQueue<Integer> q;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(in.readLine());
    	
    	q = new PriorityQueue<>(new Comparator<Integer>() {
    		@Override
    		public int compare(Integer a, Integer b) {
    			int absA = Math.abs(a);
    			int absB = Math.abs(b);
    			if(absA < absB) return -1;
    			else if(absA > absB) return 1;
    			else {
    				if(a < b) return -1;
    				else if(a > b) return 1;
    				else return 0;
    			}
    		}
    	});
    	
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < N; i++) {
    		int num = Integer.parseInt(in.readLine());
    		
    		if(num != 0) {
    			q.add(num);
    		} else {
    			if(q.isEmpty()) {
    				str.append(0);
    			} else {
    				str.append(q.poll());
    			}
    			str.append("\n");
    		}
    	}
    	System.out.println(str.toString());
    }

    static void solve(){
    	
    }
    
}
