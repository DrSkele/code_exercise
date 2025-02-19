import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int height;
  static int width;
  static int[][] matrix;
  static int size;
  static int max;
  static int[][][] horizontal;
  static int[][][] vertical;
  public static void input(BufferedReader in) throws IOException {
	  StringTokenizer tokens = new StringTokenizer(in.readLine());
	  
	  height = Integer.parseInt(tokens.nextToken());
	  width = Integer.parseInt(tokens.nextToken());
	  
	  matrix = new int[height][width];
	  
	  for(int y = 0; y < height; y++) {
		  char[] line = in.readLine().toCharArray();
		  for(int x = 0; x < width; x++) {
			  matrix[y][x] = Character.getNumericValue(line[x]);
		  }
	  }
	  
	  size = (1<<(height*width)) - 1;
	  
	  horizontal = new int[height][width][width];
	  vertical = new int[height][width][height];
	  
	  for(int y = 0; y < height; y++) {
		  for(int x = 0; x < width; x++) {
			  
			  horizontal[y][x][0] = matrix[y][x];
			  
			  for(int i = 1; x + i < width; i++) {
				  int nx = x + i;
				  
				  horizontal[y][x][i] = horizontal[y][x][i-1] * 10 + matrix[y][nx];
			  }
			  
			  vertical[y][x][0] = matrix[y][x];
			  
			  for(int i = 1; y + i < height; i++) {
				  int ny = y + i;
				  
				  vertical[y][x][i] = vertical[y][x][i-1] * 10 + matrix[ny][x];
			  }
		  }
	  }
  }
  
  public static void solve() {
	  dfs(0, 0, 0);
	  
	  System.out.println(max);
  }
  
  static void dfs(int idx, int mask, int sum) {
	  // 종이를 다 썼을 때
	  if(mask == size) {
//		  if(max < sum) System.out.println(mask + " " + sum);
		  max = Math.max(max, sum);
		  return;
	  }
	  // 모든 인덱스를 다 돌았을 때
	  if(idx >= height * width) return;
	  // 이미 사용한 숫자일 때
	  if((mask & (1 << idx)) > 0) {
		  dfs(idx+1, mask, sum);
		  return;
	  }
	  
	  int y = idx / width;
	  int x = idx % width;
	  
	  dfs(idx+1, mask | (1 << idx), sum + matrix[y][x]);
	  
	  int horiMask = (1 << idx);
	  for(int i = 1; x + i < width; i++) {
		  horiMask |= (1 << (idx + i));
		  if((mask & horiMask) > 0) break;
		  dfs(idx+i+1, mask | horiMask, sum + horizontal[y][x][i]);
	  }
	  
	  int vertMask = (1 << idx);
	  for(int i = 1; y + i < height; i++) {
		  vertMask |= (1 << (idx + (i * width)));
		  if((mask & vertMask) > 0) break;
		  dfs(idx+1, mask | vertMask, sum + vertical[y][x][i]);
	  }
  }
}