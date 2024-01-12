import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        StringBuilder out = new StringBuilder();
        int N = Integer.parseInt(tokens.nextToken());
        
        Deque<String> dq = new ArrayDeque<>();
        
        for(int i = 0; i < N; i++){
            tokens = new StringTokenizer(in.readLine());
            switch(tokens.nextToken()){
                case "push_front":
                    dq.addFirst(tokens.nextToken());                   
                    break;
                case "push_back":
                    dq.addLast(tokens.nextToken());
                    break;
                case "pop_front":
                    out.append(dq.isEmpty()?"-1" : dq.pollFirst());
                    out.append("\n");
                    break;
                case "pop_back":
                    out.append(dq.isEmpty()?"-1" : dq.pollLast());
                    out.append("\n");
                    break;
                case "size":
                    out.append(dq.size());  
                    out.append("\n");
                    break;
                case "empty":
                    out.append(dq.isEmpty()?"1" : "0");
                    out.append("\n");
                    break;
                case "front":
                    out.append(dq.isEmpty()?"-1" : dq.peekFirst()); 
                    out.append("\n");
                    break;
                case "back":
                    out.append(dq.isEmpty()?"-1" : dq.peekLast()); 
                    out.append("\n");
                    break;
            }
        }
        System.out.print(out.toString());
        
    }
}