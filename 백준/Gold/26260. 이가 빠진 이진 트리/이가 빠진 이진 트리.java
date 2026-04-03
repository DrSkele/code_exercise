import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static long size;
    static ArrayList<Integer> arr;
    static void input(BufferedReader in) throws IOException {
        size = Long.parseLong(in.readLine()) + 1;
        arr = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        long idx = 0;
        for(int i = 1; i < size; i++) {
            int val = Integer.parseInt(tokens.nextToken());
            if(val == -1) continue;
            arr.add(val);
        }
        arr.add(Integer.parseInt(in.readLine()));
        
        Collections.sort(arr);
    }
    
    static StringBuilder str;
    static int head;
    static void solve() {
        str = new StringBuilder();
        head = 0;
        traverse(1);
        System.out.println(str);
    }
    
    static void traverse(long idx) {
        if(idx*2 < size) {
            traverse(idx*2);
        }
        
        int mid = arr.get(head);
        head++;
        
        if(idx*2 + 1 < size) {
            traverse(idx*2+1);
        }
        str.append(mid).append(' ');
    }
}