import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static int[] prob;
    static int[] dy = {0, 0, +1, -1};
    static int[] dx = {+1, -1, 0, 0};
    public static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        length = Integer.parseInt(tokens.nextToken());
        prob = new int[4];
        for(int i = 0; i < 4; i++) {
            prob[i] = Integer.parseInt(tokens.nextToken());
        }
    }
    
    static double[][] matrix;
    static boolean[][] visited;
    static double sum;
    public static void solve() {
        matrix = new double[length * 2 + 1][length * 2 + 1];
        visited = new boolean[length * 2 + 1][length * 2 + 1];
        sum = 0.0;
        
        visited[length][length] = true;
        traverse(length, length, 0, 1.0);
        
        System.out.println(sum);
    }
    
    static void traverse(int y, int x, int depth, double probVal) {
        if(depth == length) {
            sum += probVal;
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if(visited[ny][nx] || prob[i] == 0) continue;
            
            visited[ny][nx] = true;
            double newProb = probVal * (prob[i] / 100.0);
            traverse(ny, nx, depth+1, newProb);
            visited[ny][nx] = false;
        }
    }
}