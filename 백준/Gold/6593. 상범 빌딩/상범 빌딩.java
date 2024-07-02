import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        while(true) {
        	StringTokenizer tokens = new StringTokenizer(in.readLine());
        	height = Integer.parseInt(tokens.nextToken());
            row = Integer.parseInt(tokens.nextToken());
            col = Integer.parseInt(tokens.nextToken());
            
            if(height == 0) break;
            
            init(in);
            solve();
        }    
    }
    static int height;
    static int row;
    static int col;
    static char[][][] building;
    static int [][][] visited;
    static int[] start;
    static int[] end;
    static void init(BufferedReader in) throws IOException {
        
        building = new char[height][row][col];
        visited =  new int[height][row][col];
        for(int i = 0; i < height; i++){
        	for(int j = 0; j < row; j++){
        		String line = in.readLine();
        		building[i][j] = line.toCharArray();
        		if(line.contains("S")) {
        			start = new int[] {i, j, line.indexOf("S")};
        		}
        		if(line.contains("E")) {
        			end = new int[] {i, j, line.indexOf("E")};
        		}
        	}
        	in.readLine();
        }
    }

    static final int[] dh = { +1, 0, 0, -1, 0, 0 };
    static final int[] dr = { 0, +1, 0, 0, -1, 0 };
    static final int[] dc = { 0, 0, +1, 0, 0, -1 };
    static void solve(){
        
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.add(start);
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int h = cur[0];
    		int r = cur[1];
    		int c = cur[2];
    		
    		for(int i = 0; i < 6; i++) {
    			int nh = h + dh[i];
    			int nr = r + dr[i];
    			int nc = c + dc[i];
    			
    			if(nh < 0 || nh >= height || nr < 0 || nr >= row || nc < 0 || nc >= col) continue;
    			
    			int val = visited[h][r][c];
    			
    			if(visited[nh][nr][nc] > 0 || building[nh][nr][nc] == '#') continue;
    			
    			visited[nh][nr][nc] = val + 1;
    			q.add(new int[] {nh, nr, nc});
    		}
    	}
    	
//    	for(int i = 0; i < height; i++){
//        	for(int j = 0; j < row; j++){
//        		for(int k = 0; k < col; k++) {
//        			System.out.print(visited[i][j][k]);
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
//        }
    	
    	int result = visited[end[0]][end[1]][end[2]];
    	if(result > 0) System.out.println("Escaped in " +result +" minute(s).");
    	else System.out.println("Trapped!");
    }
}


