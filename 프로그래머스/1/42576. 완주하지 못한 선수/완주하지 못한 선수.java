import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < participant.length; i++){
            String name = participant[i];
            map.put(name, map.containsKey(name) ? map.get(name)+1 : 1);
        }
        for(int i = 0; i < completion.length; i++){
            String name = completion[i];
            int num = map.get(name);
            if(num == 1){
                map.remove(name);
            } else {
                map.put(name, num-1);
            }
        }
        
        answer = map.keySet().iterator().next();
        
        return answer;
    }
}