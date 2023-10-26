#swe 1215
def check_horizontal(matrix, length): #horizontal check
    cnt = 0
    for i in range(size):
        for j in range(size):
            if(j + length > size):
                break
             
            has_pal = True
            half_length = length//2 if(length %2 == 0)  else length//2 + 1
             
            for k in range(half_length):
                if(matrix[i][j+k] != matrix[i][j+(length - k - 1)]):
                    has_pal = False
                    break
            if(has_pal):
                cnt += 1
    return cnt
             
def check_vertical(matrix, length): #vertical check
    cnt = 0
    for i in range(size):
        for j in range(size):
            if(j + length > size):
                break
             
            has_pal = True
            half_length = length//2 if(length %2 == 0)  else length//2 + 1
            for k in range(half_length):
                if(matrix[j+k][i] != matrix[j+(length - k - 1)][i]):
                    has_pal = False
                    break
            if(has_pal):
                cnt += 1
    return cnt
 
T = 10
 
for test_case in range(1, T + 1):
     
    length = int(input())
    matrix = []
    size = 8
    for i in range(size):
        matrix.append(input())
         
    horizontal = check_horizontal(matrix, length)
    vertical = check_vertical(matrix, length)
     
    print(f'#{test_case} {horizontal + vertical}')