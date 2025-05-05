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
    	int[] prev = new int[10_000];
    	char[] cmd = new char[10_000];
    	ArrayDeque<Integer> pq = new ArrayDeque<>();
    	
    	visited[from] = true;
    	pq.add(from);
    	prev[from] = -1;
    	
    	while(!pq.isEmpty()) {
    		int cur = pq.poll();
    		
    		if(cur == to) {
    			StringBuilder str = new StringBuilder();
    			int pivot = cur;
    			while(pivot != from) {
    				str.append(cmd[pivot]);
    				pivot = prev[pivot];
    			}
    			str.reverse();
    			System.out.println(str.toString());
    			
    			break;
    		}
    		
    		// D
    		int d = (cur * 2) % 10_000;
    		if(!visited[d]) {
    			visited[d] = true;
    			prev[d] = cur;
    			cmd[d] = 'D';
    			pq.add(d);
    		}
    		
    		// S
    		int s = cur > 0 ? cur-1 : 9999;
    		if(!visited[s]) {
    			visited[s] = true;
    			prev[s] = cur;
    			cmd[s] = 'S';
    			pq.add(s);
    		}
    		
    		// L
    		int top = cur / 1_000;
    		int l = ((cur * 10) % 10_000) + top;
    		if(!visited[l]) {
    			visited[l] = true;
    			prev[l] = cur;
    			cmd[l] = 'L';
    			pq.add(l);
    		}
    		
    		// R
    		int bottom = cur % 10;
    		int r = (cur / 10) + bottom * 1_000;
    		if(!visited[r]) {
    			visited[r] = true;
    			prev[r] = cur;
    			cmd[r] = 'R';
    			pq.add(r);
    		}
    	}
    }
}