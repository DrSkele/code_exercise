import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
        solve(in);        	
    }
    
    static final int INF = 10_000;
    static int vert;
    static int edge;
    static boolean[][] relation;
    static int[][] dist;
    static void init(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	vert = Integer.parseInt(tokens.nextToken());
    	edge = Integer.parseInt(tokens.nextToken());
    	relation = new boolean[vert+1][vert+1];
    	dist = new int[vert+1][vert+1];
    	for(int i = 0; i < edge; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		int from = Integer.parseInt(tokens.nextToken());
    		int to = Integer.parseInt(tokens.nextToken());
    		relation[from][to] = true;
    		relation[to][from] = true;
    	}
    }

    static void solve(BufferedReader in) throws IOException {
    	for(int i = 1; i <= vert; i++) {
    		for(int j = 1; j <= vert; j++) {
    			if(i == j) dist[i][j] = 0;
    			else if(relation[i][j] || relation[j][i]) dist[i][j] = 1;
    			else dist[i][j] = INF;
    		}
    	}
    	
    	for(int k = 1; k <= vert; k++) {
    		for(int i = 1; i <= vert; i++) {
    			for(int j = 1; j <= vert; j++) {
    				dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    			}
    		}
    	}
    	
    	int idx = 1;
    	int min = Integer.MAX_VALUE;
    	for(int i = 1; i <= vert; i++) {
    		int sum = 0;
    		for(int j = 1; j <= vert; j++) {
    			if(dist[i][j] >= INF) continue;
    			sum += dist[i][j];
    		}
    		if(sum < min) {
    			min = sum;
    			idx = i;
    		}
    	}
    	
    	System.out.println(idx);
    }
    
}


