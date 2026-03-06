import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int students;
    static boolean[][] relation;
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        students = Integer.parseInt(tokens.nextToken());
        int length = Integer.parseInt(tokens.nextToken());
        
        relation = new boolean[students+1][students+1];
        
        for(int i = 0; i < length; i++) {
            tokens = new StringTokenizer(in.readLine());
            int small = Integer.parseInt(tokens.nextToken());
            int big = Integer.parseInt(tokens.nextToken());
            
            relation[small][big] = true;
        }
    }
    
    static void solve() {
        
        for(int k = 1; k <= students; k++) {
          for(int i = 1; i <= students; i++) {
            for(int j = 1; j <= students; j++) {
              if(relation[i][k] && relation[k][j]) {
                relation[i][j] = true;
              }
            }
          }
        }
        
        int cnt = 0;
        
        for(int i = 1; i <= students; i++) {
          int reachable = 0;
          
          for(int j = 1; j <= students; j++) {
            if(i == j) continue;
            
            if(relation[i][j] || relation[j][i]) reachable++;
          }
          
          if(reachable == students-1) cnt++;
        }
        
        System.out.println(cnt);
    }
}