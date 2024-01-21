import java.util.*;
import java.io.*;



class Solution
{
    static int A = 1;
    static int B = 2;
    static int C = 4;
    static int D = 8;
    static int div = 1_000_000_007;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			char[] str = sc.next().toCharArray();
            long[][] cases = new long [str.length][16];
            
            for(int i = 1; i < 16; i++){
            	if((A & i) > 0 && (i & (1 << str[0]-'A')) > 0) cases[0][i] = 1;   
            }
            
            for(int i = 1; i < str.length; i++) {
            	for(int j = 1; j < 16; j++) { //cur
                	if((j & (1 << str[i] - 'A')) == 0) continue;
                    for(int k = 1; k < 16; k++){ //prev
                        if(cases[i-1][k] == 0) continue;
                    	if((k & j) > 0){
                        	cases[i][j] = (cases[i][j] + cases[i-1][k]) % div;
                        }
                    }
                }
            }
            long sum = 0;
            for(int i = 1; i < 16; i++) {
            	sum += cases[str.length-1][i];
            }
            
            System.out.println(String.format("#%d %d", test_case, sum % div));
		}
	}
}