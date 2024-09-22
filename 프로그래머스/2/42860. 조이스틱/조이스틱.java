class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int move = length - 1;

        for(int i = 0; i < length; i++){
            char ch = name.charAt(i);
            
            if(ch <= 'M'){
                answer += ch - 'A';
            } else {
                answer += 'Z' - ch + 1;
            }   
            
            int idx = i + 1;
            while(idx < length && name.charAt(idx) == 'A'){
                idx++;
            }

            if(move > i*2 + length - idx) {
                move = i*2 + length - idx;
            }
            if(move > i + (length - idx) * 2){
                move = i + (length - idx) * 2;
            }
        }
        return answer + move;
    }
}
// a b c d e f g h i j k l m / n o p q r s t u v w x y z
// 9 + 4 + 9 + 12 + 4 + 13
// 51 + 5

// 9 + 0 + 13 + 1