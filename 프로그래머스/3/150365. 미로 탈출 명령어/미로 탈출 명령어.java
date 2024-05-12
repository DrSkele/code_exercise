class Solution {
    
    // d -> l -> r -> u
    static int height;
    static int width;
    static int startX;
    static int startY;
    static int endX;
    static int endY;
    static int length;
    static int dist;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        height = n;
        width = m;
        startX = x;
        startY = y;
        endX = r;
        endY = c;
        length = k;
        dist = Math.abs(startX - endX) + Math.abs(startY - endY);
        
        if(dist % 2 != length % 2 || dist > length) return "impossible";
        
        StringBuilder str = new StringBuilder();
        
        int excess = length - dist;
        int tempX = startX;
        int tempY = startY;
        
        if(startX < endX){
            int dCnt = endX - startX;
            for(int i = 0; i < dCnt; i++){
                str.append("d");
                tempX++;
            }
        }

        while(excess > 0 && tempX < height){
            str.append("d");
            tempX++;
            excess -= 2;
        }
        
        if(startY > endY){
            int lCnt = startY - endY;
            for(int i = 0; i < lCnt; i++){
                str.append("l");
                tempY--;
            }
        }
        
        while(excess > 0 && 1 < tempY){
            str.append("l");
            tempY--;
            excess -= 2;
        }

//         int lefthand = Math.min(excess, (tempY - 1));
//         for(int i = 0; i < lefthand; i++){
//             str.append("l");
//             tempY--;
//             length -= 2;
//         }
//         excess = length - dist;

        int righthand = excess/2;
        for(int i = 0; i < righthand; i++){
            str.append("rl");
            excess -= 2;
        }

        int rCnt = Math.abs(tempY - endY);
        for(int i = 0; i < rCnt; i++){
            str.append("r");
            dist--;
            length--;
        }

        int upward = excess/2;
        for(int i = 0; i < upward; i++){
            str.append("ud");
        }

        int uCnt = Math.abs(tempX - endX);
        for(int i = 0; i < uCnt; i++){
            str.append("u");
        }
        
        return str.toString();
    }
}