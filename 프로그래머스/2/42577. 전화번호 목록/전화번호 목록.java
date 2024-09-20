import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashSet<Integer> lengthSize = new HashSet<>();
        for(String num : phone_book){
            lengthSize.add(num.length());
        }
        
        HashSet<String> set = new HashSet<>();
        
        for(String num : phone_book){
            for(Integer len : lengthSize){
                if(len < num.length()) set.add(num.substring(0, len));
            }
        }
        
        for(String num : phone_book){
            if(set.contains(num)) {
                answer = false;
                break;
            }
        }
        
        return answer;
    }
}