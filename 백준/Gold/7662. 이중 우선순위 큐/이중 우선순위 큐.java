import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(in.readLine());
    	for(int t = 0; t < T; t++) {
    		init(in);
    		
    		solve();    		
    	}
    }

    static int N;
    static PriorityQueue<Integer> minQ;
    static PriorityQueue<Integer> maxQ;
    static Map<Integer, Integer> cnt;
    static void init(BufferedReader in) throws IOException {
    	N = Integer.parseInt(in.readLine());
        minQ = new PriorityQueue<>();
        maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        cnt = new HashMap<>();
        for(int i = 0; i < N; i++) {
        	StringTokenizer tokens = new StringTokenizer(in.readLine());
        	
        	char cmd = tokens.nextToken().charAt(0);
        	int num = Integer.parseInt(tokens.nextToken());
        	if(cmd == 'I') {
        		minQ.add(num);
        		maxQ.add(num);
        		if(cnt.containsKey(num)) cnt.put(num, cnt.get(num)+1);
        		else cnt.put(num, 1);
        	} else {
        		if(num > 0) {
        			if(maxQ.isEmpty()) continue;
        			
        			while(maxQ.isEmpty() == false && cnt.get(maxQ.peek()) == 0) {
        				maxQ.poll();
        			}
        			if(maxQ.isEmpty() == false) {
        				int val = maxQ.poll();
        				//System.out.println(val);
        				cnt.put(val, cnt.get(val)-1);
        			}
        		} else {
        			if(minQ.isEmpty()) continue;
        			
        			while(minQ.isEmpty() == false && cnt.get(minQ.peek()) == 0) {
        				minQ.poll();
        			}
        			if(minQ.isEmpty() == false) {
        				int val = minQ.poll();
        				//System.out.println(val);
        				cnt.put(val, cnt.get(val)-1);
        			}
        		}
        	}
        }
        
    }

    static void solve(){
    	
    	if(maxQ.isEmpty() == false) {
    		while(maxQ.isEmpty() == false && cnt.get(maxQ.peek()) == 0) {
				maxQ.poll();
			}
    	}
		if(minQ.isEmpty() == false) {
			while(minQ.isEmpty() == false && cnt.get(minQ.peek()) == 0) {
				minQ.poll();
			}
		}
    	
    	
        if(minQ.isEmpty() || maxQ.isEmpty()) {
        	System.out.println("EMPTY");
        } else {
        	System.out.println(maxQ.peek() +" " + minQ.peek());
        }
    }

}
