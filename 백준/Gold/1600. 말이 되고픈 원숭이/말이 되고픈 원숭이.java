import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve(in);
    }
    
    static int nHorse;
    static int height;
    static int width;
    static int[][] matrix;
    static int[][][] minMove;
    static void input(BufferedReader in) throws IOException {
        nHorse = Integer.parseInt(in.readLine());
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        width = Integer.parseInt(tokens.nextToken());
        height = Integer.parseInt(tokens.nextToken());
        matrix = new int[height][width];
        
        for(int i = 0; i < height; i++) {
        	tokens = new StringTokenizer(in.readLine());
        	for(int j = 0; j < width; j++) {
        		matrix[i][j] = Integer.parseInt(tokens.nextToken());
        	}
        }
        
        minMove = new int[nHorse+1][height][width];
        for(int h = 0; h <= nHorse; h++) {
        	for(int i = 0; i < height; i++) {
            	for(int j = 0; j < width; j++) {
            		minMove[h][i][j] = Integer.MAX_VALUE;
            	}
            }
        }
    }
    
    static int[] jumpy = { -2, -1, +1, +2, +2, +1, -1, -2 };
    static int[] jumpx = { +1, +2, +2, +1, -1, -2, -2, -1 };
    static int[] dy = { 0, +1, 0, -1 };
    static int[] dx = { +1, 0, -1, 0 };
    static void solve(BufferedReader in) throws IOException {
    	
    	PriorityQueue<Move> q = new PriorityQueue<>();
    	
    	q.add(new Move(0, 0, 0, 0));
    	minMove[0][0][0] = 0;
    	
    	int result = -1;
    	
    	while(!q.isEmpty()) {
    		Move cur = q.poll();
    		int y = cur.y;
    		int x = cur.x;
    		
    		if(y == height-1 && x == width-1) {
    			result = cur.moveCnt;
    			break;
    		}
    		
    		if(cur.jumpCnt < nHorse) {
    			for(int i = 0; i < jumpy.length; i++) {
    				int ny = y + jumpy[i];
    				int nx = x + jumpx[i];
    				
    				if(outOfMatrix(ny, nx)) continue;
    				if(matrix[ny][nx] == 1) continue;
    				if(minMove[cur.jumpCnt + 1][ny][nx] <= cur.moveCnt + 1) continue;
    				
    				q.add(new Move(ny, nx, cur.moveCnt + 1, cur.jumpCnt + 1));
    				minMove[cur.jumpCnt + 1][ny][nx] = cur.moveCnt + 1;
    			}
    		}
    		
    		for(int i = 0; i < dy.length; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(outOfMatrix(ny, nx)) continue;
				if(matrix[ny][nx] == 1) continue;
				if(minMove[cur.jumpCnt][ny][nx] <= cur.moveCnt + 1) continue;
				
				q.add(new Move(ny, nx, cur.moveCnt + 1, cur.jumpCnt));
				minMove[cur.jumpCnt][ny][nx] = cur.moveCnt + 1;
			}
    	}
    	
    	System.out.println(result);
    }
    
    static boolean outOfMatrix(int y, int x) {
    	return y < 0 || y >= height || x < 0 || x >= width;
    }
    
    static class Move implements Comparable<Move>{
    	int y;
    	int x;
    	int moveCnt;
    	int jumpCnt;
    	
    	public Move(int y, int x, int m, int j) {
    		this.y = y;
    		this.x = x;
    		this.moveCnt = m;
    		this.jumpCnt = j;
    	}
    	
    	public int compareTo(Move m) {
    		return Integer.compare(moveCnt, m.moveCnt);
    	}
    }
}