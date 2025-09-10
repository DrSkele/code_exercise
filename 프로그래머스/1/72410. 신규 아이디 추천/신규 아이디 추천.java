import java.util.*;

class Solution {
    public String solution(String new_id) {
        
        String lower = new_id.toLowerCase();
        
        StringBuilder str = new StringBuilder();
        
        int idx = 0;
        while(idx < lower.length()) {
            char c = lower.charAt(idx);
            if(('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.')
                str.append(c);
            idx++;
        }
        
        idx = 0;
        while(idx < str.length()) {
            char c = str.charAt(idx);
            if(c == '.' && idx > 0 && str.charAt(idx-1) == '.')
                str.deleteCharAt(idx);
            else
                idx++;
        }
        
        if(str.length() > 0) {
            char first = str.charAt(0);
        
            if(first == '.')
                str.deleteCharAt(0);
        } 
        if(str.length() > 0) {
            char last = str.charAt(str.length()-1);

            if(last == '.')
                str.deleteCharAt(str.length()-1);
        }
        
        if(str.length() == 0) {
            str.append('a');
        }
        
        while(str.length() >= 16) {
            str.deleteCharAt(str.length()-1);
        }
        
        if(str.length() > 0 && str.charAt(str.length()-1) == '.')
            str.deleteCharAt(str.length()-1);
        
        while(str.length() <= 2) {
            str.append(str.charAt(str.length()-1));
        }
        
        String answer = str.toString();
        return answer;
    }
}