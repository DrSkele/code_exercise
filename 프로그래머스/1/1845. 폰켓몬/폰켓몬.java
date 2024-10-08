import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        
        if(set.size() < nums.length / 2){
            answer = set.size();
        } else {
            answer = nums.length / 2;
        }
        
        return answer;
    }
}