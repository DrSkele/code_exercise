import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(in.readLine());
        
        for(int t = 1; t <= T; t++) {
        	input(in);
        	System.out.println("Case #" + t + ": " + solve());        	
        }
        
    }
    
    static int runs;
    static int maxSeat;
    static int groups;
    static int[] group;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	runs = Integer.parseInt(tokens.nextToken());
    	maxSeat = Integer.parseInt(tokens.nextToken());
    	groups = Integer.parseInt(tokens.nextToken());
    	group = new int[groups];
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < groups; i++) {
    		group[i] = Integer.parseInt(tokens.nextToken());
    	}
    }
    
    static int solve() {
    	int sum = 0;
    	int filled = 0;
    	ArrayDeque<Integer> seat = new ArrayDeque<>();
    	ArrayDeque<Integer> line = new ArrayDeque<>();
    	for(int n : group) {
    		line.add(n);
    	}
    	
    	for(int i = 0; i < runs; i++) {
    		filled = 0;
    		while(!line.isEmpty() && line.peek() + filled <= maxSeat) {
    			int cur = line.poll();
    			filled += cur;
    			seat.add(cur);
    		}
    		
    		sum += filled;
    		
    		while(!seat.isEmpty()) {
    			line.add(seat.poll());
    		}
    	}
    	
    	return sum;
    }
    
}