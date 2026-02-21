import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int length = Integer.parseInt(in.readLine());
        
        if(length <= 2) {
            StringTokenizer tokens = new StringTokenizer(in.readLine());
            int firstR = Integer.parseInt(tokens.nextToken());
            int firstG = Integer.parseInt(tokens.nextToken());
            int firstB = Integer.parseInt(tokens.nextToken());
            
            tokens = new StringTokenizer(in.readLine());
            int endR = Math.min(firstG, firstB) + Integer.parseInt(tokens.nextToken());
            int endG = Math.min(firstR, firstB) + Integer.parseInt(tokens.nextToken());
            int endB = Math.min(firstR, firstG) + Integer.parseInt(tokens.nextToken());
            
            int min = Math.min(endR, Math.min(endG, endB));
            
            System.out.println(min);
            return;
        }
        
        int R = 0;
        int G = 1;
        int B = 2;
        
        int[][] r = new int[length][3];
        int[][] g = new int[length][3];
        int[][] b = new int[length][3];
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        r[0][R] = Integer.parseInt(tokens.nextToken());
        r[0][G] = 100000000;
        r[0][B] = 100000000;
        
        g[0][R] = 100000000;
        g[0][G] = Integer.parseInt(tokens.nextToken());
        g[0][B] = 100000000;
        
        b[0][R] = 100000000;
        b[0][G] = 100000000;
        b[0][B] = Integer.parseInt(tokens.nextToken());
        
        for(int i = 1; i <= length - 2; i++) {
            tokens = new StringTokenizer(in.readLine());
            int red = Integer.parseInt(tokens.nextToken());
            int green = Integer.parseInt(tokens.nextToken());
            int blue = Integer.parseInt(tokens.nextToken());
            
            r[i][R] = Math.min(r[i-1][1], r[i-1][2]) + red;
            r[i][G] = Math.min(r[i-1][0], r[i-1][2]) + green;
            r[i][B] = Math.min(r[i-1][0], r[i-1][1]) + blue;
            
            g[i][R] = Math.min(g[i-1][1], g[i-1][2]) + red;
            g[i][G] = Math.min(g[i-1][0], g[i-1][2]) + green;
            g[i][B] = Math.min(g[i-1][0], g[i-1][1]) + blue;
            
            b[i][R] = Math.min(b[i-1][1], b[i-1][2]) + red;
            b[i][G] = Math.min(b[i-1][0], b[i-1][2]) + green;
            b[i][B] = Math.min(b[i-1][0], b[i-1][1]) + blue;
        }
        
        tokens = new StringTokenizer(in.readLine());
        int minR = Integer.parseInt(tokens.nextToken()) + Math.min(Math.min(g[length-2][G], g[length-2][B]), Math.min(b[length-2][G], b[length-2][B]));
        int minG = Integer.parseInt(tokens.nextToken()) + Math.min(Math.min(r[length-2][R], r[length-2][B]), Math.min(b[length-2][R], b[length-2][B]));
        int minB = Integer.parseInt(tokens.nextToken()) + Math.min(Math.min(r[length-2][R], r[length-2][G]), Math.min(g[length-2][R], g[length-2][G]));
        
        int min = Math.min(minR, Math.min(minG, minB));
        
        System.out.println(min);
    }
}