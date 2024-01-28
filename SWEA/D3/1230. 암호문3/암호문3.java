import java.util.*;
import java.io.*;

class Solution
{
    public static Node[] nodePool;
    public static int pointer = 0;
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
		int T = 10;
        
        int maxLength = 5000;
        nodePool = new Node[maxLength];
        pointer = 0;
        
        for(int i = 0; i < maxLength; i++){
        	nodePool[i] = new Node();
        }
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			pointer = 0;
            tokens = new StringTokenizer(in.readLine());
            int length = Integer.parseInt(tokens.nextToken());
            
            tokens = new StringTokenizer(in.readLine());
            LinkList list = new LinkList();
            
            while(tokens.hasMoreTokens()){
            	list.add(Integer.parseInt(tokens.nextToken()));
            }
            tokens = new StringTokenizer(in.readLine());
            int cmdLength = Integer.parseInt(tokens.nextToken());
            
            tokens = new StringTokenizer(in.readLine());
            for(int i = 0; i < cmdLength; i++){
            	char cmd = tokens.nextToken().charAt(0);
                switch(cmd){
                    case 'I' : {
                        int idx = Integer.parseInt(tokens.nextToken());
                        int codeLength = Integer.parseInt(tokens.nextToken());
                       	LinkList tempList = new LinkList();
                        for(int j = 0; j < codeLength; j++){
                        	tempList.add(Integer.parseInt(tokens.nextToken()));
                        }
                        list.concatAt(idx, tempList);
                        break;}
                    case 'A' :{
                        int codeLength = Integer.parseInt(tokens.nextToken());
                       	LinkList tempList = new LinkList();
                        for(int j = 0; j < codeLength; j++){
                        	tempList.add(Integer.parseInt(tokens.nextToken()));
                        }
                        list.concat(tempList);
                        break;}
                    case 'D' :{
                        int idx = Integer.parseInt(tokens.nextToken());
                        int n = Integer.parseInt(tokens.nextToken());
                        list.delete(idx, n);
                        break;}
                }
            }
            StringBuilder str = new StringBuilder();
            Node next = list.head;
            for(int i = 0; i < 10; i++){
                next = next.next;
            	str.append(next.value).append(" ");
            }
            System.out.println(String.format("#%d %s", test_case, str));
		}
	}
    
    static class Node{
        public int value;
        public Node next;

        public Node(){}
        public Node(int value){
            this.value = value;
        }
    }

    static class LinkList{
        public Node head = new Node();
        public Node tail;

        public Node nodeAt(int idx){
            if(idx < 0) return head;
            int cnt = 0;
            Node next = head.next;
            while(next != null && idx != cnt){
                cnt++;
                next = next.next;
            }
            return next;
        }

        public void add(int value){
            Node newNode = nodePool[pointer++];
            newNode.value = value;
            newNode.next = null;
            if(head.next == null){
                head.next = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        public void concat(LinkList list){
            this.tail = list.head.next;
        }

        public void concatAt(int idx, LinkList list){
            Node newHead = nodeAt(idx-1);
            Node newTail = newHead.next;
            newHead.next = list.head.next;
            list.tail.next = newTail;
            if(newTail == null) this.tail = list.tail;
        }

        public void delete(int idx, int n){
            Node front = nodeAt(idx);
            Node end = nodeAt(idx+n);
            front.next = end.next;
        }
    }
}

