import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[computers.length];
        
        for(int i = 0; i < computers.length; i++){
            if(visited[i]) continue;
            
            bfs(i, visited, computers);
            answer++;
        }
        
        return answer;
    }
    
    public void bfs(int n, boolean[] visited, int[][] computers){
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        q.add(n);
        visited[n] = true;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            int[] network = computers[cur];
            for(int i = 0; i < network.length; i++){
                if(visited[i] || network[i] == 0) continue;
                
                q.add(i);
                visited[i] = true;
            }
        }
    }
}