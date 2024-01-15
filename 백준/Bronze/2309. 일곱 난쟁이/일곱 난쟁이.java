import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] dwarves = new int[9];
        
        for(int i =0; i<9; i++){
            dwarves[i] = sc.nextInt();
        }
        Arrays.sort(dwarves);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(i == j) continue;
                int sum = 0;
                for(int k = 0; k < 9; k++){
                    if(k == i || k == j) continue;
                    sum += dwarves[k];
                }
                if(sum == 100){
                    for(int k = 0; k < 9; k++){
                        if(k == i || k == j) continue;
                        System.out.println(dwarves[k]);
                    }
                    return;
                }
            }
        }
    }
}