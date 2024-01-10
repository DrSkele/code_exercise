import java.io.*;
import java.util.*;

class Main{
    static int[][] matrix;
    static int N;
    
    static Integer[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        
        matrix = new int[N][3];
        
        visited = new Integer[N][3];
        
        StringTokenizer tokens;
        for(int i = 0; i < N; i++){
            tokens = new StringTokenizer(reader.readLine());
            for(int j  = 0; j < 3; j++){
                matrix[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        
        visited[0][0] = matrix[0][0];
        visited[0][1] = matrix[0][1]; 
        visited[0][2] = matrix[0][2];
        
        System.out.print(Math.min(matrix[0][0] + dp(1, 0), Math.min(matrix[0][1] + dp(1, 1), matrix[0][2] + dp(1, 2))));
    }
    
    static int dp(int idx, int lastColor){
        if(idx == N) return 0;
        
        int red = matrix[idx][0];
        int green = matrix[idx][1];
        int blue = matrix[idx][2];
        
        if(visited[idx][lastColor] == null)
        {
            switch(lastColor){
            case 0://red
                visited[idx][0] = Math.min(green + dp(idx+1, 1), blue + dp(idx+1, 2));
            case 1://green
                visited[idx][1] = Math.min(red + dp(idx+1, 0), blue + dp(idx+1, 2));
            case 2://blue
                visited[idx][2] = Math.min(red + dp(idx+1, 0), green + dp(idx+1, 1));
            }
        }
        return visited[idx][lastColor];
    }
}