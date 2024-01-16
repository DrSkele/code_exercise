import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int numP = Integer.parseInt(tokens.nextToken());
        int varC = Integer.parseInt(tokens.nextToken());
        
        int[][] prefer = new int[numP][varC];
        for(int i =0; i< numP; i++){
            tokens = new StringTokenizer(in.readLine());
            for(int j = 0; j < varC; j++){
                prefer[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        int[] sums = new int[varC];
        
        for(int i = 0; i < varC; i++){
            int sum = 0;
            for(int j = 0; j < numP; j++){
                sum += prefer[j][i];
            }
            sums[i] = sum;
        }
        int max = 0;
        for(int x = 0; x < varC; x++){
            for(int y = 0; y < varC; y++){
                if(y == x) continue;
                for(int z = 0; z  < varC; z++){
                    if(z == x || z == y) continue;
                    
                    int sum = 0;
                    
                    for(int i = 0; i < numP; i++){
                        sum += Math.max(prefer[i][x], Math.max(prefer[i][y], prefer[i][z]));
                    }
                    
                    max = Math.max(max, sum);
                }
            }
        }
        System.out.print(max);
    }
}