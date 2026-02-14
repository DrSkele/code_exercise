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
    public static void solve() {
        
        int[] dx = { +1, +1, 0, -1, -1, -1, 0, +1 };
        int[] dy = { 0, +1, +1, +1, 0, -1, -1, -1 };
        
        boolean[][] visited = new boolean[height][width];
        
        int cnt = 0;
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(visited[i][j]) continue;
                
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                
                visited[i][j] = true;
                queue.add(new int[] {i, j});
                
                boolean isTop = true;
                
                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int y = cur[0];
                    int x = cur[1];
                    int val = matrix[y][x];
                    
                    for(int n = 0; n < 8; n++) {
                        int ny = y + dy[n];
                        int nx = x + dx[n];
                        
                        // out of matrix
                        if(ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
                        // bigger
                        if(matrix[ny][nx] > val) {
                            isTop = false;
                            continue;
                        }
                        // smaller
                        if(matrix[ny][nx] < val) continue;
                        // visited
                        if(visited[ny][nx]) continue;
                        
                        visited[ny][nx] = true;
                        queue.add(new int[] {ny, nx});
                    }
                }
                
                if(isTop) {
                  cnt++;
                }
            }
        }
        
        System.out.println(cnt);
    }
}