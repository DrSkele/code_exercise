import java.io.*;
import java.util.*;

class Main{
    static StringBuilder str;
    static int N;
    static int[] nums;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        nums = new int[N];
        visited = new boolean[N];
        
        generate(0);
    }
    
    static void generate(int cnt){
        if(cnt == N){
            str.setLength(0);
            for(int i = 0; i < N; i++){
                str.append(nums[i]);
                str.append(" ");
            }
            System.out.println(str.toString());
        } else {
            for(int i = 0; i < N; i++){
                if(!visited[i])
                {
                    visited[i] = true;
                    nums[cnt] = i+1;
                    generate(cnt+1);
                    visited[i] = false;
                }
            }
        }
    }
}