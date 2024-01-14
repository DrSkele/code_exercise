import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        int numOfHouse = Integer.parseInt(tokens.nextToken());
        int lan = Integer.parseInt(tokens.nextToken());
        int[] houses = new int[numOfHouse];
        for(int i =0;i< numOfHouse; i++){
            tokens = new StringTokenizer(in.readLine());
            houses[i] = Integer.parseInt(tokens.nextToken());
        }
        Arrays.sort(houses);
        int dist = (houses[numOfHouse-1] - houses[0]);
        
        
        int top = dist;
        int bottom = 0;
        
        while(bottom <= top){
            int mid = (top+bottom)/2;
            int lastPlaced = houses[0];
            int cnt = 1;
            for(int j = 1; j<numOfHouse; j++){
                int between = houses[j]-lastPlaced;
                if(between >= mid){
                    cnt++;
                    lastPlaced = houses[j];
                }
            }
            if(cnt >= lan) bottom = mid+1;
            else top = mid-1;
        }
        System.out.println(top);
    }
}