import java.io.*;
import java.util.*;

class Main{
    static int[] weight = new int[26]; //A is 0
    static HashMap<Character, Integer> map = new HashMap<>();
    static String[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        array = new String[N];
        
        for(int i = 0; i < N; i++){
            String str = in.readLine();
            array[i] = str;
            
            char[] cha = str.toCharArray();
            for(int j = 0; j < cha.length; j++){
                char cur = cha[j];
                int idx = cur - 'A';
                weight[idx] += Math.pow(10, cha.length-1-j);
            }
        }
        
        int max;
        int cnt = 9;
        do{
            max = 0;
            int idx = -1;
            for(int i = 0; i < 26; i++){
                if(weight[i] > max) {
                    max = weight[i];
                    idx = i;
                }
            }
            if(max > 0){
                map.put((char)('A'+idx), cnt);
                weight[idx] = 0;
                cnt--;
            }
        } while(max > 0);
        
        long sum = 0;
        for(int i = 0; i < N; i++){
            char[] cha = array[i].toCharArray();
            for(int j = 0; j < cha.length; j++){
                sum += map.get(cha[j]) * Math.pow(10, cha.length-1-j);
            }
        }
        System.out.print(sum);
    }
}