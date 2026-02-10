import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve();
    }
    
    static int length;
    static Node[] nodes;
    static int root;
    static void input(BufferedReader in) throws IOException {
        length = Integer.parseInt(in.readLine());
        nodes = new Node[length];
        for(int i = 0; i < length; i++) {
            nodes[i] = new Node();
        }
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < length; i++) {
            int parent = Integer.parseInt(tokens.nextToken());
            
            if(parent == -1) {
                root = i;
                continue;
            }
            
            nodes[i].parent = parent;
            nodes[parent].childs.add(i);
        }
        
        int erased = Integer.parseInt(in.readLine());
        
        int idx = nodes[erased].parent;
        if(idx == -1) root = -1;
        else nodes[idx].childs.remove(erased);
    }
    
    static int cnt;
    static void solve() {
        cnt = 0;
        if(root >= 0) {
          traverse(root);
        }
        System.out.println(cnt);
    }
    
    static void traverse(int idx) {
        if(nodes[idx].childs.isEmpty()) {
            cnt++;
            return;
        }
        
        for(int next : nodes[idx].childs) {
            traverse(next);
        }
    }
    
    static class Node {
        int parent = -1;
        TreeSet<Integer> childs = new TreeSet();
    }
}