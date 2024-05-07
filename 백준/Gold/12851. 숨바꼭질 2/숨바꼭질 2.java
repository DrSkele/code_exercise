import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static final int size = 100_001;
    static int from;
    static int to;
    static int[] min;
    static int[] cnt;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	min = new int[size];
    	cnt = new int[size];
    	for(int i = 0; i < size; i++) {
    		min[i] = Integer.MAX_VALUE;
    		cnt[i] = 1;
    	}
    	from = Integer.parseInt(tokens.nextToken());
    	to = Integer.parseInt(tokens.nextToken());
    }

    static void solve(){
    	
    	ArrayDeque<Integer> q = new ArrayDeque<>();
    	
    	q.add(from);
    	min[from] = 0;
    	
    	while(!q.isEmpty()) {
    		int dest = q.poll();
    		int time = min[dest];
    		
    		if(dest - 1 >= 0 && time+1 <= min[dest-1] ) {
    			if(!check(dest-1, time+1)) {
    				q.add(dest-1);    				
    				min[dest-1] = time+1;
    			}
    		}
    		if(dest + 1 < size && time+1 <= min[dest+1]) {
    			if(!check(dest+1, time+1)) {
    				q.add(dest+1);    				
    				min[dest+1] = time+1;
    			}
    		}
    		if(dest * 2 < size && time+1 <= min[dest*2]) {
    			if(!check(dest*2, time+1)) {
    				q.add(dest*2);    				
    				min[dest*2] = time+1;
    			}
    		}
    	}
    	
    	System.out.println(min[to]);
    	System.out.println(cnt[to]);
    }
    
    static boolean check(int dest, int time) {
    	if(dest == to) {
			//System.out.println(time + " / " + min[dest]);
			if(time < min[dest]) {
				min[dest] = time;
				cnt[dest] = 1;
			} else if(time == min[dest]) {
				cnt[dest]++;
			}
			return true;
		}
    	return false;
    }
    
    static class Route{
    	int dest;
    	int time;
    	public Route(int dest, int time) {
    		this.dest = dest;
    		this.time = time;
    	}
    }
    
}
