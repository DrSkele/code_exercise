import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int size = Integer.parseInt(in.readLine());
        int appleCnt = Integer.parseInt(in.readLine());
        char[][] matrix = new char[size][size];
        
        char snake = 'S';
        char apple = 'A';
        
        char left = 'L';
        char right = 'D';
        
        // apples
        for(int i = 0; i < appleCnt; i++) {
            StringTokenizer tokens = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(tokens.nextToken()) - 1;
            int x = Integer.parseInt(tokens.nextToken()) - 1;
            
            matrix[y][x] = apple;
        }
        
        // directions
        int cmdCnt = Integer.parseInt(in.readLine());
        HashMap<Integer, Character> cmd = new HashMap();
        
        for(int i = 0; i < cmdCnt; i++) {
            StringTokenizer tokens = new StringTokenizer(in.readLine());
            int sec = Integer.parseInt(tokens.nextToken());
            char dir = tokens.nextToken().charAt(0);
            
            cmd.put(sec, dir);
        }
        // input finished
        
        ArrayDeque<int[]> body = new ArrayDeque();
        
        matrix[0][0] = snake;
        body.add(new int[] {0, 0});
        
        int cnt = 0;
        int curDir = 0;
        
        int[] dx = { +1, 0, -1, 0 };
        int[] dy = { 0, +1, 0, -1 };
        
        while(true) {
            int[] head = body.peekLast();
            int y = head[0];
            int x = head[1];
            
            // direction change check
            if(cmd.containsKey(cnt)) {
                char nextDir = cmd.get(cnt);
                if(nextDir == left) {
                    curDir = (curDir + 3) % 4;
                } else {
                    curDir = (curDir + 1) % 4;
                }
            }
            
            int ny = y + dy[curDir];
            int nx = x + dx[curDir];
            
            // wall check
            if(ny < 0 || ny >= size || nx < 0 || nx >= size) break;
            // body check
            if(matrix[ny][nx] == snake) break;
            
            // not eaten apple
            if(matrix[ny][nx] != apple) {
                int[] tail = body.poll();
                int ty = tail[0];
                int tx = tail[1];
                
                matrix[ty][tx] = ' ';
            }
            
            body.add(new int[] {ny, nx});
            matrix[ny][nx] = snake;
            
            cnt++;
            
            // for(int i = 0; i < size; i++) {
            //   for(int j = 0; j < size; j++) {
            //     System.out.print(matrix[i][j]);
            //   }
            //   System.out.println();
            // }
        }
        
        System.out.println(cnt + 1);
    }
}