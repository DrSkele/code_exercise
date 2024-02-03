
import java.util.*;
import java.io.*;

class Solution
{
    static StringBuilder str = new StringBuilder();
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            str.setLength(0);
            
            tokens = new StringTokenizer(in.readLine());
			int nodeCnt = Integer.parseInt(tokens.nextToken());
            char[] tree = new char[nodeCnt+1];
            
            for(int i = 0; i < nodeCnt; i++){
            	tokens = new StringTokenizer(in.readLine());
                int idx = Integer.parseInt(tokens.nextToken());
                char value = tokens.nextToken().charAt(0);
                tree[idx] = value;
            }
            
            inorder(tree, 1);
            
            System.out.println(String.format("#%d %s", test_case, str.toString()));
		}
	}
    
    static void inorder(char[] tree, int idx){
    	
        if(idx >= tree.length) return;
        
        int left = 2*idx;
        int right = 2*idx+1;
        
        inorder(tree, left);
        str.append(tree[idx]);
        inorder(tree, right);
    }
}