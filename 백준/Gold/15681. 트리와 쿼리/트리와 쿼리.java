import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int totalNodes = Integer.parseInt(tokens.nextToken());
        int root = Integer.parseInt(tokens.nextToken()) - 1;
        int totalQuery = Integer.parseInt(tokens.nextToken());
        
        Node[] node = new Node[totalNodes];
        for(int i = 0; i < totalNodes; i++) {
            node[i] = new Node(i);
        }
        
        for(int i = 0; i < totalNodes - 1; i++) {
            tokens = new StringTokenizer(in.readLine());
            int first = Integer.parseInt(tokens.nextToken()) - 1;
            int second = Integer.parseInt(tokens.nextToken()) - 1;
            
            node[first].addNode(node[second]);
            node[second].addNode(node[first]);
        }
        
        makeTree(node[root]);
        
        StringBuilder str = new StringBuilder();
        
        for(int i = 0; i < totalQuery; i++) {
            int query = Integer.parseInt(in.readLine()) - 1;
            str.append(countChild(node[query])).append('\n');
        }
        
        System.out.println(str);
    }
    
    static void makeTree(Node root) {
        for(Node child : root.child) {
            if(child.idx == root.parent) continue;
            child.parent = root.idx;
            makeTree(child);
        }
    }
    
    static int countChild(Node root) {
        if(root.childCnt != -1) return root.childCnt;
        
        root.childCnt = 1;
        for(Node child : root.child) {
            if(child.idx == root.parent) continue;
            root.childCnt += countChild(child);
        }
        return root.childCnt;
    }
    
    static class Node {
        int idx = -1;
        int parent = -1;
        int childCnt = -1;
        ArrayList<Node> child;
        
        public Node(int idx) {
            this.idx = idx;
            child = new ArrayList<>();
        }
        
        public void addNode(Node other) {
            child.add(other);
        }
    }
}