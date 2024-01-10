import java.io.*;
import java.util.*;


class Main{
    static final int solid = 0;
    static final int broken = 1;
    
    static int[][] matrix;
    static int width;
    static int height;
    
    static int[][][] visited;
    
    static int[] dx = {+1, 0, -1, 0};
    static int[] dy = {0, +1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        
        height = Integer.parseInt(tokens.nextToken());
        width = Integer.parseInt(tokens.nextToken());
        matrix = new int[height][width];
        visited = new int[2][height][width];
        
        ArrayList<int[]> walls = new ArrayList<>();
        
        for(int h = 0; h < height; h++){
            char[] line = reader.readLine().toCharArray();
            for(int w = 0; w < width; w++){
                int value = Character.getNumericValue(line[w]);
                matrix[h][w] = value;
            }
        }
        
        BFS(new Path(0,0,false));
        
        int answer = 0;
        int brokeWall = visited[broken][height-1][width-1];
        int notBroken = visited[solid][height-1][width-1];
        
        if(brokeWall == 0 && notBroken == 0) answer = -1;
        else if(brokeWall == 0) answer = notBroken;
        else if(notBroken == 0) answer = brokeWall;
        else answer = Math.min(brokeWall, notBroken);
        
        System.out.println(answer);
    }
    
    static void BFS(Path path){
        
        Queue<Path> queue = new LinkedList<>();
        
        queue.add(path);
        visited[path.broken() ? broken : solid][path.y()][path.x()] = 1;
        
        while(!queue.isEmpty()){
            Path cur = queue.poll();
            int x = cur.x();
            int y = cur.y();
            
            if(x == width-1 && y == height -1) break;
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
                
                if(cur.broken()){
                    if(matrix[ny][nx] == 0 && visited[broken][ny][nx] == 0){
                        queue.add(new Path(nx, ny, true));
                        visited[broken][ny][nx] = visited[broken][y][x] + 1;
                    }
                } else {
                    if(matrix[ny][nx] == 1 && visited[broken][ny][nx] == 0){
                        queue.add(new Path(nx, ny, true));
                        visited[broken][ny][nx] = visited[solid][y][x] + 1;
                    } else if(matrix[ny][nx] == 0 && visited[solid][ny][nx] == 0){
                        queue.add(new Path(nx, ny, false));
                        visited[solid][ny][nx] = visited[solid][y][x] + 1;
                    }
                }
            }
        }
    }
}
class Path{
    int x;
    int y;
    boolean broken;
    
    public int x(){ return x; }
    public int y(){ return y; }
    public boolean broken(){ return broken; }
    
    public Path(int nx, int ny, boolean nBroken){
        this.x = nx;
        this.y = ny;
        this.broken = nBroken;
    }
}
