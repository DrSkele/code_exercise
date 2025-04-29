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
    
    static int length;
    static int[][] arr;
    static void input(BufferedReader in) throws IOException {
        length = Integer.parseInt(in.readLine());
        arr = new int[2][length];
        
        for(int i = 0; i < 2; i++) {
        	StringTokenizer tokens = new StringTokenizer(in.readLine());
        	for(int j = 0; j < length; j++) {
        		arr[i][j] = Integer.parseInt(tokens.nextToken());
        	}
        }
    }
    
    static void solve() {
    	
    	if(length == 1) {
    		System.out.println(Math.max(arr[0][0], arr[1][0]));
    	} else if(length == 2) {
    		System.out.println(Math.max(arr[0][0] + arr[1][1], arr[1][0] + arr[0][1]));
    	} else {
    		int[][] max = new int[2][length];
    		
    		max[0][0] = arr[0][0];
    		max[1][0] = arr[1][0];
    		
    		max[0][1] = Math.max(max[1][0] + arr[0][1], max[0][0]);
    		max[1][1] = Math.max(max[0][0] + arr[1][1], max[1][0]);
    		
    		for(int i = 2; i < length; i++) {
    			int maxFar = Math.max(max[0][i-2], max[1][i-2]);
    			max[0][i] = Math.max(maxFar, max[1][i-1]) + arr[0][i];
    			max[1][i] = Math.max(maxFar, max[0][i-1]) + arr[1][i];
    		}
    		
    		System.out.println(Math.max(max[0][length-1], max[1][length-1]));
    	}
    }
}