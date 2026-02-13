import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int size;
    static char[][] relation;
    public static void input(BufferedReader in) throws IOException {
        size = Integer.parseInt(in.readLine());
        relation = new char[size][size];
        for(int i = 0 ; i < size; i++) {
            relation[i] = in.readLine().toCharArray();
        }
    }
    
    public static void solve() {
        
        int max = 0;
        
        for(int i = 0; i < size; i++) {
            ArrayList<Integer> friends = new ArrayList();
            
            for(int j = 0; j < size; j++) {
                if(i == j) continue;
                if(relation[i][j] == 'Y') friends.add(j);
            }
            
            HashSet<Integer> known = new HashSet();
            
            for(int f : friends) {
                known.add(f);
                
                for(int j = 0; j < size; j++) {
                    if(i == j) continue;
                    if(relation[f][j] == 'Y') known.add(j);
                }
            }
            
            max = Math.max(max, known.size());
        }
        
        System.out.println(max);
    }
}