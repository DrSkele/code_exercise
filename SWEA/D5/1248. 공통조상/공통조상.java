import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        
		int T = Integer.parseInt(tokens.nextToken());
		
		for(int testCase = 1; testCase <= T; testCase++)
		{
			tokens = new StringTokenizer(in.readLine());
            int vertex = Integer.parseInt(tokens.nextToken());
            int line = Integer.parseInt(tokens.nextToken());
            int first = Integer.parseInt(tokens.nextToken());
            int second = Integer.parseInt(tokens.nextToken());
            
            int[] getParent = new int[vertex+1];
            int[][] getChild = new int[vertex+1][2];
            
            tokens = new StringTokenizer(in.readLine());
            for(int i = 0; i < line; i++){
            	int parent = Integer.parseInt(tokens.nextToken());
                int child = Integer.parseInt(tokens.nextToken());
                
                getParent[child] = parent;
                if(getChild[parent][0] == 0) getChild[parent][0] = child;
                else getChild[parent][1] = child;
            }
            
            HashSet<Integer> set = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            
            q.add(first);
            q.add(second);
            
            int root = 0;
            while(!q.isEmpty()){
            	int cur = q.poll();
                if(cur == 0) continue;
                
                if(set.contains(cur)){
                	root = cur;
                    break;
                }
                
                set.add(cur);
                q.add(getParent[cur]);
            }
            
            int size = 0;
            q.clear();
            
            q.add(root);
            while(!q.isEmpty()){
            	int cur = q.poll();
                size++;
                
                int left = getChild[cur][0];
                int right = getChild[cur][1];
                if(left != 0) q.add(left);
                if(right != 0) q.add(right);
            }
            System.out.println(String.format("#%d %d %d", testCase, root, size));
		}
	}
}