import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
//        solve();   
    }
    
    static int size;
    static int[][] matrix;
    static void input(BufferedReader in) throws IOException {
    	
    	int idx = 1;
    	StringBuilder str = new StringBuilder();
    	
    	while((size = Integer.parseInt(in.readLine())) != 0) {
    		matrix = new int[size][size];
    		
    		for(int i = 0; i < size; i++) {
    			StringTokenizer tokens = new StringTokenizer(in.readLine());
    			for(int j = 0; j < size; j++) {
    				matrix[i][j] = Integer.parseInt(tokens.nextToken());
    			}
    		}
    		
    		solve(idx++, str);
    	}
    	
    	System.out.println(str.toString());
    }
    
    static int[] dy = { +1, 0, -1, 0 };
    static int[] dx = { 0, +1, 0, -1 };
    static void solve(int idx, StringBuilder str) {
    	
    	boolean[][] visited = new boolean[size][size];
    	
    	PriorityQueue<Path> pq = new PriorityQueue<>();
    	
    	pq.add(new Path(0, 0, matrix[0][0]));
    	
    	while(!pq.isEmpty()) {
    		Path cur = pq.poll();
    		
    		if(visited[cur.y][cur.x]) continue;
    		visited[cur.y][cur.x] = true;
    		
    		if(cur.y == size-1 && cur.x == size-1) {
    			str.append("Problem ").append(idx).append(": ").append(cur.val).append('\n');
    			break;
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			int ny = cur.y + dy[i];
    			int nx = cur.x + dx[i];
    			
    			if(ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
    			if(visited[ny][nx]) continue;
    			
    			pq.add(new Path(ny, nx, cur.val + matrix[ny][nx]));
    		}
    	}
    }
    
    static class Path implements Comparable<Path>{
    	int y;
    	int x;
    	int val;
    	public Path(int y, int x, int val) {
    		this.y = y;
    		this.x = x;
    		this.val = val;
    	}
    	
    	public int compareTo(Path other) {
    		return Integer.compare(val, other.val);
    	}
    }
}