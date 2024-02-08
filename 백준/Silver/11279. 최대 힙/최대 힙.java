import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        
        StringBuilder str = new StringBuilder();
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < N; i++){
            int value = Integer.parseInt(in.readLine());
            if(value > 0){
                pQ.add(value);
            } else {
                int output = 0;
                if(!pQ.isEmpty()){
                    output = pQ.poll();
                }
                str.append(output).append('\n');
            }
        }
        
        System.out.print(str.toString());
    }
}