import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int N = Integer.parseInt(tokens.nextToken());
        int money = Integer.parseInt(tokens.nextToken());
        int[] coins = new int[N];
        
        for(int i = 0; i < N; i++){
            tokens = new StringTokenizer(in.readLine());
            coins[i] = Integer.parseInt(tokens.nextToken());
        }
        
        int cnt = 0;
        for(int i = N-1; i >= 0; i--){
            int coin = coins[i];
            int div = money/coin;
            if(div > 0){
                money -= div*coin;
                cnt += div;
            }
        }
        System.out.print(cnt);
    }
}