import java.io.*;
import java.util.*;

class Main{
    static int[][] matrix;
    static int width;
    static int height;
    static int[] dx = {+1, 0, -1, 0};
    static int[] dy = {0, +1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        for(int t = 0; t < T; t++){
         
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            width = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            int cabNum = Integer.parseInt(tokens.nextToken());
            matrix = new int[height][width];
        
            for(int i = 0; i < cabNum; i++){
                tokens = new StringTokenizer(reader.readLine());
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                matrix[y][x] = 1;
            }
        
            int cnt = 0;
        
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    if(matrix[i][j] == 1){
                        cnt++;
                        BFS(j, i);
                    }
                }
            }
            System.out.println(cnt);   
        }
    }
    
    static void BFS(int x, int y){
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{x, y});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int i =0; i < 4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                
                if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
                
                if(matrix[ny][nx] == 1){
                    queue.add(new int[]{nx, ny});
                    matrix[ny][nx] = 0;
                }
            }
        }
    }
}