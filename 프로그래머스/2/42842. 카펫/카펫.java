class Solution {
    public int[] solution(int brown, int yellow) {
        
        int width = 0;
        int height = 0;
        
        int half = (int)Math.ceil(brown/2f);
        
        for(int vert = half; vert >= 3; vert--) {
            int hori = (brown - (vert*2) + 4)/2;
            
            if(hori < vert) continue;
            
            int inside = (hori-2) * (vert-2);
            
            if(inside == yellow){
                width = hori;
                height = vert;
                break;
            }
        }
        
        int[] answer = {width, height};
        return answer;
    }
}