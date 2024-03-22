import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        int max = score.length / m;
        int offset = score.length % m;
        for(int i = max - 1; i >= 0; i--){
            int last = m * i + offset;
            answer += score[last] * m;
        }
        
        return answer;
    }
}