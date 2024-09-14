import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) {
            q.add(scoville[i]);
        }
        
        while(q.peek() < K) {
            int first = q.poll();
            if(q.isEmpty()) return -1;
            int second = q.poll();
            
            q.add(first + second*2);
            answer++;
        }
        
        return answer;
    }
}