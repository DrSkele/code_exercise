import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve();
    }
    
    static int nNode;
    static ArrayList<Integer>[] branch;
    static void input(BufferedReader in) throws IOException {
        nNode = Integer.parseInt(in.readLine());
        
        branch = new ArrayList[nNode+1];
        for(int i = 0; i <= nNode; i++) {
        	branch[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < nNode-1; i++) {
        	StringTokenizer tokens = new StringTokenizer(in.readLine());
        	
        	int node1 = Integer.parseInt(tokens.nextToken());
        	int node2 = Integer.parseInt(tokens.nextToken());
        	
        	branch[node1].add(node2);
        	branch[node2].add(node1);
        }
    }
    
    static void solve() {
    	
    	int[] parent = new int[nNode+1];
    	Arrays.fill(parent, -1);
    	
    	ArrayDeque<Integer> q = new ArrayDeque<>();
    	
    	q.add(1);
    	parent[1] = 0;
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		
    		for(int next : branch[cur]) {
    			if(parent[next] != -1) continue;
    			
    			parent[next] = cur;
    			q.add(next);
    		}
    	}
    	
    	StringBuilder str = new StringBuilder();
    	
    	for(int i = 2; i <= nNode; i++) {
    		str.append(parent[i]).append("\n");
    	}
    	
    	System.out.println(str.toString());
    }
}