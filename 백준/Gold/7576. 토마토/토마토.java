import java.io.*;
import java.util.*;

class Main{
    static int[][] matrix;
    static int width;
    static int height;
    static int[] dx = {+1, 0, -1, 0};
    static int[] dy = {0, +1, 0, -1};
    static int maxVal = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        width = Integer.parseInt(tokens.nextToken());
        height = Integer.parseInt(tokens.nextToken());
        matrix = new int[height][width];
        
        Queue<int[]> ripe = new LinkedList<>();
        
        for(int i = 0; i < height; i++){
            tokens = new StringTokenizer(reader.readLine());
            for(int j = 0; j < width; j++){
                int val = Integer.parseInt(tokens.nextToken());
                matrix[i][j] = val;
                if(val == 1) ripe.add(new int[]{j, i});
            }
        }
        
        while(!ripe.isEmpty()){
            int[] cur = ripe.poll();
            int x = cur[0];
            int y = cur[1];
            int curVal = matrix[y][x];
            
            if(curVal > maxVal){
                maxVal = curVal;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
                
                if(matrix[ny][nx] == 0){
                    ripe.add(new int[]{nx, ny});
                    matrix[ny][nx] = curVal + 1;
                }
            }
        }
        
        System.out.print(check()? maxVal-1 : -1);
    }
    
    static boolean check(){
        for(int i = 0; i < height; i++){
            for(int j =0; j < width; j++){
                if(matrix[i][j] == 0){
                    maxVal = -2;
                    return false;
                }
            }
        }
        return true;
    }
}