import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(in.readLine());
        
        for(int i = 0; i < T; i++) {
        	input(in);
        	solve();        	
        }
        
    }
    
    static int from;
    static int to;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	from = Integer.parseInt(tokens.nextToken());
    	to = Integer.parseInt(tokens.nextToken());
    }
    
    static void solve() {
    	
    	boolean[] visited = new boolean[10_000];
    	PriorityQueue<Command> pq = new PriorityQueue<>();
    	
    	visited[from] = true;
    	pq.add(new Command(from, ""));
    	
    	
    	
    	while(!pq.isEmpty()) {
    		Command cur = pq.poll();
    		
    		if(cur.num == to) {
    			System.out.println(cur.str);
    			break;
    		}
    		
    		// D
    		int d = (cur.num * 2) % 10_000;
    		if(!visited[d]) {
    			visited[d] = true;
    			pq.add(new Command(d, cur.str + "D"));
    		}
    		
    		// S
    		int s = cur.num > 0 ? cur.num-1 : 9999;
    		if(!visited[s]) {
    			visited[s] = true;
    			pq.add(new Command(s, cur.str + "S"));
    		}
    		
    		// L
    		int top = cur.num / 1_000;
    		int l = ((cur.num * 10) % 10_000) + top;
    		if(!visited[l]) {
    			visited[l] = true;
    			pq.add(new Command(l, cur.str + "L"));
    		}
    		
    		int bottom = cur.num % 10;
    		int r = (cur.num / 10) + bottom * 1_000;
    		if(!visited[r]) {
    			visited[r] = true;
    			pq.add(new Command(r, cur.str + "R"));
    		}
    	}
    }
    
    static class Command implements Comparable<Command>{
    	int num;
    	String str;
    	
    	public Command(int num, String str) {
    		this.num = num;
    		this.str = str;
    	}
    	
    	public int compareTo(Command other) {
    		return Integer.compare(this.str.length(), other.str.length());
    	}
    }
}