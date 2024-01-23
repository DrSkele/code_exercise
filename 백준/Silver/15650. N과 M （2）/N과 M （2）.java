import java.io.*;
import java.util.*;

class Main{
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int num = Integer.parseInt(tokens.nextToken());
        int length = Integer.parseInt(tokens.nextToken());
        int[] array = new int[length];
        boolean[] visited = new boolean[num];
        permutate(0, array, visited, num, length);
    }
    
    static void permutate(int cnt, int[] array, boolean[] visited, int num, int length){
        if(cnt == length){
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < length; i++){
                str.append(array[i]).append(" ");
            }
            System.out.println(str.toString());
        } else {
            for(int i = 0; i < num; i++){
                if(visited[i]) continue;
                if(cnt > 0 && array[cnt-1] >= i+1) continue;
                
                array[cnt] = i+1;
                visited[i] = true;
                permutate(cnt+1, array, visited, num, length);
                visited[i] = false;
            }
        }
    }
}