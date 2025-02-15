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
  static int[][] map;
  public static void input(BufferedReader in) throws IOException {
	  StringTokenizer tokens = new StringTokenizer(in.readLine());
	  height = Integer.parseInt(tokens.nextToken());
	  width = Integer.parseInt(tokens.nextToken());
	  
	  map = new int[height][width];
	  for(int y = 0; y < height; y++) {
		  tokens = new StringTokenizer(in.readLine());
		  for(int x = 0; x < width; x++) {
			  map[y][x] = Integer.parseInt(tokens.nextToken());
		  }
	  }
  }
  
  public static void solve() {
	  island();
	  bridge();
	  System.out.println(findMinLength());
  }
  
  static int[] dx = { +1, 0, -1, 0 };
  static int[] dy = { 0, +1, 0, -1 };
  static ArrayList<ArrayList<Coord>> islands;
  static void island() {
	  islands = new ArrayList<>();
	  
	  boolean[][] visited = new boolean[height][width];
	  
	  int num = 1;
	  
	  for(int y = 0; y < height; y++) {
		  for(int x = 0; x < width; x++) {
			  if(visited[y][x] || map[y][x] == 0) continue;
			  
			  ArrayList<Coord> list = new ArrayList<>();
			  
			  ArrayDeque<Coord> q = new ArrayDeque<>();
			  
			  Coord start = new Coord(y, x); 
			  
			  q.add(start);
			  list.add(start);
			  visited[y][x] = true;
			  map[y][x] = num;
			  
			  while(!q.isEmpty()) {
				  Coord cur = q.poll();
				  
				  for(int i = 0; i < 4; i++) {
					  int nx = cur.x + dx[i];
					  int ny = cur.y + dy[i];
					  
					  if(nx < 0 || nx >= width || ny < 0 || ny >= height) continue;
					  if(visited[ny][nx] || map[ny][nx] == 0) continue;
					  
					  Coord next = new Coord(ny, nx);
					  q.add(next);
					  list.add(next);
					  visited[ny][nx] = true;
					  map[ny][nx] = num;
				  }
			  }
			  
			  islands.add(list);
			  num++;
		  }
	  }
	  
//	  for(int y = 0; y < height; y++) {
//		  for(int x = 0; x < width; x++) {
//			  System.out.print(map[y][x] + " ");
//		  }  
//		  System.out.println();
//	  }
  }
  
  static ArrayList<Bridge> bridges;
  static void bridge() {
	  
	  bridges = new ArrayList<>();
	  
	  for(ArrayList<Coord> island : islands) {
		  
		  int num = map[island.get(0).y][island.get(0).x];
		  
		  for(Coord coord : island) {
			  
			  for(int i = 0; i < 4; i++) {
				  int nx = coord.x + dx[i];
				  int ny = coord.y + dy[i];
				  int length = 0;
				  int dest = -1;
				  
				  while(nx >= 0 && nx < width && ny >= 0 && ny < height) {
					  if(map[ny][nx] != 0) {
						  dest = map[ny][nx];
						  break;
					  }
					  nx += dx[i];
					  ny += dy[i];
					  length++;
				  }
				  
				  if(length >= 2 && dest > 0 && num != dest) {
					  bridges.add(new Bridge(num, dest, length));
				  }
			  }
		  }
	  }
	  
	  Collections.sort(bridges);
  }
  
  static int[] root;
  static int findMinLength() {
	  root = new int[islands.size()+1];
	  
	  for(int i = 1; i < root.length; i++) {
		  root[i] = i;
	  }
	  
	  int sum = 0;
	  
	  for(Bridge bridge : bridges) {
		  if(union(bridge.end1, bridge.end2)) {
			  sum += bridge.length;
			  //System.out.println(bridge.end1 + " " + bridge.end2 + " " + bridge.length);
		  }
	  }
	  
	  boolean isValid = true;
	  int num = find(1);
	  for(int i = 2; i < root.length; i++) {
		  if(num != find(i)) {
			  isValid = false;
			  break;
		  }
	  }
	  
	  return (isValid && sum > 0) ? sum : -1;
  }
  
  static boolean union(int a, int b) {
	  a = find(a);
	  b = find(b);
	  
	  if(a == b) return false;
	  
	  if(a < b) root[b] = a;
	  else root[a] = b;
	  
	  return true;
  }
  
  static int find(int i) {
	  if(root[i] == i) return i;
	  
	  return root[i] = find(root[i]);
  }
  
  static class Bridge implements Comparable<Bridge>{
	  int end1;
	  int end2;
	  int length;
	  public Bridge(int e1, int e2, int l) {
		  end1 = e1;
		  end2 = e2;
		  length = l;
	  }
	  public int compareTo(Bridge b) {
		  return length - b.length;
	  }
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