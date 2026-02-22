import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int length = Integer.parseInt(in.readLine());
        
        ArrayList<Integer> cards = new ArrayList<>();
        int max = 0;
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < length; i++) {
            int card = Integer.parseInt(tokens.nextToken());
            if(card > max) max = card;
            cards.add(card);
        }
        
        // player number (1~N)
        int[] owner = new int[max+1];
        for(int i = 0; i < length; i++) {
            owner[cards.get(i)] = i+1;
        }
        // score (1~N)
        int[] score = new int[length+1];
        
        // cards (0~N-1)
        for(int i = 0; i < length; i++) {
            int card = cards.get(i);
            int player = owner[card];
            
            int mult = 2;
            while(card * mult <= max) {
                int nextCard = card * mult;
                int oppo = owner[nextCard];
                
                if(oppo != 0) {
                  score[player]++;
                  score[oppo]--;
                }
                
                mult++;
            }
        }
        
        StringBuilder str = new StringBuilder();
        for(int i = 1; i < length+1; i++) {
            str.append(score[i]).append(' ');
        }
        System.out.println(str.toString());
    }
}