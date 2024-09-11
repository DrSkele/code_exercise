class Solution {
    public int[] solution(int[] answers) {
        
        int[] scores = new int[3];
        
        int[] secondPattern = {1, 3, 4, 5};
        int[] thirdPattern = {3, 1,2,4,5};
        
        for(int i = 0; i < answers.length; i++){
            int answer = answers[i];
            if(answer == (1 + (i % 5))) scores[0]++;
            if(i%2 == 0){//even
                if(answer == 2) scores[1]++;
            } else {//odd
                if(answer == secondPattern[(i/2)%4]) scores[1]++;
            }
            if(answer == thirdPattern[(i/2)%5]) scores[2]++;
        }
        
        int max = 0;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] >= max) max = scores[i];
        }
        int cnt = 0;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] == max) cnt++;
        }
        int[] result = new int[cnt];
        
        int j = 0;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] == max) result[j++] = i+1;
        }
        
        return result;
    }
}