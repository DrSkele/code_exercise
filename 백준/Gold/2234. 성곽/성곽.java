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
  static int[][] walls;
  static int[][] rooms;
  public static void input(BufferedReader in) throws IOException {
	  StringTokenizer tokens = new StringTokenizer(in.readLine());
	  width = Integer.parseInt(tokens.nextToken());
	  height = Integer.parseInt(tokens.nextToken());
	  
	  walls = new int[height][width];
	  rooms = new int[height][width];
	  
	  for(int i = 0; i < height; i++) {
		  tokens = new StringTokenizer(in.readLine());
		  for(int j = 0; j < width; j++) {
			  walls[i][j] = Integer.parseInt(tokens.nextToken());
		  }
	  }
  }
  
  public static void solve() {
	  
	  int num = 1;
	  ArrayList<Integer> roomSize = new ArrayList<>();
	  roomSize.add(0);
	  
	  int max = 0;
	  
	  for(int y = 0; y < height; y++) {
		  for(int x = 0; x < width; x++) {
			  if(rooms[y][x] > 0) continue;
			  
			  int size = bfs(y, x, num++);
			  
			  roomSize.add(size);
			  max = Math.max(max, size);
		  }
	  }
	  
	  int sum = 0;
	  
	  for(int y = 0; y < height; y++) {
		  for(int x = 0; x < width; x++) {
			  int curRoom = rooms[y][x];
			  int curSize = roomSize.get(curRoom);
			  int wall = walls[y][x];
			  
			  for(int i = 0; i < 4; i++) {
				  if((wall & dir[i]) == 0) continue;
				  
				  int ny = y + dy[i];
				  int nx = x + dx[i];
				  
				  if(ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
				  
				  int nextRoom = rooms[ny][nx];
				  
				  if(nextRoom == curRoom) continue;
				  
				  int nextSize = roomSize.get(nextRoom);
				  
				  sum = Math.max(sum, curSize + nextSize);
			  }
		  }
	  }
	  
//	  for(int i = 0; i < height; i++) {
//		  for(int j = 0; j < width; j++) {
//			  System.out.print(rooms[i][j]);
//		  }
//		  System.out.println();
//	  }
	  
	  System.out.println(roomSize.size()-1);
	  System.out.println(max);
	  System.out.println(sum);
	  
  }
  
  static final int[] dir = { 1, 2, 4, 8 };
  static final int[] dx = { -1, 0, +1, 0 };
  static final int[] dy = { 0, -1, 0, +1 };
  public static int bfs(int y, int x, int num) {
	  
	  ArrayDeque<Coord> q = new ArrayDeque<>();
	  boolean[][] visited = new boolean[height][width];
	  
	  q.add(new Coord(y, x));
	  visited[y][x] = true;
	  
	  int cnt = 0;
	  
	  while(!q.isEmpty()) {
		  Coord cur = q.poll();
		  int wall = walls[cur.y][cur.x];
		  cnt++;
		  rooms[cur.y][cur.x] = num;
		  
		  
		  
		  for(int i = 0; i < 4; i++) {
			  if((wall & dir[i]) == dir[i]) continue;
			  
			  int ny = cur.y + dy[i];
			  int nx = cur.x + dx[i];
			  
			  if(ny < 0 || ny >= height || nx < 0 || nx >= width) continue;
			  if(visited[ny][nx]) continue;
			  
			  q.add(new Coord(ny, nx));
			  visited[ny][nx] = true;
		  }
	  }
	  
	  return cnt;
  }
  
  static class Coord{
	  int y;
	  int x;
	  public Coord(int y, int x) {
		  this.y = y;
		  this.x = x;
	  }
  }
}