import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static PriorityQueue<Request> queue;
    static int max;
    public static void input(BufferedReader in) throws IOException {
        length = Integer.parseInt(in.readLine());
        queue = new PriorityQueue<>(new Comparator<Request>() {
            public int compare(Request first, Request second) {
                int diff = second.fee - first.fee;
                if(diff == 0) return first.due - second.due;
                else return diff;
            }
        });
        max = 0;
        for(int i = 0; i < length; i++) {
            StringTokenizer tokens = new StringTokenizer(in.readLine());
            int fee = Integer.parseInt(tokens.nextToken());
            int due = Integer.parseInt(tokens.nextToken());
            
            queue.add(new Request(due, fee));
            
            if(max < due) max = due;
        }
    }
    
    public static void solve() {
        int sum = 0;
        
        boolean[] schedule = new boolean[max+1];
        
        while(!queue.isEmpty()) {
            Request cur = queue.poll();
            
            for(int i = cur.due; i > 0; i--) {
                if(!schedule[i]) {
                    schedule[i] = true;
                    sum += cur.fee;
                    break;
                }
            }
        }
        
        System.out.println(sum);
    }
    
    static class Request {
        int due;
        int fee;
        
        public Request(int d, int f) {
            due = d;
            fee = f;
        }
    }
}