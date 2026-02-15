import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve();        
    }
    
    static int size;
    static int min;
    static int max;
    static int[][] matrix;
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        size = Integer.parseInt(tokens.nextToken());
        min = Integer.parseInt(tokens.nextToken());
        max = Integer.parseInt(tokens.nextToken());
        matrix = new int[size][size];
        
        for(int i = 0; i < size; i++) {
            tokens = new StringTokenizer(in.readLine());
            for(int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }
    
    static boolean[][] visited;
    static void solve() {
        int cnt = 0;
        
        while(true) {
            visited = new boolean[size][size];
            boolean hasMoved = false;
            
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(visited[i][j]) continue;
                    if(repopulate(i, j)) {
                        hasMoved = true;
                    }
                }
            }
            
            if(!hasMoved) break;
            
            cnt++;
        }
        
        System.out.println(cnt);
    }
    
    static int[] dy = { 0, +1, 0, -1 };
    static int[] dx = { +1, 0, -1, 0 };
    static boolean repopulate(int oy, int ox) {
        boolean hasMoved = false;
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        ArrayList<int[]> alliance = new ArrayList<>();
        int sum = 0;
        
        queue.add(new int[]{oy, ox});
        visited[oy][ox] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int val = matrix[y][x];
            
            alliance.add(cur);
            sum += val;
            
            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny < 0 || ny >= size || nx < 0 || nx >= size) continue;
                if(visited[ny][nx]) continue;
                
                int diff = Math.abs(val - matrix[ny][nx]);
                if(diff < min || max < diff) continue;
                
                queue.add(new int[] {ny, nx});
                visited[ny][nx] = true;
                hasMoved = true;
            }
        }
        
        int population = sum / alliance.size();
        
        for(int[] ally : alliance) {
            int y = ally[0];
            int x = ally[1];
            matrix[y][x] = population;
        }
        
        return hasMoved;
    }
}