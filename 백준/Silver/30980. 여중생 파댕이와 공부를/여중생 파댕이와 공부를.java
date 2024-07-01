import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
        init(in);
    
        solve();
    }
    static int row;
    static int col;
    static char[][] matrix;
    static void init(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        row = Integer.parseInt(tokens.nextToken());
        col = Integer.parseInt(tokens.nextToken());
        matrix = new char[row*3][col*8];
        for(int i = 0; i < row*3; i++){
            char[] line = in.readLine().toCharArray();
            matrix[i] = line;
        }
    }

    static void solve(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int y = i*3+1;
                int x = j*8+1;
            
                int a = Character.getNumericValue(matrix[y][x]);
                int b = Character.getNumericValue(matrix[y][x+2]);
                int c;
                if(matrix[y][x+5] != '.'){
                    c = Character.getNumericValue(matrix[y][x+4])*10 + Character.getNumericValue(matrix[y][x+5]);
                } else {
                    c = Character.getNumericValue(matrix[y][x+4]);
                }
                //System.out.println(a + "+" + b + "=" + c);
                
                if(a+b == c){
                    int n = c < 10 ? 5 : 6;
                    int m = j*8;
                    matrix[y][m] = '*';
                    for(; n > 0; n--){
                    	m++;
                        matrix[y-1][m] = '*';
                        matrix[y+1][m] = '*';
                    }
                    m++;
                    matrix[y][m] = '*';
                } else {
                    matrix[y-1][x+2] = '/';
                    matrix[y][x+1] = '/';
                    matrix[y+1][x] = '/';
                }
            }
        }
    
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < row*3; i++){
            for(int j = 0; j < col*8; j++){
                str.append(matrix[i][j]);
            }
            str.append("\n");
        }
        System.out.println(str.toString());
    }
}


