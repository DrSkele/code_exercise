import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int nBuilding;
    static int[] height;
    static void input(BufferedReader in) throws IOException {
    	nBuilding = Integer.parseInt(in.readLine());
    	height = new int[nBuilding];
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < nBuilding; i++) {
    		height[i] = Integer.parseInt(tokens.nextToken());
    	}
    }
    
    static void solve() {
    	int max = 0;
    	
    	for(int i = 0; i < nBuilding; i++) {
    		int cur = height[i];
    		
    		int cnt = 0;
    		double prev = Double.MAX_VALUE;
    		for(int j = i-1; j >= 0; j--) {
    			double dist = i - j;
    			double tilt = (cur - height[j])/dist;
    			if(tilt < prev) {
    				prev = tilt;
    				cnt++;
    			}
    		}
    		
    		prev = Double.MAX_VALUE;
    		for(int j = i+1; j < nBuilding; j++) {
    			double dist = j - i;
    			double tilt = (cur - height[j])/dist;
    			if(tilt < prev) {
    				prev = tilt;
    				cnt++;
    			}
    		}
    		
    		max = Math.max(max, cnt);
    	}
    	
    	System.out.println(max);
    }
}