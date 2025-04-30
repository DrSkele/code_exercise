import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        input(in);
        solve();      
    }
    
    static int lengthA;
    static int[] arrA;
    static int lengthB;
    static int[] arrB;
    
    static void input(BufferedReader in) throws IOException {
        lengthA = Integer.parseInt(in.readLine());
        arrA = new int[lengthA];
        StringTokenizer tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < lengthA; i++) {
            arrA[i] = Integer.parseInt(tokens.nextToken());
        }
        lengthB = Integer.parseInt(in.readLine());
        arrB = new int[lengthB];
        tokens = new StringTokenizer(in.readLine());
        for(int i = 0; i < lengthB; i++) {
            arrB[i] = Integer.parseInt(tokens.nextToken());
        }
    }
    
    static void solve() {
        List<Integer> result = findLexicographicallyGreatestCommonSubsequence();
        
        StringBuilder str = new StringBuilder();
        str.append(result.size()).append("\n");
        if(result.size() > 0) {
            for(int num : result) {
                str.append(num).append(' ');
            }    
        }
        System.out.println(str.toString());
    }
    
    static List<Integer> findLexicographicallyGreatestCommonSubsequence() {
        // 메모이제이션을 위한 맵 - 각 상태에 대한 최적의 부분 수열 저장
        Map<String, List<Integer>> memo = new HashMap<>();
        
        // 재귀 함수 호출로 결과 얻기
        return findSubsequence(0, 0, memo);
    }
    
    // 현재 위치에서 시작하는 사전 순으로 가장 나중인 공통 부분 수열 찾기
    static List<Integer> findSubsequence(int idxA, int idxB, Map<String, List<Integer>> memo) {
        // 배열 범위를 벗어났으면 빈 리스트 반환
        if (idxA >= lengthA || idxB >= lengthB) {
            return new ArrayList<>();
        }
        
        // 이미 계산된 결과가 있으면 바로 반환
        String key = idxA + "," + idxB;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // 현재 위치의 원소가 같은 경우
        if (arrA[idxA] == arrB[idxB]) {
            // 현재 원소를 포함하는 경우
            List<Integer> includeThis = new ArrayList<>();
            includeThis.add(arrA[idxA]);
            List<Integer> nextResult = findSubsequence(idxA + 1, idxB + 1, memo);
            includeThis.addAll(nextResult);
            
            // 현재 원소를 포함하지 않는 경우의 두 가지
            List<Integer> skipA = findSubsequence(idxA + 1, idxB, memo);
            List<Integer> skipB = findSubsequence(idxA, idxB + 1, memo);
            
            // 세 가지 경우 중 사전 순으로 가장 나중인 것 선택
            List<Integer> result = includeThis;
            if (compare(skipA, result) > 0) {
                result = skipA;
            }
            if (compare(skipB, result) > 0) {
                result = skipB;
            }
            
            // 결과 저장 및 반환
            memo.put(key, result);
            return result;
        } else {
            // 현재 원소가 다른 경우, 둘 중 사전 순으로 더 나중인 것 선택
            List<Integer> skipA = findSubsequence(idxA + 1, idxB, memo);
            List<Integer> skipB = findSubsequence(idxA, idxB + 1, memo);
            
            List<Integer> result = skipA;
            if (compare(skipB, result) > 0) {
                result = skipB;
            }
            
            // 결과 저장 및 반환
            memo.put(key, result);
            return result;
        }
    }
    
    // 두 수열의 사전 순 비교 (양수면 a가 더 나중)
    static int compare(List<Integer> a, List<Integer> b) {
        int minLen = Math.min(a.size(), b.size());
        
        for (int i = 0; i < minLen; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        }
        
        return a.size() - b.size();
    }
}