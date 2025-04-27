import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input(in);
        solve(in);
    }
    
    static int size;
    static boolean[][] matrix;
    static void input(BufferedReader in) throws IOException {
        size = Integer.parseInt(in.readLine());
        matrix = new boolean[size][size*2-1];
    }
    
    static void solve(BufferedReader in) throws IOException {
    	printStar(0,0,size);
    	StringBuilder str = new StringBuilder();
    	
    	for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size*2-1; j++) {
    			str.append(matrix[i][j] ? '*' : ' ');
    		}
    		str.append("\n");
    	}
    	
    	System.out.println(str.toString());
    }
    
    static void printStar(int offsetX, int offsetY, int size) {
    	if(size == 3) {
    		matrix[offsetY][offsetX+2] = true;
    		matrix[offsetY+1][offsetX+1] = true;
    		matrix[offsetY+1][offsetX+3] = true;
    		for(int i = offsetX; i <= offsetX+4; i++) {
    			matrix[offsetY+2][i] = true;
    		}
    	} else {
    		printStar(offsetX + size/2, offsetY, size/2);
    		printStar(offsetX, offsetY + size/2, size/2);
    		printStar(offsetX + size, offsetY + size/2, size/2);
    	}
    }
}