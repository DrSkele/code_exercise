import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args){
        boolean[] hasSelfNum = new boolean[10001];
        for(int i = 1; i < 10001; i++){
            int tenth = i/10000;
            int thous = i%10000/1000;
            int hund = i%1000/100;
            int ten = i%100/10;
            int one = i%10;
            int nextIdx = i+tenth+thous+hund+ten+one;
            if(nextIdx < 10001) hasSelfNum[nextIdx] = true;
        }
        for(int i = 1; i < 10001; i++){
            if(!hasSelfNum[i]) System.out.println(i);
        }
    }
}