import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
        int N = Integer.parseInt(tokens.nextToken());
        tokens = new StringTokenizer(in.readLine());
        int[] array = new int[N];

        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(tokens.nextToken());
        }
        
        int[] temp = array.clone();
        Arrays.sort(temp);
        
        ArrayList<Integer> sorted = new ArrayList<>();
        sorted.add(temp[0]);
        for(int i = 1; i < N; i++){
            if(temp[i] != temp[i-1]) sorted.add(temp[i]);
        }
        
        StringBuilder str = new StringBuilder();
        
        for(int i = 0; i < N; i++){
            int target = array[i];
            
            int bottom = 0;
            int top = sorted.size();
            
            while(bottom < top){
                int mid = (top+bottom)/2;
                if(target <= sorted.get(mid)){
                    top = mid;
                } else {
                    bottom = mid+1;
                }
            }
            str.append(bottom);
            str.append(" ");
        }
        System.out.print(str.toString());
    }
}