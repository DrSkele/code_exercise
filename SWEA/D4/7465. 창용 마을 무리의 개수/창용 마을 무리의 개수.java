import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(in.readLine());
         
        for(int t = 1; t <= T; t++) {
            init(in);
             
            solve(t, in);
        }
    }
     
    static int N;
    static int M;
    static int[] union;
    static void init(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        union = new int[N];
        for(int i = 0; i < N; i++) {
            union[i] = i;
        }
        for(int i = 0; i < M; i++) {
            tokens = new StringTokenizer(in.readLine());
             
            int a = Integer.parseInt(tokens.nextToken()) - 1;
            int b = Integer.parseInt(tokens.nextToken()) - 1;
            union(a, b);
        }
    }
     
    static void solve(int t, BufferedReader in) throws IOException {
         
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            set.add(find(i));
        }
        System.out.println(String.format("#%d %d", t, set.size()));
    }
     
    static int find(int a) {
        if(union[a] == a) return a;
        return union[a] = find(union[a]);
    }
     
    static void union(int a, int b) {
        int uA = find(a);
        int uB = find(b);
        union[uB] = uA;
    }
}