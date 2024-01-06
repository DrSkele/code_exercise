import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        int nodes = Integer.parseInt(tokens.nextToken());
        int lines = Integer.parseInt(tokens.nextToken());
        int start = Integer.parseInt(tokens.nextToken());
        
        boolean[][] map = new boolean[nodes+1][nodes+1];
        
        for(int t = 0; t < lines; t++){
            tokens = new StringTokenizer(reader.readLine());
            int first = Integer.parseInt(tokens.nextToken());
            int second = Integer.parseInt(tokens.nextToken());
            
            map[first][second] = true;
            map[second][first] = true;
        }
        //DFS
        Stack<Integer> dfsQ = new Stack<>();
        boolean[] visitedDFS = new boolean[nodes+1];
        
        dfsQ.push(start);
        
        while(!dfsQ.isEmpty()){
            int cur = dfsQ.pop();
            if(visitedDFS[cur]) continue;
            else visitedDFS[cur] = true;
            for(int i = nodes; i >= 0; i--){
                if(map[cur][i] && !visitedDFS[i]){
                    dfsQ.push(i);
                }
            }
            System.out.print(cur + " ");
        }
        System.out.println();
        
        //BFS
        Queue<Integer> bfsQ = new LinkedList<>();
        boolean[] visitedBFS = new boolean[nodes+1];
        
        bfsQ.add(start);
        
        while(!bfsQ.isEmpty()){
            int cur = bfsQ.poll();
            for(int i = 0; i < nodes+1; i++){
                if(map[cur][i] && !visitedBFS[i]){
                    bfsQ.add(i);
                    visitedBFS[i] = true;
                }
            }
            visitedBFS[cur] = true;
            System.out.print(cur + " ");
        }
    }
}