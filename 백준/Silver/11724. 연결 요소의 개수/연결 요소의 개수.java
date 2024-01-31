import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int vertex = Integer.parseInt(tokens.nextToken());
        int line = Integer.parseInt(tokens.nextToken());
        
        boolean[][] map = new boolean[vertex][vertex];
        boolean[] visited = new boolean[vertex];
        
        for(int i =0; i < line; i++){
            tokens = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(tokens.nextToken())-1;
            int v2 = Integer.parseInt(tokens.nextToken())-1;
            
            map[v1][v2] = true;
            map[v2][v1] = true;
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        int cnt = 0;
        for(int i = 0; i < vertex; i++){
            if(visited[i]) continue;
            cnt++;
            for(int j = i+1; j < vertex; j++){
                if(map[i][j] || map[j][i]){
                    q.add(i);
                    while(!q.isEmpty()){
                        int cur = q.poll();
                        for(int k = 0; k < vertex; k++){
                            if(visited[k]) continue;
                            if(map[cur][k] || map[k][cur]){
                                q.add(k);
                                map[cur][k] = false;
                                map[k][cur] = false;
                                visited[k] = true;
                            }
                        }
                        visited[cur] = true;
                    }
                }
            }
        }
        
        System.out.println(cnt);
    }
}