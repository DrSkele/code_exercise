import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        int sec = 0;
        
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[100001];
        q.add(N);
        visited[N] = 0;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            int curVal = visited[cur];
            
            if(cur == K) {
                sec = curVal;
                break;
            }
            
            if(cur - 1 >= 0 && (visited[cur-1] == 0 || visited[cur-1] > curVal+1)) {
                q.add(cur-1);
                visited[cur-1] = curVal+1;
            }
            if(cur + 1 < 100001 && (visited[cur+1] == 0 || visited[cur+1] > curVal+1)) {
                q.add(cur+1);
                visited[cur+1] = curVal+1;
            }
            if(2*cur < 100001 && (visited[2*cur] == 0 || visited[2*cur] > curVal+1)){
                q.add(2*cur);
                visited[2*cur] = curVal+1;
            }
        }
        System.out.println(sec);
    }
}