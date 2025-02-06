import java.util.*;

class Solution {
    public ArrayList<String> solution(String[] expressions) {
        
        Set<Integer> candidate = new HashSet<>();
        for(int i = 2; i <= 9; i++){
            candidate.add(i);
        }
        
        ArrayList<String> unknown = new ArrayList<>();
        
        for(int i = 0; i < expressions.length; i++) {
            String[] expression = expressions[i].split(" ");
            
            int num1 = Integer.parseInt(expression[0]);
            String op = expression[1];
            int num2 = Integer.parseInt(expression[2]);
            String result = expression[4];
            
            int min = Math.max(num1%10, num2%10);
            for(int m = 2; m <= min; m++) {
                candidate.remove(m);
            }
            
            if(result.equals("X")) unknown.add(expressions[i]);
            else {
                ArrayList<Integer> list = new ArrayList<>(candidate);
                
                int res = Integer.parseInt(result);
                
                for(int div : list) {
                    int temp = calculation(div, num1, num2, op);
                    
                    //System.out.println(div + ":" + num1 + " " + op + " " + num2 + "=" + temp);
                    
                    if(temp != res) candidate.remove(div);
                }
            }
        }
        
        ArrayList<String> answer = new ArrayList<>();
        
        for(String string : unknown) {
            String[] expression = string.split(" ");
            
            int num1 = Integer.parseInt(expression[0]);
            String op = expression[1];
            int num2 = Integer.parseInt(expression[2]);
            
            int res = -1;
            
            for(int div : candidate) {
                
                int temp = calculation(div, num1, num2, op);
                
                //System.out.println(div + " " + temp);
                
                if(res >= 0 && temp != res) {
                    res = -1;
                    break;
                } else {
                    res = temp;
                }
            }
            
            answer.add(num1 + " " + op + " " + num2 + " = " + (res >= 0 ? res : "?"));
        }
        
        return answer;
    }
    
    static int calculation(int div, int num1, int num2, String op) {
        int hund1 = num1/100;
        int tenth1 = num1/10;
        int ones1 = num1%10;
        
        int hund2 = num2/100;
        int tenth2 = num2/10;
        int ones2 = num2%10;
        
        int cal1 = hund1 * (div * div) + tenth1 * div + ones1;
        int cal2 = hund2 * (div * div) + tenth2 * div + ones2;
        int result = op.equals("+") ? cal1 + cal2 : cal1 - cal2;
        
        //System.out.println(cal1 + " " + cal2 + " " + result);
        
        int hund = result / (div*div);
        int tenth = (result % (div*div)) / div;
        int ones = result % div;
        
        return hund * 100 + tenth * 10 + ones;
    }
}