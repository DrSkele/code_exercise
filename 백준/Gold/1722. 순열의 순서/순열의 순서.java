import java.io.*;
import java.util.*;

class Main{
    static int N;
    static long[] fact = new long[21];
    static boolean[] visited;
    
    static long k;
    static int[] array;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        N = Integer.parseInt(tokens.nextToken());
        
        tokens = new StringTokenizer(in.readLine());
        int p = Integer.parseInt(tokens.nextToken());
        
        fact[0] = 1;
        fact[1] = 1;
        for(int i = 2; i < 21; i++){
            fact[i] = i*fact[i-1];
        }
        array = new int[N];
        visited = new boolean[N];
        
        if(p == 1){
            k = Long.parseLong(tokens.nextToken())-1; //0부터 시작하도록
            for(int i = 0; i < N; i++){
                long curFact = fact[N-i-1];
                long div = k/curFact; //팩토리얼로 나눌 수 있는 값
                long cnt = 0;
                int curNum = 1;
                for(int j = 0; j < N; j++){
                    if(visited[j]) continue; 
                    if(cnt == div){ 
                        curNum = j;
                        break;
                    }
                    cnt++;
                }
                array[i] = curNum+1; //0부터 시작이므로 +1
                k -= div*curFact;
                visited[curNum] = true; //방문 표시
            }
            
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < N; i++){
                str.append(array[i]);
                str.append(" ");
            }
            System.out.print(str.toString());
            
        } else {
            for(int i = 0; i < N; i++){
                array[i] = Integer.parseInt(tokens.nextToken());
            }
            
            long idx = 0;
            for(int i = 0; i < N; i++){
                int curNum = array[i];
                visited[curNum-1] = true; //index 0부터 이므로
                int cnt = 0;
                for(int j = 0; j < curNum-1; j++){ //현재 숫자가 몇번째로 사용가능한 숫자인지 체크
                    if(!visited[j]) cnt++;
                }
                idx += cnt*fact[N-i-1];
            }
            System.out.print(idx+1);
        }
        
    }
    
}