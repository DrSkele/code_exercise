import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int height = Integer.parseInt(tokens.nextToken());
        int width = Integer.parseInt(tokens.nextToken());
        char[][] board = new char[height][width];
        for(int i = 0; i< height; i++){
            char[] line = in.readLine().toCharArray();
            board[i] = line;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < height-7; i++){
            for(int j = 0; j < width-7; j++){
                int cntB = 0;
                int cntW = 0;
                for(int y = 0; y < 8; y++){
                    for(int x = 0; x < 8; x++){
                        int nx = j+x;
                        int ny = i+y;
                        //System.out.print(board[ny][nx] == 'B' ? 'B' : 'W');
                        if((ny+nx)%2 == 0){
                            if(board[ny][nx] == 'B'){
                                cntW+=1;
                            } else {
                                cntB+=1;
                            }
                        } else {
                            if(board[ny][nx] == 'W'){
                                cntW+=1;
                            } else {
                                cntB+=1;
                            }
                        }
                    }
                    //System.out.println();
                }
                //System.out.println(cntB + " " + cntW);
                int cntMin = cntB < cntW ? cntB : cntW;
                min = cntMin < min ? cntMin : min;
            }
        }
        System.out.print(min);
    }
}