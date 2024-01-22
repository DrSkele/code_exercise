import java.io.*;
import java.util.*;

class Main{
    static int N;
    static int M;
    static boolean[] visited;
    static int[] array;
    static StringBuilder str;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        str = new StringBuilder();
        
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        visited = new boolean[N];
        array = new int[M];
        
        permutate(0);
    }
    
    static void permutate(int cnt){
        if(cnt == M){
            str.setLength(0);
            for(int i = 0; i < M; i++){
                str.append(array[i]);
                str.append(" ");
            }
            System.out.println(str.toString());
        } else {
            for(int i = 0; i < N; i++){
                if(visited[i]) continue;
                visited[i] = true;
                array[cnt] = i+1;
                permutate(cnt+1);
                visited[i] = false;
            }
        }
    }
}