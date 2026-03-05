import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int total = Integer.parseInt(in.readLine());
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int first = Integer.parseInt(tokens.nextToken());
        int second = Integer.parseInt(tokens.nextToken());
        
        int length = Integer.parseInt(in.readLine());
        boolean[][] relation = new boolean[total+1][total+1];
        
        for(int i = 0; i < length; i++) {
            tokens = new StringTokenizer(in.readLine());
            int parent = Integer.parseInt(tokens.nextToken());
            int child = Integer.parseInt(tokens.nextToken());
            
            relation[parent][child] = true;
            relation[child][parent] = true;
        }
        
        int dist = findDist(0, first, second, 0, relation);
        
        System.out.println(dist);
    }
    
    static int findDist(int from, int cur, int goal, int depth, boolean[][] relation) {
        for(int i = 1; i < relation[cur].length; i++) {
            if(!relation[cur][i] || i == from) continue;
            
            if(i == goal) return depth+1;
            int dist = findDist(cur, i, goal, depth+1, relation);
            if(dist > 0) return dist;
        }
        return -1;
    }
}