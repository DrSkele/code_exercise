#swe 1240

T = int(input())
 
code = [ '00011010', '00110010', '00100110', '01111010', '01000110', '01100010', '01011110', '01110110', '01101110', '00010110' ]
 
for test_case in range(1, T + 1):
     
    vert, hori = map(int, input().split())
    matrix = []
    for i in range(vert):
        matrix.append(input())
     
    result = 0
     
    for i in range(vert):
        if('1' not in matrix[i]):
            continue
         
        msg = []
         
        idx = 0
         
        while(idx < hori - 7):
            value = -1
            for j in range(len(code)):
                if(matrix[i][idx: idx + 8] == code[j]): #check for valid code
                    value = j
                    break
            if(value >= 0):
                msg.append(value)
                idx += 7
            else:
                idx += 1
                 
        if(len(msg) > 0):
            total = 0
            for i in range(len(msg)):
                if(i % 2 == 0):
                    total += msg[i] * 3
                else:
                    total += msg[i]
             
            if(total % 10 == 0):
                result = sum(msg)
                
            break
             
    print(f'#{test_case} {result}')