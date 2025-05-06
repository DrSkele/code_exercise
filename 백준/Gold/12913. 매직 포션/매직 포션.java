import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int nCity;
    static int nPotion;
    static float[][] time;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	nCity = Integer.parseInt(tokens.nextToken());
    	nPotion = Integer.parseInt(tokens.nextToken());
    	
    	time = new float[nCity][nCity];
    	
    	for(int i = 0; i < nCity; i++) {
    		char[] line = in.readLine().toCharArray();
    		for(int j = 0; j < nCity; j++) {
    			time[i][j] = Character.getNumericValue(line[j]);
    		}
    	}
    }
    
    static void solve() {
    	boolean[][] visited = new boolean[nPotion+1][nCity];
    	
    	PriorityQueue<Path> pq = new PriorityQueue<Path>();
    	
    	pq.add(new Path(0, nPotion, 0f));
    	
    	while(!pq.isEmpty()) {
    		Path cur = pq.poll();
    		
    		if(visited[cur.potions][cur.dest]) continue;
    		visited[cur.potions][cur.dest] = true;
    		
    		if(cur.dest == 1) {
    			System.out.println(cur.time);
    			break;
    		}
    		
    		for(int i = 0; i < nCity; i++) {
    			if(i == cur.dest) continue;
    			
    			// Use potion
    			if(cur.potions > 0 && !visited[cur.potions-1][i]) {
    				pq.add(new Path(i, cur.potions-1, cur.time + time[cur.dest][i]/2f));
    			}
    			
    			// Not use potion
    			if(!visited[cur.potions][i]) {
    				pq.add(new Path(i, cur.potions, cur.time + time[cur.dest][i]));
    			}
    		}
    	}
    }
    
    static class Path implements Comparable<Path>{
    	int dest;
    	int potions;
    	float time;
    	
    	public Path(int d, int p, float t) {
    		dest = d;
    		potions = p;
    		time = t;
    	}
    	
    	public int compareTo(Path other) {
    		return Float.compare(time, other.time);
    	}
    }
}