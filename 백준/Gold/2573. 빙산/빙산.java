import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        
        solve();
    }
    
    static int height;
    static int width;
    static int[][] matrix;
    public static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        height = Integer.parseInt(tokens.nextToken());
        width = Integer.parseInt(tokens.nextToken());
        
        matrix = new int[height][width];
        
        for(int i = 0; i < height; i++) {
            tokens = new StringTokenizer(in.readLine());
            for(int j = 0; j < width; j++) {
                matrix[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }
    
    static Boolean[][] visited;
    public static void solve() {
        int cnt = -1;
        
        int iceBerg = 0;
        visited = new Boolean[height][width];
        
        do {
            iceBerg = countIceBerg();
            cnt++;
        } while(iceBerg != 0 && iceBerg < 2);
        
        if(iceBerg < 2) System.out.println(0);
        else System.out.println(cnt);
    }
    
    static <T> void resetMatrix(T[][] matrix, T value) {
        for(T[] row : matrix) {
            Arrays.fill(row, value);
        }
    }
    
    static int countIceBerg() {
        int cnt = 0;
        resetMatrix(visited, false);
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(matrix[i][j] == 0 || visited[i][j]) continue;
                
                meltIce(i, j);
                
                cnt++;
            }
        }
        
        return cnt;
    }
    
    static int[] dy = { +1, 0, -1, 0 };
    static int[] dx = { 0, +1, 0, -1 };
    static void meltIce(int oy, int ox) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[]{oy, ox});
        visited[oy][ox] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            
            int water = 0;
            
            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
                if(visited[ny][nx]) continue;
                if(matrix[ny][nx] == 0) {
                    water++;
                    continue;
                }
                
                queue.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
            
            matrix[y][x] = Math.max(0, matrix[y][x] - water);
        }
    }
}