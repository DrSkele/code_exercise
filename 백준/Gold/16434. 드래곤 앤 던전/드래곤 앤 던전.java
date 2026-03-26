import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int length = Integer.parseInt(tokens.nextToken());
        long atk = Long.parseLong(tokens.nextToken());
        long health = 0;
        long totalDmg = 0;
        
        for(int i = 0; i < length; i++) {
            tokens = new StringTokenizer(in.readLine());
            
            String type = tokens.nextToken();
            
            if(type.equals("1")) {
                // monster
                int monsterAtk = Integer.parseInt(tokens.nextToken());
                int monsterHp = Integer.parseInt(tokens.nextToken());
                
                long turns = monsterHp / atk;
                if(monsterHp % atk == 0) turns--;
                
                long dmgTaken = monsterAtk * turns;
                
                totalDmg += dmgTaken;
                
                health = Math.max(health, totalDmg);
            } else {
                // potion
                int buff = Integer.parseInt(tokens.nextToken());
                int heal = Integer.parseInt(tokens.nextToken());
                
                atk += buff;
                totalDmg = Math.max(0, totalDmg - heal);
            }
        }
        
        System.out.println(health+1);
    }
}