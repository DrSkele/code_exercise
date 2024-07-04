import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve();
    }
    static int ladder;
    static int snake;
    static Map<Integer, Integer> ladders;
    static Map<Integer, Integer> snakes;
    static int[] map;
    static void init(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        ladder = Integer.parseInt(tokens.nextToken());
        snake = Integer.parseInt(tokens.nextToken());
        ladders = new HashMap<>();
        snakes = new HashMap<>();
        
        for(int i = 0; i < ladder; i++) {
        	tokens = new StringTokenizer(in.readLine());
        	int from = Integer.parseInt(tokens.nextToken());
        	int to = Integer.parseInt(tokens.nextToken());
        	ladders.put(from, to);
        }
        for(int i = 0; i < snake; i++) {
        	tokens = new StringTokenizer(in.readLine());
        	int from = Integer.parseInt(tokens.nextToken());
        	int to = Integer.parseInt(tokens.nextToken());
        	snakes.put(from, to);
        }
        map = new int[101];
    }

    static void solve(){
        
    	ArrayDeque<Integer> q = new ArrayDeque<>();
    	q.add(1);
    	map[1] = 0;
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		int val = map[cur];
    		
    		for(int i = 6; i >= 1; i--) {
    			int next = cur + i;
    			
    			if(next > 100) continue;
    			if(map[next] != 0 && map[next] <= val+1) continue;
    			
    			map[next] = val+1;
    			if(ladders.containsKey(next)) next = ladders.get(next);
    			else if(snakes.containsKey(next)) next = snakes.get(next);
    			
    			//System.out.println(next);
    			
    			map[next] = val+1;
    			q.add(next);
    		}
    	}
    	
    	System.out.println(map[100]);
    }
}


