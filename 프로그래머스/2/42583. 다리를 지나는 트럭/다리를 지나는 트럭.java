import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int idx = 0;
        int clock = 1;
        int totalWeight = 0;
        ArrayDeque<Integer> times = new ArrayDeque<>();
        ArrayDeque<Integer> weights = new ArrayDeque<>();
        
        times.add(clock);
        weights.add(truck_weights[idx]);
        totalWeight += truck_weights[idx];
        idx++;
        
        while(!times.isEmpty()){
            clock++;
            if(clock - times.peek() >= bridge_length){
                times.poll();
                totalWeight -= weights.poll();
            }
            
            if(idx < truck_weights.length && totalWeight + truck_weights[idx] <= weight){
                times.add(clock);
                weights.add(truck_weights[idx]);
                totalWeight += truck_weights[idx];
                idx++;
            }
        }
        
        return answer = clock;
    }
}