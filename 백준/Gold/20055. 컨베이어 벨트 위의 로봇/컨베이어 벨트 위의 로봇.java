import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int length;
    static int limit;
    static int[] belt;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	length = Integer.parseInt(tokens.nextToken());
    	limit = Integer.parseInt(tokens.nextToken());
    	belt = new int[length * 2];
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < length * 2; i++) {
    		belt[i] = Integer.parseInt(tokens.nextToken());
    	}
    }
    
    static void solve() {
    	
    	int totalLength = length*2;
    	
    	int start = 0;
    	int end = length-1;
    	int stage = 0;
    	int cnt = 0;
    	
    	boolean[] hasRobot = new boolean[totalLength];
    	
    	while(cnt < limit) {
    		stage++;
    		// belt rotation
    		hasRobot[end] = false;
    		start = (totalLength + (start-1))%totalLength;
    		end = (totalLength + (end-1))%totalLength;
    		
    		// robot move
    		hasRobot[end] = false;
    		for(int i = 1; i < length; i++) {
    			int cur = (totalLength + (end-i))%totalLength;
    			
    			// to next belt
    			if(hasRobot[cur]) {
    				int next = (cur+1)%totalLength;
    				if(!hasRobot[next] && belt[next] > 0) {
    					belt[next]--;
    					hasRobot[cur] = false;
    					hasRobot[next] = true;
    					
    					if(belt[next] == 0) cnt++;
    				}
    			}
    		}
    		
    		// put robot
    		if(!hasRobot[start] && belt[start] > 0) {
    			belt[start]--;
    			hasRobot[start] = true;
    			
    			if(belt[start] == 0) cnt++;
    		}
    	}
    	
    	System.out.println(stage);
    }
}