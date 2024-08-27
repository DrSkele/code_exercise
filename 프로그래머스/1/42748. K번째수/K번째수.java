import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; i++){
            ArrayList<Integer> arr = new ArrayList<>();
            
            int[] command = commands[i];
            for(int j = command[0]-1; j <= command[1]-1; j++){
                arr.add(array[j]);
            }
            Collections.sort(arr);
            answer[i] = arr.get(command[2]-1);
        }
        
        return answer;
    }
}