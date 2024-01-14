import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int vertN = Integer.parseInt(tokens.nextToken());
        int horiM = Integer.parseInt(tokens.nextToken());
        int inBag = Integer.parseInt(tokens.nextToken());
        
        int length = vertN*horiM;
        int[] blocks = new int[length];
        int[] height = new int[257];
        
        int cnt = 0;
        int total = 0;
        int top = 0;
        for(int i = 0; i < vertN; i++){
            tokens = new StringTokenizer(in.readLine());
            for(int j  = 0; j < horiM; j++){
                int curBlock = Integer.parseInt(tokens.nextToken());
                blocks[cnt++] = curBlock;
                total += curBlock;
                if(curBlock > top) top = curBlock;
            }
        }
        int[] workTime = new int[257];
        int minTime = Integer.MAX_VALUE;
        int flatten = 0;
        
        for(int i = 0; i <= 256; i++){
            if(total + inBag < i * length) continue;
            int sum = 0;
            for(int j = 0; j < length; j++){
                int curBlock = blocks[j];
                if(curBlock < i) {
                    sum += i-curBlock; 
                } else if(i < curBlock){
                    sum += 2*(curBlock-i);
                }
            }
            if(sum <= minTime){
                minTime = sum;
                flatten = i;
            }
        }
        
        System.out.print(minTime + " " + flatten);
        
    }
}