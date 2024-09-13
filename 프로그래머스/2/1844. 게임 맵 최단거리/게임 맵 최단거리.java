import java.util.*;

class Solution {
    
    static int width;
    static int height;
    static int[][] map;
    static int[][] visited;
    public int solution(int[][] maps) {
        height = maps.length;
        width = maps[0].length;
        map = maps;
        visited = new int[height][width];
        
        return bfs();
    }
    
    static int[] dx = { +1, 0, -1, 0 };
    static int[] dy = { 0, +1, 0, -1 };
    public static int bfs() {
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{0, 0});
        visited[0][0] = 1;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[1];
            int y = cur[0];
            int val = visited[y][x];
            
            if(x == width-1 && y == height-1) break;
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= width || ny >= height) continue;
                if(map[ny][nx] == 0 || visited[ny][nx] > 0) continue;
                
                q.add(new int[]{ny, nx});
                visited[ny][nx] = val + 1;
            }
        }
        
        int dest = visited[height-1][width-1];
        
        return dest > 0 ? dest : -1;
    }
}