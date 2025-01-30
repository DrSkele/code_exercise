import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int num;
  static int[] time;
  static ArrayList<Integer>[] order;
  static int[] degree;
  public static void input(BufferedReader in) throws IOException {
    num = Integer.parseInt(in.readLine());
    time = new int[num+1];
    order = (ArrayList<Integer>[]) new ArrayList[num+1];
    degree = new int[num+1];
    
    for(int i = 0; i < order.length; i++) {
      order[i] = new ArrayList<>();
    }
    
    for(int i = 1; i < num+1; i++) {
      StringTokenizer tokens = new StringTokenizer(in.readLine());
      time[i] = Integer.parseInt(tokens.nextToken());
      int cnt = Integer.parseInt(tokens.nextToken());
      
      int to = i;
      for(int j = 0; j < cnt; j++) {
        int from = Integer.parseInt(tokens.nextToken());
        order[from].add(to);
        degree[to]++;
      }
    }
  }
  
  public static void solve() {
    int[] minTime = new int[num+1];
    
    ArrayDeque<Integer> q = new ArrayDeque<>();
    
    for(int i = 0; i < degree.length; i++) {
      if(degree[i] == 0) {
        q.add(i);
        minTime[i] = time[i];
      }
    }
    
    while(!q.isEmpty()) {
      int cur = q.poll();
      int curTime = minTime[cur];
      
      for(int next : order[cur]) {
        degree[next]--;
        minTime[next] = Math.max(minTime[next], curTime + time[next]);
        if(degree[next] == 0) q.add(next);
      }
    }
    
    int answer = 0;
    for(int t : minTime) {
      answer = Math.max(answer, t);
    }
    System.out.println(answer);
  }
}