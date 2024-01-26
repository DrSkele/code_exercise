import java.io.*;
import java.util.*;

class Solution{
    static int minDist;
    static Coord[] coords;
    static boolean[] visited;
    static int length;
    static Coord home;
    static Coord work;
	public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int T = Integer.parseInt(tokens.nextToken());
        
        for(int t = 0; t < T; t++){
            minDist = Integer.MAX_VALUE;
        	length = Integer.parseInt(in.readLine());
            coords = new Coord[length];
            
            tokens = new StringTokenizer(in.readLine());
            
            work = new Coord(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
            home = new Coord(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));

            for(int i = 0; i < length; i++){
            	int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                coords[i] = new Coord(x, y);
            }
            
            visited = new boolean[length];
            
            permutate(0, work, 0);
            
            System.out.println(String.format("#%d %d", t+1, minDist));
        }
    }
    
    static void permutate(int cnt, Coord last, int dist){
        if(dist > minDist) return;
    	if(cnt == length){
            dist += last.getDist(home);
        	minDist = Math.min(minDist, dist);
        } else {
        	for(int i = 0; i < length; i++){
                if(visited[i]) continue;
                visited[i] = true;
                permutate(cnt+1, coords[i], dist + last.getDist(coords[i]));
                visited[i] = false;
            }
        }
        
    }
}
class Coord{
	int x;
    int y;
    
    public Coord(int x, int y){
    	this.x = x;
        this.y = y;
    }
    public Coord(){}
    
    public int getDist(int nx, int ny){
    	return Math.abs(x-nx) + Math.abs(y-ny);
    }
    
    public int getDist(Coord coord){
    	return getDist(coord.x, coord.y);
    }
}