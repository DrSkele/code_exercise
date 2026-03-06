import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int students;
    static ArrayList<Integer>[] bigger;
    static ArrayList<Integer>[] smaller;
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        students = Integer.parseInt(tokens.nextToken());
        int length = Integer.parseInt(tokens.nextToken());
        
        bigger = new ArrayList[students+1];
        smaller = new ArrayList[students+1];
        
        for(int i = 0; i < students+1; i++) {
          bigger[i] = new ArrayList<>();
          smaller[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < length; i++) {
            tokens = new StringTokenizer(in.readLine());
            int small = Integer.parseInt(tokens.nextToken());
            int big = Integer.parseInt(tokens.nextToken());
            
            smaller[big].add(small);
            bigger[small].add(big);
        }
    }
    
    static boolean[] visited;
    static void solve() {
        visited = new boolean[students+1];
        
        int cnt = 0;
        
        for(int i = 1; i <= students; i++) {
            visited = new boolean[students+1];
            int small = getElements(i, smaller) - 1;
            visited = new boolean[students+1];
            int big = getElements(i, bigger) - 1;
            
            // System.out.printf("%d %d %d \n", i, small, big);
            
            if(small + big + 1 == students) cnt++;
        }
        
        System.out.println(cnt);
    }
    
    static int getElements(int idx, ArrayList<Integer>[] arr) {
        visited[idx] = true;
        
        int cnt = 1;
        for(int next : arr[idx]) {
            if(visited[next]) continue;
            cnt += getElements(next, arr);
        }
        
        return cnt;
    }
}