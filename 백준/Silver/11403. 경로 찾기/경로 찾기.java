import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int size;
    static int[][] path;
    static int[][] dest;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	size = Integer.parseInt(in.readLine());
    	path = new int[size][size];
    	dest = new int[size][size];
    	
    	for(int y = 0; y < size; y++) {
    		StringTokenizer tokens = new StringTokenizer(in.readLine());
    		for(int x = 0; x < size; x++) {
    			path[y][x] = Integer.parseInt(tokens.nextToken());
    		}
    	}
    }

    static void solve(){
    	for(int k = 0; k < size; k++) {
    		for(int i = 0; i < size; i++) {
    			for(int j = 0; j < size; j++) {
    				if(path[i][k] == 1 && path[k][j] == 1)
    					path[i][j] = 1;
    			}
    		}
    	}
    	
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				str.append(path[i][j]).append(" ");
			}
			str.append("\n");
		}
    	System.out.println(str.toString());
    }
}
