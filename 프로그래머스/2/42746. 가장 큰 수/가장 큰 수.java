import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        PriorityQueue<ComparableString> q = new PriorityQueue<>();
        
        for(int num : numbers){
            q.add(new ComparableString(String.valueOf(num)));
        }
        
        StringBuilder str = new StringBuilder();
        
        while(!q.isEmpty()){
            str.append(q.poll().value);
        }
        
        if(str.charAt(0) == '0') return "0";
        
        return str.toString();
    }
    
    class ComparableString implements Comparable<ComparableString>{
        String value;
        public ComparableString(String val){
            value = val;
        }
        
        @Override
        public int compareTo(ComparableString str){
            int idx = 0;
            while(true){
                int compare = str.value.charAt(idx) - value.charAt(idx);
                if(compare != 0) return compare;
                else {
                    idx++;
                    if(value.length() == idx && str.value.length() == idx) return 0;
                    else if(value.length() == idx){
                        return this.compareTo(new ComparableString(str.value.substring(idx, str.value.length())));
                    } else if(str.value.length() == idx){
                        return new ComparableString(value.substring(idx, value.length())).compareTo(str);
                    }
                }
            }
        }
    }
}