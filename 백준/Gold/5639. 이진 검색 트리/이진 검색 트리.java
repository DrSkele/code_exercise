import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		input(in);
		solve();	
	}
	
	static Node root;
	static void input(BufferedReader in) throws IOException {
		
		int value = Integer.parseInt(in.readLine());
		root = new Node(value);
		
		while(true) {
			String line = in.readLine();
			if(line == null || line.equals("")) break;
			
			int newValue = Integer.parseInt(line);
			root.insert(newValue);
		}
	}
	
	static void solve(){
		StringBuilder str = new StringBuilder();
		traverse(root, str);
		System.out.println(str.toString());
	}

	static void traverse(Node node, StringBuilder str) {
		if(node.left != null) {
			traverse(node.left, str);
		}
		if(node.right != null) {
			traverse(node.right, str);
		}
		str.append(node.value).append("\n");
	}
	
	static class Node {
		int value;
		Node left = null;
		Node right = null;
		
		public Node(int value) {
			this.value = value;
		}
		
		public void insert(int newValue) {
			if(newValue <= value) {
				if(left == null) left = new Node(newValue);
				else left.insert(newValue);
			} else {
				if(right == null) right = new Node(newValue);
				else right.insert(newValue);
			}
		}
	}
}