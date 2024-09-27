import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Process> processes = new LinkedList<>();
        
        for(int i = 0; i < priorities.length; i++){
            pq.add(priorities[i]);
            processes.add(new Process(priorities[i], i == location));
        }
        
        while(!processes.isEmpty()){
            Process cur = processes.poll();
            if(cur.priority < pq.peek()) {
                processes.add(cur);
            } else {
                answer++;
                pq.poll();
                //System.out.println(cur.priority);
                if(cur.target) break;
            }
        }
        
        return answer;
    }
    
    class Process{
        int priority;
        boolean target;
        public Process(int p, boolean t){
            priority = p;
            target = t;
        }
    }
}