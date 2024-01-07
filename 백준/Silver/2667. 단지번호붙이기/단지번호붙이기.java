import java.io.*;
import java.util.*;

class Main{
    static int[][] matrix;
    static int size;
    
    static int[] dx = { +1, 0, -1, 0 };
    static int[] dy = { 0, +1, 0, -1 };
    
    public static void main(String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());
        matrix = new int[size][size];
        
        for(int i = 0; i < size; i++){
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            char[] line = tokens.nextToken().toCharArray();
            for(int j = 0; j < size; j++){
                matrix[i][j] = Character.getNumericValue(line[j]);
            }
        }
        
        int apt = 0;
        ArrayList<Integer> aptSizes = new ArrayList<>();
        
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(matrix[i][j] == 1){
                    apt++;
                    aptSizes.add(BFS(j, i));
                }
            }
        }
        Collections.sort(aptSizes);
        
        System.out.println(apt);
        for(Integer aptSize : aptSizes){
            System.out.println(aptSize);
        }
    }
    
    static int BFS(int x, int y){
        
        Queue<Integer[]> queue = new LinkedList<>();
        
        queue.add(new Integer[]{x, y});
        matrix[y][x] = 0;

        int blockSize = 1;
        
        while(!queue.isEmpty()){
            Integer[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            
            for(int i = 0; i < 4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                
                if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                
                if(matrix[ny][nx] == 1){
                    queue.add(new Integer[]{nx, ny});
                    matrix[ny][nx] = 0;
                    blockSize += 1;
                }
            }
        }
        return blockSize;
    }
}