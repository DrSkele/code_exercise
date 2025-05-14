import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();   
    }
    
    static int height;
    static int width;
    static int[] block;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	height = Integer.parseInt(tokens.nextToken());
    	width = Integer.parseInt(tokens.nextToken());
    	
    	block = new int[width];
    	
    	tokens = new StringTokenizer(in.readLine());
    	for(int i = 0; i < width; i++) {
    		block[i] = Integer.parseInt(tokens.nextToken());
    	}
    }
    
    static void solve() {
    	int[] water = new int[width];
    	
    	int prev = block[0];
    	int prevIdx = 0;
    	
    	for(int i = 1; i < width; i++) {
    		int cur = block[i];
    		
    		if(prev <= cur) {
    			for(int j = i-1; j > prevIdx; j--) {
    				water[j] = prev - block[j];
    			}
    			prev = cur;
    			prevIdx = i;
    		} else {
    			for(int j = i-1; j > prevIdx; j--) {
    				water[j] = Math.max(water[j], cur - block[j]);
    			}
    		}
    	}
    	
    	int sum = 0;
    	for(int w : water) {
    		sum += w;
    	}
    	
    	System.out.println(sum);
    }
}