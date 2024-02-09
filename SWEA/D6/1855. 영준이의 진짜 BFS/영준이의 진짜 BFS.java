import java.io.*;
import java.util.*;

class Solution{
	
	static int length;
	static ArrayList<Integer>[] child;
	static int[][] parent;
	static int[] depth;
	
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringTokenizer tokens;
        for(int t = 1; t < T+1; t++) {
        	length = Integer.parseInt(in.readLine());
        	
        	tokens = new StringTokenizer(in.readLine());
        	
        	child = new ArrayList[length+1];
        	parent = new int[length+1][20];
        	depth = new int[length+1];
        	
        	for(int i = 2; i < length+1; i++) {
        		int cur = Integer.parseInt(tokens.nextToken());
        		
        		if(child[cur] == null) {
        			child[cur] = new ArrayList<>();
        		}
        		
        		child[cur].add(i);
        		parent[i][0] = cur;
        		depth[i] = depth[cur]+1;
        	}
        	
        	fillParent();
        	
        	long result = bfs(1);
        	
        	System.out.println(String.format("#%d %d", t, result));
        }
    }
    
    static void fillParent(){
    	for(int i = 1; i < 20; i++){
        	for(int j = 1; j < length+1; j++){
            	parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    
    static long bfs(int node) {
    	
    	Queue<Integer> q = new LinkedList<>();
    	int prev = node;
    	
    	long sum = 0;
    	
    	q.add(node);
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		ArrayList<Integer> arr = child[cur];
    		if(arr != null) {
    			for(int i = 0; i < arr.size(); i++) {
    				int next = arr.get(i);
    				q.add(next);
    			}
    		}
    		
    		int lca = LCA(cur, prev);
    		sum += depth[prev] + depth[cur] - 2 * depth[lca];
    		
    		prev = cur;
    	}
    	return sum;
    }
    
    static int LCA(int v1, int v2){
    	if(depth[v1] > depth[v2]){
        	int temp = v1;
            v1 = v2;
            v2 = temp;
        }
        
        if(depth[v2] > depth[v1]){
        	for(int i = 19; i >= 0; i--){
                if(depth[v1] <= depth[parent[v2][i]]){
                	v2 = parent[v2][i];
                }
            }
        }
        
        if(v1 != v2){
        	for(int i = 19; i >= 0; i--){
            	if(parent[v1][i] != parent[v2][i]){
                	v1 = parent[v1][i];
                    v2 = parent[v2][i];
                }
            }
            return parent[v1][0];
        }
        return v1;
    }
}