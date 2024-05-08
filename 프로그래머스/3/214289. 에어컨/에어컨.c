#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int dp[51][1001];

const int correction_value = 10;
int direction = 0;

// onboard_len은 배열 onboard의 길이입니다.
int solution(int temperature, int t1, int t2, int a, int b, int onboard[], size_t onboard_len) {
    int answer = 100000000;
    
    // step01. dp 테이블 초기화
    for(int i = 0; i <= 50; i++)
        for(int j = 0; j <= 1000; j++)
            dp[i][j] = 100000000;
    
    // step02. 초기값 설정
    dp[temperature + correction_value][0] = 0;
    
    // step03. 방향 설정 (-1: 온도 낮춰야함. 1 : 온도 높여야함.)
    if(t2 <= temperature)
        direction = -1;
    else
        direction = 1;

    // step04. dp 테이블 확장
    for(int i = 0; i < onboard_len - 1; i++){
        int start, end;
        if(onboard[i] == 1){
            start = t1 + correction_value;
            end = t2 + correction_value;
        }else{
            start = 0;
            end = 50;
        }
        
        for(int j = start; j <= end; j++){
            if(j - 1 >= 0 && dp[j - 1][i + 1] > dp[j][i] + a)
                dp[j - 1][i + 1] = dp[j][i] + a;

            if(j + 1 <= 50 && dp[j + 1][i + 1] > dp[j][i] + a)
                dp[j + 1][i + 1] = dp[j][i] + a;

            if(dp[j][i + 1] > dp[j][i] + b)
                dp[j][i + 1] = dp[j][i] + b;
            
            
            if(direction == -1){
                if(j + 1 <= 50 && dp[j + 1][i + 1] > dp[j][i])
                    dp[j + 1][i + 1] = dp[j][i];
            }else{
                if(j - 1 >= 0 && dp[j - 1][i + 1] > dp[j][i])
                    dp[j - 1][i + 1] = dp[j][i];
            }
            
            if(temperature + correction_value == j)
                if(dp[j][i + 1] > dp[j][i])
                    dp[j][i + 1] = dp[j][i];
        }
    }
    
    // step05. 정답 추출 
    if(onboard[onboard_len - 1] == 1){
        for(int j = t1 + correction_value; j <= t2 + correction_value; j++){
            if(dp[j][onboard_len - 1] < answer)
                answer = dp[j][onboard_len - 1];
        }
    }else{
        for(int j = 0; j <= 50; j++){
            if(dp[j][onboard_len - 1] < answer)
                answer = dp[j][onboard_len - 1];
        }
    }
    
    return answer;
}