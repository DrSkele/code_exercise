import java.io.*;
import java.util.*;

class Main{
    
    static int[] visited;
    
    public static void main(String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        visited = new int[num+1];
        
        System.out.print(DP(num));
    }
    
    public static int DP(int num){
        if(num < 2) return 0;
        if(visited[num] == 0){
            if(num % 6 == 0)
                visited[num] = Math.min(DP(num-1), Math.min(DP(num/3), DP(num/2))) + 1;
            else if(num % 3 == 0){
                visited[num] = Math.min(DP(num/3), DP(num-1)) + 1;
            } else if(num % 2 == 0){
                visited[num] = Math.min(DP(num-1), DP(num/2)) + 1;
            } else
                visited[num] = DP(num-1) + 1;
        }
        return visited[num];
    }
}