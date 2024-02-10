import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i <= n; i++){
            q.add(i);
        }
        boolean discard = true;
        while(q.size() > 1){
            if(discard){
                q.poll();
            } else {
                q.add(q.poll());
            }
            discard = !discard;
        }
        System.out.print(q.poll());
    }
}