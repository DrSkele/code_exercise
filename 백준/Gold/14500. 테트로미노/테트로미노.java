import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int height;
    static int width;
    static int[][][] matrix;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	height = Integer.parseInt(tokens.nextToken());
    	width = Integer.parseInt(tokens.nextToken());
    	
    	matrix = new int[4][][];
    	matrix[0] = new int[height][width];
    	matrix[1] = new int[width][height];
    	matrix[2] = new int[height][width];
    	matrix[3] = new int[width][height];
    	
    	for(int y = 0; y < height; y++) {
    		tokens = new StringTokenizer(in.readLine());
    		for(int x = 0; x < width; x++) {
    			int val = Integer.parseInt(tokens.nextToken());
    			matrix[0][y][x] = val;
    			matrix[1][x][height-1-y] = val;
    			matrix[2][height-1-y][width-1-x] = val;
    			matrix[3][width-1-x][y] = val;
    		}
    	}
    }

    static void solve(){
    	
    	int max = 0;
    	for(int i = 0; i < 4; i++) {
    		int[][] rect = matrix[i];
    		int rectH = rect.length;
    		for(int y = 0; y < rectH; y++) {
    			int rectW = rect[0].length;
    			for(int x = 0; x < rectW; x++) {
    				// 긴 막대
    				if(x + 3 < rectW) {
    					int val = matrix[i][y][x] + matrix[i][y][x+1] + matrix[i][y][x+2] + matrix[i][y][x+3];
    					max = Math.max(max, val);
    				}
    				// 정사각형 막대
    				if(x + 1 < rectW && y + 1 < rectH) {
    					int val = matrix[i][y][x] + matrix[i][y][x+1] + matrix[i][y+1][x] + matrix[i][y+1][x+1];
    					max = Math.max(max, val);
    				}
    				// ㄴ, ㄹ, ㅜ 자 막대
    				if(x + 2 < rectW && y + 1 < rectH) {
    					int val1 = matrix[i][y][x] + matrix[i][y][x+1] + matrix[i][y][x+2] + matrix[i][y+1][x];
    					max = Math.max(max, val1);
    					int val2 = matrix[i][y][x] + matrix[i][y][x+1] + matrix[i][y][x+2] + matrix[i][y+1][x+2];
    					max = Math.max(max, val2);
    					int val3 = matrix[i][y][x] + matrix[i][y][x+1] + matrix[i][y+1][x+1] + matrix[i][y+1][x+2];
    					max = Math.max(max, val3);
    					int val4 = matrix[i][y][x+1] + matrix[i][y][x+2] + matrix[i][y+1][x] + matrix[i][y+1][x+1];
    					max = Math.max(max, val4);
    					int val5 = matrix[i][y][x] + matrix[i][y][x+1] + matrix[i][y][x+2] + matrix[i][y+1][x+1];
    					max = Math.max(max, val5);
    				}
    			}
    		}
    	}
    	System.out.println(max);
    }
    
}
