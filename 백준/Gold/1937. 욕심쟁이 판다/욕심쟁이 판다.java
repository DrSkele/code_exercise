import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int size;
  static int[][] forest;
  static int[][] depth;
  public static void input(BufferedReader in) throws IOException {
    size = Integer.parseInt(in.readLine());
    forest = new int[size][size];
    depth = new int[size][size];
    
    for(int i = 0; i < size; i++) {
      StringTokenizer tokens = new StringTokenizer(in.readLine());
      for(int j = 0; j < size; j++) {
        forest[i][j] = Integer.parseInt(tokens.nextToken());
        depth[i][j] = -1;
      }
    }
  }
  
  public static void solve() {
    int max = 0;
    
    for(int y = 0; y < size; y++) {
      for(int x = 0; x < size; x++) {
        max = Math.max(max, dfs(y, x));
      }
    }
    
    System.out.println(max);
  }
  
  static int[] dx = { +1, 0, -1, 0 };
  static int[] dy = { 0, +1, 0, -1 };
  public static int dfs(int y, int x) {
    if(depth[y][x] != -1) return depth[y][x];
    
    int maxDepth = 1;
    
    int cur = forest[y][x];
    for(int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
      if(forest[ny][nx] <= cur) continue; 
      
      maxDepth = Math.max(maxDepth, dfs(ny, nx) + 1);
    }
    
    return depth[y][x] = maxDepth;
  }
}