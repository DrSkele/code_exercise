import java.io.*;
import java.util.*;

class Main{
    static int N;
    
    static int cnt = 0;
    static int[] placed;
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        
        placed = new int[N];
        for(int i = 0; i < N; i++){
            placed[i] = -1;
        }
        
        for(int x = 0; x <N; x++){
            DFS(x, 0);
            placed[0] = -1;
        }
        System.out.print(cnt);
    }
    
    static void DFS(int curX, int curY){
        
        placed[curY] = curY * N + curX;
        
        if(curY == N-1){
            cnt++;
            return;
        }
        
        int y = curY+1;
        
        for(int x = 0; x < N; x++){
            boolean isPlacable = true;
            for(int q = 0; q < N; q++){
                int queen = placed[q];
                if(queen == -1) continue;
                
                int qX = queen%N;
                int qY = queen/N;
                
                if(x == qX || x-y == qX-qY || x+y == qX+qY){
                    isPlacable = false;
                    break;
                }
            }
            if(isPlacable){
                DFS(x, y);
                placed[y] = -1;
            }
        }
    }
}