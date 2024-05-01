import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int size;
    static char[][] matrix;
    static boolean[][] visited;
    static boolean[][] visitedWeak;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	size = Integer.parseInt(in.readLine());
    	matrix = new char[size][size];
    	visited = new boolean[size][size];
    	visitedWeak = new boolean[size][size];
    	
    	for(int y = 0; y < size; y++) {
    		String line = in.readLine();		
    		for(int x = 0; x < size; x++) {
    			matrix[y][x] = line.charAt(x);
    		}
    	}
    }

    static void solve(){
    	int cnt = 0;
    	for(int y = 0; y < size; y++) {
    		for(int x = 0; x < size; x++) {
    			if(visited[y][x]) continue;
    			
    			cnt++;
    			bfs(y, x);
    		}
    	}
    	int cntWeak = 0;
    	for(int y = 0; y < size; y++) {
    		for(int x = 0; x < size; x++) {
    			if(visitedWeak[y][x]) continue;
    			
    			cntWeak++;
    			bfsWeak(y, x);
    		}
    	}
    	
    	System.out.println(cnt + " " + cntWeak);
    }
    static final int[] dx = { +1, 0, -1, 0 };
    static final int[] dy = { 0, +1, 0, -1 };
    static void bfs(int sy, int sx) {
    	
    	char color = matrix[sy][sx];
    	
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	
    	q.add(new int[] {sy, sx});
    	visited[sy][sx] = true;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int x = cur[1];
    		int y = cur[0];
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			
    			if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
    			if(visited[ny][nx] || matrix[ny][nx] != color) continue;
    			
    			q.add(new int[] {ny, nx});
    			visited[ny][nx] = true;
    		}
    	}
    }
    
    static void bfsWeak(int sy, int sx) {
	
    	char color = matrix[sy][sx];
    	
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	
    	q.add(new int[] {sy, sx});
    	visitedWeak[sy][sx] = true;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int x = cur[1];
    		int y = cur[0];
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			
    			if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
    			if(visitedWeak[ny][nx]) continue;
    			if(color == 'B') {
    				if(matrix[ny][nx] != color) continue;
    			} else {
    				if(matrix[ny][nx] == 'B') continue;
    			}
    			
    			q.add(new int[] {ny, nx});
    			visitedWeak[ny][nx] = true;
    		}
    	}
    }
}
