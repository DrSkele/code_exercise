import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int height;
    static int width;
    static int[][] matrix;
    static List<int[]> virus;
    static List<int[]> space;
    static int infected;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	
    	height = Integer.parseInt(tokens.nextToken());
    	width = Integer.parseInt(tokens.nextToken());
    	matrix = new int[height][width];
    	virus = new ArrayList<>();
    	space = new ArrayList<>();
    	infected = Integer.MAX_VALUE;
    	for(int y = 0; y < height; y++) {
    		tokens = new StringTokenizer(in.readLine());
    		for(int x = 0; x < width; x++) {
    			int val = Integer.parseInt(tokens.nextToken());
    			matrix[y][x] = val;
    			if(val == 2) virus.add(new int[] {y, x});
    			else if(val == 0) space.add(new int[] {y, x});
    		}
    	}
    }

    static void solve(){
    	combination(0, -1);
    	System.out.println(space.size() - infected - 3);
    }
    
    static void combination(int depth, int idx) {
    	if(depth == 3) {
    		bfs();
    		return;
    	}
    	
    	for(int i = idx + 1; i < space.size(); i++) {
    		int[] cur = space.get(i);
    		matrix[cur[0]][cur[1]] = 1;
    		combination(depth+1, i);
    		matrix[cur[0]][cur[1]] = 0;
    	}
    }
    
    final static int[] dx = { +1, 0, -1, 0 };
    final static int[] dy = { 0, +1, 0, -1 };
    static void bfs() {
    	
    	ArrayDeque<int[]> q = new ArrayDeque();
    	
    	boolean[][] visited = new boolean[height][width];
    	for(int[] v : virus) {
    		q.add(v);
    		visited[v[0]][v[1]] = true;
    	}
    	int cnt = 0;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int x = cur[1];
    		int y = cur[0];
    		
    		if(cnt > infected) break;
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			
    			if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
    			if(visited[ny][nx] || matrix[ny][nx] == 1) continue;
    			
    			q.add(new int[] {ny, nx});
    			visited[ny][nx] = true;
    			cnt++;
    		}
    	}
    	
    	infected = Math.min(infected, cnt);
    }
}
