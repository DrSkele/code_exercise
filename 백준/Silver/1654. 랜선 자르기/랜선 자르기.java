import java.io.*;
import java.util.*;

class Main{
    static int[] cables;
    static int ownes;
    static int need;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        ownes = Integer.parseInt(tokens.nextToken());
        need = Integer.parseInt(tokens.nextToken());
        
        cables = new int[ownes];
        
        for(int i =0;i < ownes; i++){
            tokens = new StringTokenizer(in.readLine());
            cables[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(cables);
        
        long last = cables[ownes-1] > 1_000_000 ? 1_000_000 : cables[ownes-1];
        
        System.out.print(cutCables(1, cables[ownes-1]));
    }
    
    static long cutCables(long start, long end){
        
        long mid = (start+end)/2;
        
        if(start > end) return mid;
        
        int cut = getNumber(mid);
        
        if(cut < need){
            return cutCables(start, mid-1);
        } else {
            return cutCables(mid+1, end);
        } 
    }
    
    static int getNumber(long length){
        
        int cnt = 0;
        
        for(int i = 0; i < ownes; i++){
            cnt += cables[i]/length;    
        }
        
        return cnt;
    }
}