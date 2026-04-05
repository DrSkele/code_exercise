import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder str = new StringBuilder();
        
        while(true) {
            StringTokenizer tokens = new StringTokenizer(in.readLine());
            int width = Integer.parseInt(tokens.nextToken());
            int height = Integer.parseInt(tokens.nextToken());
            
            // end of dataset
            if(width == 0 && height == 0) break;
            
            char[][] matrix = new char[2 * height - 1][2 * width - 1];
            
            for(int i = 0; i < 2 * height - 1; i++) {
                matrix[i] = in.readLine().toCharArray();
            }
            
            str.append(getShortestPath(matrix, 2 * width - 1, 2 * height - 1)).append('\n');
        }
        
        System.out.println(str.toString());
    }
    
    static int getShortestPath(char[][] matrix, int width, int height) {
        
        int[] dx = { +2, 0, -2, 0 };
        int[] dy = { 0, +2, 0, -2 };
        
        int[][] visited = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                visited[i][j] = -1;
            }
        }
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            int y = cur[0];
            int x = cur[1];
            int val = visited[y][x];
            
            if(y == height-1 && x == width-1) break;
            
            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
                if(visited[ny][nx] != -1) continue;
                
                int wally = y + dy[i]/2;
                int wallx = x + dx[i]/2;
                
                if(matrix[wally][wallx] == '1') continue;
                
                queue.add(new int[]{ny, nx});
                visited[ny][nx] = val + 1;
            }
        }
        
        int dest = visited[height-1][width-1];
        if(dest == -1) dest = 0;
        return dest;
    }
}