import java.util.*;

class Solution {
    public int solution(String s) {
        StringBuilder str = new StringBuilder();
        
        int idx = 0;
        while(idx < s.length()) {
            char c = s.charAt(idx);
            
            switch(c) {
                case 'z': 
                    str.append(0);
                    idx += 4;
                    break;
                case 'o': 
                    str.append(1);
                    idx += 3;
                    break;
                case 't':
                    char nextT = s.charAt(idx+1);
                    if(nextT == 'w') {
                        str.append(2);
                        idx += 3;
                    } else {
                        str.append(3);
                        idx += 5;
                    }
                    break;
                case 'f':
                    char nextF = s.charAt(idx+1);
                    if(nextF == 'o') {
                        str.append(4);
                        idx += 4;
                    } else {
                        str.append(5);
                        idx += 4;
                    }
                    break;
                case 's':
                    char nextS = s.charAt(idx+1);
                    if(nextS == 'i') {
                        str.append(6);
                        idx += 3;
                    } else {
                        str.append(7);
                        idx += 5;
                    }
                    break;
                case 'e': 
                    str.append(8);
                    idx += 5;
                    break;
                case 'n': 
                    str.append(9);
                    idx += 4;
                    break;
                default:
                    str.append(c);
                    idx += 1;
                    break;
            }
        }
        
        return Integer.parseInt(str.toString());
    }
}