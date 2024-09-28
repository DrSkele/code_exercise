import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for(int cnt : citations){
            q.add(cnt);
        }
        
        while(!q.isEmpty()){
            int cur = q.peek();
            
            if(cur >= q.size()) {
                answer = q.size();
                break;
            };
            
            q.poll();
        }
        
        return answer;
    }
}