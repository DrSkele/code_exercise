import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        init(in);
        
        solve();
    }
    static int N;
    static int row;
    static int column;
    static int result;
    static void init(BufferedReader in) throws IOException{
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        N = Integer.parseInt(tokens.nextToken());
        row = Integer.parseInt(tokens.nextToken());
        column = Integer.parseInt(tokens.nextToken());
        result = 0;
    }
    
    static void solve(){
        reduce((int)Math.pow(2, N), 0);
        System.out.println(result);
    }
    
    static void reduce(int size, int order){
        if(size == 1){
            result = order;
            return;
        }
        
        int half = size/2;
        if(row < half && column < half){
            reduce(half, order);
        } else if(row < half && column >= half){
            column -= half;
            reduce(half, order + (half * half));
        } else if(row >= half && column < half){
            row -= half;
            reduce(half, order + 2 * (half * half));
        } else {
            row -= half;
            column -= half;
            reduce(half, order + 3 * (half * half));
        }
    }
}