import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int size;
    static int length;
    static int[][] matrix;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	size = Integer.parseInt(tokens.nextToken());
    	length = Integer.parseInt(tokens.nextToken());
    	matrix = new int[size][size];
    	
    	for(int i = 0; i < size; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		for(int j = 0; j < size; j++) {
    			matrix[i][j] = Integer.parseInt(tokens.nextToken());
    		}
    	}
    }
    
    static void solve() {
    	System.out.println(horizontal() + vertical());
    }
    
    static int horizontal() {
    	boolean[][] isUsed = new boolean[size][size];
    	
    	int cnt = 0;
    	// horizontal
    	for(int i = 0; i < size; i++) {
    		
    		boolean isPath = true;
    		int prev = matrix[i][0];
    		
    		for(int j = 1; j < size; j++) {
    			int cur = matrix[i][j];
    			
    			if(Math.abs(cur - prev) > 1) {
    				isPath = false;
    				break;
    			}
    			
    			// backward
    			if(prev < cur) {
    				int slopeCnt = 0;
    				for(int k = 1; k <= length && j - k >= 0; k++) {
    					if(matrix[i][j-k] == prev && !isUsed[i][j-k]) {
    						isUsed[i][j-k] = true;
    						slopeCnt++;
    					}
    				}
    				
    				if(slopeCnt != length) {
    					isPath = false;
    					break;
    				}
    			} else if(prev > cur) { // forward
    				int slopeCnt = 0;
    				for(int k = 0; k < length && j + k < size; k++) {
    					if(matrix[i][j+k] == cur && !isUsed[i][j+k]) {
    						isUsed[i][j+k] = true;
    						slopeCnt++;
    					}
    				}
    				
    				if(slopeCnt != length) {
    					isPath = false;
    					break;
    				}
    			}
    			
    			prev = cur;
    		}
    		
    		if(isPath) cnt++;
    		else {
    			Arrays.fill(isUsed[i], false);
    		}
    	}
    	
    	return cnt;
    }
    
    static int vertical() {
    	boolean[][] isUsed = new boolean[size][size];

    	int cnt = 0;
    	// horizontal
    	for(int i = 0; i < size; i++) {
    		
    		boolean isPath = true;
    		int prev = matrix[0][i];
    		
    		for(int j = 1; j < size; j++) {
    			int cur = matrix[j][i];
    			
    			if(Math.abs(cur - prev) > 1) {
    				isPath = false;
    				break;
    			}
    			
    			// backward
    			if(prev < cur) {
    				int slopeCnt = 0;
    				for(int k = 1; k <= length && j - k >= 0; k++) {
    					if(matrix[j-k][i] == prev && !isUsed[j-k][i]) {
    						isUsed[j-k][i] = true;
    						slopeCnt++;
    					}
    				}
    				
    				if(slopeCnt != length) {
    					isPath = false;
    					break;
    				}
    			} else if(prev > cur) { // forward
    				int slopeCnt = 0;
    				for(int k = 0; k < length && j + k < size; k++) {
    					if(matrix[j+k][i] == cur && !isUsed[j+k][i]) {
    						isUsed[j+k][i] = true;
    						slopeCnt++;
    					}
    				}
    				
    				if(slopeCnt != length) {
    					isPath = false;
    					break;
    				}
    			}
    			
    			prev = cur;
    		}
    		
    		if(isPath) cnt++;
    		else {
    			for(int j = 0; j < size; j++) {
    				isUsed[j][i] = false;
    			}
    		}
    	}
    	
    	return cnt;
    }
}