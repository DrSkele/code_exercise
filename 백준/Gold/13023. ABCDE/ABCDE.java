
import java.io.*;
import java.util.*;

class Main{
    static ArrayList<Integer>[] map;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int friend = Integer.parseInt(tokens.nextToken());
        int relationship = Integer.parseInt(tokens.nextToken());
        
        map = new ArrayList[friend];
        visited = new boolean[friend];
        
        for(int i = 0; i < friend; i++){
            map[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < relationship; i++){
        	tokens = new StringTokenizer(in.readLine());
        	
            int f1 = Integer.parseInt(tokens.nextToken());
            int f2 = Integer.parseInt(tokens.nextToken());
            
            map[f1].add(f2);
            map[f2].add(f1);
        }
        
        boolean result = false;
        for(int i = 0; i < friend; i++){
            visited[i] = true;
            result = dfs(i, 1);
            visited[i] = false;
            if(result) break;
        }
        System.out.println(result ? 1 : 0);
    }
    
    static boolean dfs(int node, int cnt){
    	//System.out.println(cnt);
        if(cnt >= 5) return true;
        boolean result = false;
        
        ArrayList<Integer> friendList = map[node];
        
        for(int i = 0; i < friendList.size(); i++){
            int cur = friendList.get(i);
            if(visited[cur]) continue;
            
            visited[cur] = true;
            result = dfs(cur, cnt+1);
            visited[cur] = false;
            if(result) break;
        }
        
        return result;
    }
}