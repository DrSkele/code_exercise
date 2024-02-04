
import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(in.readLine());
		StringTokenizer tokens;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			tokens = new StringTokenizer(in.readLine());
			int vertex = Integer.parseInt(tokens.nextToken());
			int line = Integer.parseInt(tokens.nextToken());
			int target1 = Integer.parseInt(tokens.nextToken());
			int target2 = Integer.parseInt(tokens.nextToken());
			
			int[] parent = new int[vertex+1];
			int[][] child = new int[vertex+1][2];
			
			tokens = new StringTokenizer(in.readLine());
			
			for(int i = 0; i < line; i++) {
				int v1 = Integer.parseInt(tokens.nextToken());
				int v2 = Integer.parseInt(tokens.nextToken());
				
				parent[v2] = v1;
				
				if(child[v1][0] == 0) child[v1][0] = v2;
				else child[v1][1] = v2;
			}
			
			int[] subTree = new int[vertex+1];
			
			dfs(1, child, subTree);
			
			int parent1 = target1;
			int parent2 = target2;
			
			boolean[] visited = new boolean[vertex+1];
			visited[parent1] = true;
			visited[parent2] = true;
			
			int sharedParent = 1;
			
			while(parent1 != 1 || parent2 != 1) {
				if(parent1 > 1) {
					parent1 = parent[parent1];
					if(visited[parent1]) {
						sharedParent = parent1;
						break;
					}
					visited[parent1] = true;					
				}
				if(parent2 > 1) {
					parent2 = parent[parent2];
					if(visited[parent2]) {
						sharedParent = parent2;
						break;
					}
					visited[parent2] = true;					
				}
			}
			
			System.out.println(String.format("#%d %d %d", test_case, sharedParent, subTree[sharedParent]));
		}
	}
	
	static void dfs(int node, int[][] child, int[] subTree) {
		subTree[node] = 1;
		
		for(int i = 0; i < 2; i++) {
			if(child[node][i] == 0) return;
			else {
				int childNode = child[node][i];
				dfs(childNode, child, subTree);
				
				subTree[node] += subTree[childNode];
			}
		}
	}
}