import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(in.readLine());
        StringBuilder str = new StringBuilder();
        
        for(int i = 1; i <= T; i++) {
        	input(in);
        	str.append("Case #").append(i).append(": ").append(solve()).append(" meters\n");
        }
        System.out.println(str.toString());
    }
    
    static int vert;
    static int edge;
    static Path[] paths;
    static int[] root;
    static void input(BufferedReader in) throws IOException {
    	StringTokenizer tokens = new StringTokenizer(in.readLine());
    	
    	vert = Integer.parseInt(tokens.nextToken());
    	edge = Integer.parseInt(tokens.nextToken());
    	paths = new Path[edge];
    	
    	for(int i = 0; i < edge; i++) {
    		tokens = new StringTokenizer(in.readLine());
    		
    		paths[i] = new Path(
    				Integer.parseInt(tokens.nextToken()), 
    				Integer.parseInt(tokens.nextToken()), 
    				Integer.parseInt(tokens.nextToken())
    				);
    	}
    	Arrays.sort(paths);
    	
    	root = new int[vert+1];
    	for(int i = 0; i < vert+1; i++) {
    		root[i] = i;
    	}
    }
    
    static int solve() {
    	
    	int sum = 0;
    	
    	for(Path path : paths) {
    		if(union(path.from, path.to)) {
    			sum += path.dist;
    		}
    	}
    	
    	return sum;
    }
    
    static boolean union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	
    	if(a == b) return false;
    	
    	if(a < b) {
    		root[b] = a;
    	} else {
    		root[a] = b;
    	}
    	
    	return true;
    }
    
    static int find(int a) {
    	if(root[a] == a) return a;
    	
    	return root[a] = find(root[a]);
    }
    
    public static class Path implements Comparable<Path>{
    	int from;
    	int to;
    	int dist;
    	public Path(int f, int t, int d) {
    		from = f;
    		to = t;
    		dist = d;
    	}
    	
    	public int compareTo(Path other) {
    		return Integer.compare(dist, other.dist);
    	}
    }
}