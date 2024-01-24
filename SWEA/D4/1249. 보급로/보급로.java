import java.util.*;
import java.io.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int T=Integer.parseInt(tokens.nextToken());
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int size = Integer.parseInt(in.readLine());
            int[][] matrix = new int[size][size];
            int[][] visited = new int[size][size];
            for(int y = 0; y < size; y++){
                char[] line = in.readLine().toCharArray();
                for(int x = 0; x < size; x++){
                    matrix[y][x] = Character.getNumericValue(line[x]);
                    visited[y][x] = Integer.MAX_VALUE;
                }
            }
             
            Queue<int[]> q = new LinkedList<>();
             
            int[] dx = {+1, 0, -1, 0};
            int[] dy = {0, +1, 0, -1};
             
            q.add(new int[]{0, 0});
            visited[0][0] = matrix[0][0];
                         
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int curVal = visited[y][x];
                 
                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                     
                    if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                     
                    int nextVal = curVal + matrix[ny][nx];
                    if(nextVal >= visited[ny][nx]) continue;
                     
                    q.add(new int[]{nx, ny});
                    visited[ny][nx] = nextVal;
                }
            }
             
            System.out.println(String.format("#%d %d", test_case, visited[size-1][size-1]));
        }
    }
}