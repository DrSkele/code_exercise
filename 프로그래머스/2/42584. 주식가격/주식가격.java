import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Stock> stack = new Stack<>();
        stack.add(new Stock(prices[0], 0));
        
        for(int i = 1; i < prices.length; i++){
            while(!stack.isEmpty() && stack.peek().price > prices[i]) {
                Stock cur = stack.pop();
                answer[cur.regTime] = i - cur.regTime;
            }
            stack.add(new Stock(prices[i], i));
        }
        
        while(!stack.isEmpty()) {
            Stock cur = stack.pop();
            answer[cur.regTime] = prices.length - 1 - cur.regTime;
        }
        
        return answer;
    }
    
    class Stock{
        int price;
        int regTime;
        public Stock(int p, int r){
            price = p;
            regTime = r;
        }
    }
}