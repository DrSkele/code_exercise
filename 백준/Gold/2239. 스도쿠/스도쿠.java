import java.io.*;
import java.util.*;

class Main{
    static int[][] matrix = new int[9][9];
    static ArrayList<int[]> empty = new ArrayList<>();
    static boolean isComplete = false;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        for(int y = 0; y < 9; y++){
            String line = in.readLine();
            for(int x = 0; x < 9; x++){
                int value = Character.getNumericValue(line.charAt(x));
                matrix[y][x] = value;
                if(value == 0) empty.add(new int[]{x, y});
            }
        }
        dfs(0);
        
        StringBuilder str = new StringBuilder();
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                str.append(matrix[y][x]);
            }
            str.append("\n");
        }
        System.out.print(str.toString());
    }
    
    static void dfs(int idx){
        if(idx == empty.size()){
            isComplete = true;
            return;            
        }
        int[] coord = empty.get(idx);
        int x = coord[0];
        int y = coord[1];
        
        boolean[] hasValue = new boolean[9];
        
        //horizontal
        for(int i = 0; i < 9; i++){
            int value = matrix[y][i];
            if(value == 0) continue;
            hasValue[value-1] = true;
        }
        
        //vertical
        for(int i = 0; i < 9; i++){
            int value = matrix[i][x];
            if(value == 0) continue;
            hasValue[value-1] = true;
        }
        
        //rect
        int rx = x/3;
        int ry = y/3;
        
        for(int i = ry*3; i < (ry+1)*3; i++){
            for(int j = rx*3; j < (rx+1)*3; j++){
                int value = matrix[i][j];
                if(value == 0) continue;
                hasValue[value-1] = true;
            }
        }
        
        for(int i = 0; i < 9; i++){
            if(hasValue[i]) continue;
            matrix[y][x] = i+1;
            dfs(idx+1);
            if(isComplete) break;
            matrix[y][x] = 0;
        }
    }
}