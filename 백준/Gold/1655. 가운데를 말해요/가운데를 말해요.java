import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        
        PriorityQueue<Integer> qLow = new PriorityQueue<>(Collections.reverseOrder());
        int mid = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> qHigh = new PriorityQueue<>();
        
        StringBuilder str = new StringBuilder();
        str.append(mid).append("\n");
        
        for(int i = 1; i < n; i++){
            int cur = Integer.parseInt(in.readLine());
            
            if(qHigh.isEmpty()){
                if(mid < cur){
                    qHigh.add(cur);
                } else {
                    qHigh.add(mid);
                    mid = cur;
                }
            } else if (qLow.isEmpty()){
                if(cur < mid){
                    qLow.add(cur);
                } else if (mid < cur && cur < qHigh.peek()){
                    qLow.add(mid);
                    mid = cur;
                } else {
                    qLow.add(mid);
                    mid = qHigh.poll();
                    qHigh.add(cur);
                }
            } else {
                if(cur < mid){
                    qLow.add(cur);
                    if(qLow.size() > qHigh.size()){
                        qHigh.add(mid);
                        mid = qLow.poll();
                    }
                } else {
                    qHigh.add(cur);
                    if(qLow.size()+1 < qHigh.size()){
                        qLow.add(mid);
                        mid = qHigh.poll();
                    }
                }
            }
            str.append(mid).append("\n");
        }
        System.out.print(str.toString());
    }
}