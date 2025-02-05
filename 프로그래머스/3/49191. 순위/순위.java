import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        
        Player[] players = new Player[n];
        
        for(int i = 0;i < n; i++) {
            players[i] = new Player();
        }
        
        for(int i = 0; i < results.length; i++) {
            int win = results[i][0]-1;
            int lose = results[i][1]-1;
            
            players[win].wins.add(lose);
            players[lose].loses.add(win);
        }
        
        int cnt = 0;
        
        for(int i = 0; i < n; i++) {
            Player cur = players[i];
            
            boolean[] visited = new boolean[n];
            
            ArrayDeque<Integer> q = new ArrayDeque<>();
            
            q.add(i);
            visited[i] = true;
            
            while(!q.isEmpty()) {
                int num = q.poll();
                
                for(int loser : players[num].wins) {
                    if(visited[loser]) continue;
                    visited[loser] = true;
                    q.add(loser);
                }
            }
            
            q.add(i);
            
            while(!q.isEmpty()) {
                int num = q.poll();
                
                for(int winner : players[num].loses) {
                    if(visited[winner]) continue;
                    visited[winner] = true;
                    q.add(winner);
                }
            }
            
            boolean predictable = true;
            for(boolean visit : visited) {
                if(!visit) {
                    predictable = false;
                    break;
                }
            }
            
            if(predictable) cnt++;
        }
        
        return cnt;
    }
    
    static class Player{
        Set<Integer> wins = new TreeSet<Integer>();
        Set<Integer> loses = new TreeSet<Integer>();
    }
}