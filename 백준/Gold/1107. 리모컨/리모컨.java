import java.io.*;
import java.util.*;

class Main{
    
    static int minVal;
    static int goal;
    static int length;
    static int[] pressed;
    static int[] btns;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        String goalString = tokens.nextToken();
        goal = Integer.parseInt(goalString);
        length = goalString.length();
        
        tokens = new StringTokenizer(in.readLine());
        int brokeM = Integer.parseInt(tokens.nextToken());
        
        btns = new int[10];
        if(brokeM > 0){
            tokens = new StringTokenizer(in.readLine());
            
            for(int i = 0; i < brokeM; i++){
                int broken = Integer.parseInt(tokens.nextToken());
                btns[broken] = 1;
            }
        }
        
        int channel = 100;
        minVal = Math.abs(goal-channel);
        pressed = new int[length+1];
        
        if(brokeM < 10){
            pressBtn(0);
        }
        System.out.print(minVal);
    }
    
    static void pressBtn(int cnt){
        if(0 < cnt && cnt <= length+1){
            StringBuilder str = new StringBuilder("");
            for(int i = 0; i < cnt; i++){
                str.append(pressed[i]);
            }
            int curChannel = Integer.parseInt(str.toString());
            
            minVal = Math.min(minVal, cnt + Math.abs(goal-curChannel));
        }
        if(cnt < length+1) {
            for(int i = 0; i < 10; i++){
                if(btns[i] == 1) continue;
                pressed[cnt] = i;
                pressBtn(cnt+1);
            }
        }
    }
}