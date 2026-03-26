import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();
    }
    
    static int length;
    static int initialAtk;
    static int [][] dungeon;
    static void input(BufferedReader in) throws IOException {
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        length = Integer.parseInt(tokens.nextToken());
        initialAtk = Integer.parseInt(tokens.nextToken());
        
        dungeon = new int[length][3];
        
        for(int i = 0; i < length; i++) {
            tokens = new StringTokenizer(in.readLine());
            
            for(int j = 0; j < 3; j++) {
                dungeon[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }
    
    static void solve() {
        long left = 1;
        long right = Long.MAX_VALUE;
        long answer = 0;
        
        while(left <= right) {
            long mid = left + (right - left)/2;
            
            if(tryDungeon(initialAtk, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        System.out.println(answer);
    }
    
    static Boolean tryDungeon(long atk, long maxHp) {
        long currentHp = maxHp;
        
        for(int i = 0; i < length; i++) {
            int type = dungeon[i][0];
            
            if(type == 1) {
                // monster
                int monsterAtk = dungeon[i][1];
                int monsterHp = dungeon[i][2];
                
                long turns = monsterHp / atk;
                if(monsterHp % atk == 0) turns--;
                
                long dmgTaken = monsterAtk * turns;
                
                currentHp -= dmgTaken;
                // dead
                if(currentHp <= 0) {
                    return false;
                }
            } else {
                // potion
                int buff = dungeon[i][1];
                int heal = dungeon[i][2];
                
                atk += buff;
                currentHp += heal;
                if(currentHp > maxHp) currentHp = maxHp;
            }
        }
        
        return true;
    }
}