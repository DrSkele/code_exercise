import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int N;
    static int[][] matrix;
    static int[][] max;
    static int[][] min;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(in.readLine());
    	matrix = new int[N][3];
    	max = new int[N][3];
    	min = new int[N][3];
    	
    	for(int i = 0; i < N; i++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		for(int j = 0; j < 3; j++) {
    			matrix[i][j] = Integer.parseInt(tokens.nextToken());
    			max[i][j] = Integer.MIN_VALUE;
    			min[i][j] = Integer.MAX_VALUE;
    		}
    	}
    }

    static void solve(){
    	
    	for(int i = 0; i < 3; i++) {
    		max[0][i] = matrix[0][i];
    		min[0][i] = matrix[0][i];
    	}
    	
    	for(int i = 1; i < N; i++) {
    		max[i][0] = matrix[i][0] + Math.max(max[i-1][0], max[i-1][1]);
    		min[i][0] = matrix[i][0] + Math.min(min[i-1][0], min[i-1][1]);
    		
    		max[i][1] = matrix[i][1] + Math.max(max[i-1][0], Math.max(max[i-1][1], max[i-1][2]));
    		min[i][1] = matrix[i][1] + Math.min(min[i-1][0], Math.min(min[i-1][1], min[i-1][2]));
    		
    		max[i][2] = matrix[i][2] + Math.max(max[i-1][1], max[i-1][2]);
    		min[i][2] = matrix[i][2] + Math.min(min[i-1][1], min[i-1][2]);
    	}
    	
    	int minNum = Integer.MAX_VALUE;
    	int maxNum = Integer.MIN_VALUE;
    	for(int i = 0; i < 3; i++) {
    		minNum = Math.min(minNum, min[N-1][i]);
    		maxNum = Math.max(maxNum, max[N-1][i]);
    	}
    	System.out.println(maxNum + " " + minNum);
    }
    
}
