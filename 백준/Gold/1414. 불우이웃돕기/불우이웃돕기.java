import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int size;
  static int[][] lan;
  static int total;
  public static void input(BufferedReader in) throws IOException {
    size = Integer.parseInt(in.readLine());
    lan = new int[size][size];
    total = 0;
    for(int i = 0; i < size; i++) {
      char[] line = in.readLine().toCharArray();
      for(int j = 0; j < size; j++) {
        int cur = toNum(line[j]);
        total += cur;
        
        if(cur == 0) {
          lan[i][j] = lan[j][i];
        } else {
          int other = lan[j][i];
          
          if(other != 0 && other < cur) {
            lan[i][j] = lan[j][i];
          } else {
            lan[j][i] = cur;
            lan[i][j] = cur;
          }
        }
      }
    }
    
    // for(int i = 0; i < size; i++) {
    //   for(int j = 0; j < size; j++) {
    //     System.out.print(lan[i][j]);
    //   }
    //   System.out.println();
    // }
  }
  
  static int toNum(char ch) {
    if(ch == '0') return 0;
    if('a' <= ch && ch <= 'z') return ch - 'a' + 1;
    else return ch - 'A' + 27;
  }
  
  public static void solve() {
    int result = dijkstra();
    System.out.println(result >= 0 ? total - result : result);
  }
  
  static int dijkstra() {
    boolean[] visited = new boolean[size];
    PriorityQueue<Connection> q = new PriorityQueue<>();
    
    q.add(new Connection(0, 0, 1));
    
    int sum = 0;
    
    while(!q.isEmpty()) {
      Connection cur = q.poll();
      
      if(visited[cur.dest]) continue;
      visited[cur.dest] = true;
      if(cur.from != cur.dest) sum += cur.length;
      
      for(int i = 0; i < size; i++) {
        if(i == cur.dest || lan[cur.dest][i] == 0 || visited[i]) continue;
        
        q.add(new Connection(cur.dest, i, lan[cur.dest][i]));
      }
    }
    
    boolean visitedAll = true;
    for(int i = 0; i < size; i++) {
      if(!visited[i]){
        visitedAll = false;
        break;
      }
    }
    
    return visitedAll ? sum : -1;
  }
  
  static class Connection implements Comparable<Connection>{
    int from;
    int dest;
    int length;
    public Connection(int f, int d, int l) {
      this.from = f;
      this.dest = d;
      this.length = l;
    }
    
    public int compareTo(Connection co) {
      if(this.length < co.length) return -1;
      else if(this.length > co.length) return 1;
      else return 0;
    }
  }
}