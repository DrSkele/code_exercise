import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static Integer[] cranes;
    static Integer[] boxes;
    public static void input(BufferedReader in) throws IOException {
        int craneNum = Integer.parseInt(in.readLine());
        cranes = new Integer[craneNum];
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < craneNum; i++) {
            cranes[i] = Integer.parseInt(tokens.nextToken());
        }
        
        int boxNum = Integer.parseInt(in.readLine());
        boxes = new Integer[boxNum];
        tokens =new StringTokenizer(in.readLine());
        for(int i = 0; i < boxNum; i++) {
            boxes[i] = Integer.parseInt(tokens.nextToken());
        }
        
        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(boxes, Collections.reverseOrder());
    }
    
    public static void solve() {
        
        int cnt = 0;
        int total = boxes.length;
        boolean[] shipped = new boolean[boxes.length];
        
        if(boxes[0] > cranes[0]) {
            System.out.println(-1);
            return;
        }
        
        while(total > 0) {
            int idx = 0;
            for(int i = 0; i < cranes.length; i++) {
                int power = cranes[i];
                
                while(idx < boxes.length) {
                    if(shipped[idx]) {
                      idx++;
                      continue;
                    }
                    
                    if(boxes[idx] <= power) {
                        shipped[idx] = true;
                        total--;
                        break;
                    }
                    
                    idx++;
                }
            }
            cnt++;
        }
        
        System.out.println(cnt);
    }
    
}