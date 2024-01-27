import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int visited = 0;
			int N = Integer.parseInt(in.readLine());
            boolean allCount = false;
            int x = 0;
            int cnt = 0;
            while(!allCount){
                x++;
                cnt += N;
                String line = Integer.toString(x * N);
            	
                for(int j = 0; j < line.length(); j++){
                	int cur = Character.getNumericValue(line.charAt(j));
                    if((visited & (1 << cur)) == (1 << cur)){
                    	continue;
                    } else {
                    	visited = visited | (1 << cur);
                        
                        if((visited & ((1 << 10) - 1)) == ((1 << 10) - 1)){
                            allCount = true;
                            break;
                        }
                    }
                }
            }
            System.out.println(String.format("#%d %d", test_case, cnt));
		}
	}
}