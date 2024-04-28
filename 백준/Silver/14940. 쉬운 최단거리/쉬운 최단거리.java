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
    static int[][] values;
    static int sx;
    static int sy;
    static void init() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        height = Integer.parseInt(tokens.nextToken());
        width = Integer.parseInt(tokens.nextToken());
        matrix = new int[height][width];
        values = new int[height][width];
        for(int y = 0; y < height; y++) {
        	tokens = new StringTokenizer(in.readLine());
        	for(int x = 0; x < width; x++) {
        		int val = Integer.parseInt(tokens.nextToken());
        		matrix[y][x] = val;
        		if(val == 2) {
        			sx = x;
        			sy = y;
        		}
        		values[y][x] = val == 0 ? 0 : -1;
        	}
        }
    }

    static void solve(){
        traverse();
        StringBuilder str = new StringBuilder();
        for(int y = 0; y < height; y++) {
        	for(int x = 0; x < width; x++) {
        		str.append(values[y][x]).append(" ");
        	}
        	str.append("\n");
        }
        System.out.println(str.toString());
    }

    static final int[] dx = {+1, 0, -1, 0};
    static final int[] dy = {0, +1, 0, -1};
    static void traverse() {
    	
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {sy, sx});
    	values[sy][sx] = 0;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int x = cur[1];
    		int y = cur[0];
    		int val = values[y][x];
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			
    			if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
    			if(matrix[ny][nx] == 0) continue;
    			int nVal = values[ny][nx];
    			if(0 < nVal) continue;
    			
    			q.add(new int[] {ny, nx});
    			values[ny][nx] = val+1;
    		}
    	}
    	
    	values[sy][sx] = 0;
    }
}
