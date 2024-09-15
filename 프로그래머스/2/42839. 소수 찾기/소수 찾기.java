import java.util.*;

class Solution {
    public int solution(String numbers) {
        char[] arr = new char[numbers.length()];
        for(int i = 0; i < numbers.length(); i++){
            arr[i] = numbers.charAt(i);
        }
        Arrays.sort(arr);
        
        StringBuilder str = new StringBuilder();
        for(int i = arr.length-1; i >= 0; i--){
            str.append(arr[i]);
        }
        int max = Integer.parseInt(str.toString());
        
        boolean[] nums = new boolean[max+1];
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i = 2; i <= max; i++){
            if(nums[i] == true) continue;
            primes.add(i);
            
            int val = i;
            while(val <= max){
                val = val + i;
                if(val > max) break;
                nums[val] = true;
            }
        }
        int[] pool = new int[10];
        for(int i = 0; i < arr.length; i++){
            pool[Character.getNumericValue(arr[i])]++;
        }
        int cnt = 0;
        for(int prime : primes){
            int[] comp = new int[10];
            int temp = prime;
            while(temp > 0){
                int ones = temp % 10;
                comp[ones]++;
                temp /= 10;
            }
            boolean possible = true;
            for(int i = 0; i < pool.length; i++){
                if(pool[i] < comp[i]) {
                    possible = false;
                    break;
                }
            }
            if(possible) cnt++;
        }
        
        return cnt;
    }
}