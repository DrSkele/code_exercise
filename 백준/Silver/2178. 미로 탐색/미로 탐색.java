import java.io.*;
import java.util.*;

class Main{
    static int[][] matrix;
    static boolean[][] visited;
    static int[][] cntMatrix;
    static int xMax;
    static int yMax;
    static int[] dy = {0, +1, 0, -1};
    static int[] dx = {+1, 0, -1, 0};
    static int minVal = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        yMax = Integer.parseInt(tokens.nextToken());
        xMax = Integer.parseInt(tokens.nextToken());
        
        matrix = new int[yMax][xMax];
        visited = new boolean[yMax][xMax];
        
        for(int i = 0; i < yMax; i++){
            tokens = new StringTokenizer(reader.readLine());
            char[] line = tokens.nextToken().toCharArray();
            for(int j = 0; j < xMax; j++){
                matrix[i][j] = Character.getNumericValue(line[j]);
            }
        }
        visited[0][0] = true;
        //DFS(0, 0, 1);
        BFS();
        System.out.print(cntMatrix[yMax-1][xMax-1]);
    }
    
    
    static void BFS(){
        Queue<Integer[]> queue = new LinkedList<>();
        
        cntMatrix = new int[yMax][xMax];
        
        cntMatrix[0][0] = 1;
        queue.add(new Integer[]{0, 0});
        
        while(!queue.isEmpty()){
            Integer[] cur = queue.poll();            
            int x = cur[0];
            int y = cur[1];
            int cnt = cntMatrix[y][x];
                
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
            
                if(nx < 0 || nx >= xMax || ny < 0 || ny >= yMax) continue;
            
                if(cntMatrix[ny][nx] == 0 && matrix[ny][nx] == 1){
                    queue.add(new Integer[]{nx, ny});
                    cntMatrix[ny][nx] = cnt + 1;
                }
            }
        }
    }
    
    static void DFS(int x, int y, int cnt){
        if(x == xMax-1 && y == yMax-1){
            if(cnt < minVal) minVal = cnt;
        }
        
        if(cnt > minVal) return;
        
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= xMax || ny < 0 || ny >= yMax) continue;
            
            if(!visited[ny][nx] && matrix[ny][nx] == 1){
                visited[ny][nx] = true;
                DFS(nx, ny, cnt+1);
                visited[ny][nx] = false;
            }
        }
    }
}