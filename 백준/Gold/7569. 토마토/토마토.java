import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	init();
    	
    	solve();  
    }

    static int X;
    static int Y;
    static int Z;
    static int[][][] box;
    static ArrayDeque<Coord> ripe;
    static int cnt;
    static void init() throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	X = Integer.parseInt(tokens.nextToken());
    	Y = Integer.parseInt(tokens.nextToken());
    	Z = Integer.parseInt(tokens.nextToken());
        
    	box = new int[Z][Y][X];
    	ripe = new ArrayDeque<>();
    	cnt = 0;
    	
        for(int z = 0; z < Z; z++) {
        	for(int y = 0; y < Y; y++) {
        		tokens = new StringTokenizer(in.readLine());
        		for(int x = 0; x < X; x++) {
        			int val = Integer.parseInt(tokens.nextToken());
        			box[z][y][x] = val;
        			if(val == 1) {
        				ripe.add(new Coord(x, y, z));
        			} else if(val == 0) {
        				cnt++;
        			}
        		}
        	}
        }
    }

    static int[] dx = { +1, 0, 0, -1, 0, 0 };
    static int[] dy = { 0, +1, 0, 0, -1, 0 };
    static int[] dz = { 0, 0, +1, 0, 0, -1 };
    static void solve(){
    	
    	int day = -1;
    	while(!ripe.isEmpty()) {
    		day++;
    		ripen();
    	}
    	if(cnt == 0) {    		
    		System.out.println(day);
    	} else {
    		System.out.println(-1);
    	}
    }
    
    static void ripen() {
    	
    	ArrayDeque<Coord> ripen = new ArrayDeque<>();
    	
    	while(!ripe.isEmpty()) {
    		Coord cur = ripe.poll();
    		int x = cur.x;
    		int y = cur.y;
    		int z = cur.z;
    		
    		for(int i = 0; i < 6; i++) {
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			int nz = z + dz[i];
    			
    			if(nx < 0 || nx >= X || ny < 0 || ny >= Y || nz < 0 || nz >= Z) continue;
    			if(box[nz][ny][nx] != 0) continue;
    			
    			cnt--;
    			ripen.add(new Coord(nx, ny, nz));
    			box[nz][ny][nx] = 1;
    		}
    	}
    	
    	ripe = ripen;
    }

    static class Coord{
    	int x;
    	int y;
    	int z;
    	public Coord(int x, int y, int z) {
    		this.x = x;
    		this.y = y;
    		this.z = z;
    	}
    }
}
