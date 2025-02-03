class Solution {
    public int[] solution(int brown, int yellow) {
        
        int width = 0;
        int height = 0;
        
        int half = (int)Math.ceil(yellow/2f);
        
        for(int vert = 1; vert <= half; vert++) {
            if(yellow%vert > 0) continue;
            
            int hori = yellow/vert;
            
            int edge = 2*(hori+2) + 2*(vert+2) - 4;
            
            if(edge == brown){
                width = hori+2;
                height = vert+2;
                break;
            }
        }
        
        int[] answer = {width, height};
        return answer;
    }
}