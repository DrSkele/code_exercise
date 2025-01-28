import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    input(in);
    solve();
  }
  
  static int nSinger;
  static int nProducer;
  static ArrayList<Integer>[] order;
  static int[] degree;
  public static void input(BufferedReader in) throws IOException {
    StringTokenizer tokens = new StringTokenizer(in.readLine());
    nSinger = Integer.parseInt(tokens.nextToken());
    nProducer = Integer.parseInt(tokens.nextToken());
    
    order = (ArrayList<Integer>[]) new ArrayList[nSinger];
    degree = new int[nSinger];
    
    for(int i = 0; i < nSinger; i++) {
      order[i] = new ArrayList<>();
    }
    for(int i = 0; i < nProducer; i++) {
      tokens = new StringTokenizer(in.readLine());
      int cnt = Integer.parseInt(tokens.nextToken());
      
      int from = Integer.parseInt(tokens.nextToken())-1;
      for(int j = 1; j < cnt; j++) {
        int to = Integer.parseInt(tokens.nextToken())-1;
        
        order[from].add(to);
        degree[to]++;
        
        from = to;
      }
    }
  }
  
  public static void solve() {
    
    ArrayList<Integer> list = new ArrayList<>();
    ArrayDeque<Integer> q = new ArrayDeque<>();
    
    for(int i = 0; i < nSinger; i++) {
      if(degree[i] == 0) q.add(i);
    }
    
    while(!q.isEmpty()) {
      int cur = q.poll();
      list.add(cur);
      
      for(int next : order[cur]) {
        degree[next]--;
        
        if(degree[next] == 0) q.add(next);
      }
    }
    
    if(list.size() < nSinger) {
      System.out.println(0);
      return;
    } else {
      StringBuilder str = new StringBuilder();
      for(int singer : list) {
        str.append(singer+1).append("\n");
      }
      System.out.println(str.toString());
    }
  }
}