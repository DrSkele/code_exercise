class Solution {
    public int solution(int[][] sizes) {
        int hori = 0; //longer
        int vert = 0; //shorter
        for(int i = 0; i < sizes.length; i++){
            int cur_h = sizes[i][0];
            int cur_v = sizes[i][1];
            
            hori = cur_h > cur_v ? Math.max(hori, cur_h) : Math.max(hori, cur_v);
            vert = cur_h > cur_v ? Math.max(vert, cur_v) : Math.max(vert, cur_h);
        }
        
        return hori*vert;
    }
}