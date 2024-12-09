import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(in.readLine());
    	
    	for(int t = 0; t < T; t++) {
    		init(in);
    		solve(in);    		
    	}
    }
    
    static int size;
    static int[] cur;
    static int[] dest;
    static int[][] matrix;
    static void init(BufferedReader in) throws IOException {
    	size = Integer.parseInt(in.readLine());
    	cur = new int[2];
    	dest = new int[2];
    	matrix = new int[size][size];
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	cur[0] = Integer.parseInt(tokens.nextToken());
    	cur[1] = Integer.parseInt(tokens.nextToken());
    	tokens = new StringTokenizer(in.readLine());
    	dest[0] = Integer.parseInt(tokens.nextToken());
    	dest[1] = Integer.parseInt(tokens.nextToken());
    }

    static int[] dx = { +1, +2, +2, +1, -1, -2, -2, -1 };
    static int[] dy = { -2, -1, +1, +2, +2, +1, -1, -2 };
    static void solve(BufferedReader in) throws IOException {
    	
    	ArrayDeque<int[]> q = new ArrayDeque<int[]>();
    	
    	// x, y
    	q.add(new int[] {cur[0], cur[1]});
    	matrix[cur[1]][cur[0]] = 1;
    	
    	while(!q.isEmpty()) {
    		int[] now = q.poll();
    		int x = now[0];
    		int y = now[1];
    		int val = matrix[y][x];
    		
    		if(x == dest[0] && y == dest[1]) break;
    		
    		for(int i = 0; i < dx.length; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			
    			if(nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
    			if(matrix[ny][nx] > 0) continue;
    			
    			matrix[ny][nx] = val + 1;
    			q.add(new int[] {nx, ny});
    		}
    	}
    	
    	System.out.println(matrix[dest[1]][dest[0]]-1);
    }
    
}


