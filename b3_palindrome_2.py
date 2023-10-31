#swe 1216

def dp_y(matrix, y, size): #horizontal
    length = 100 - size + 1
    for i in range(length):
        string = matrix[y][i: i+size]
        if(string == string[::-1]):
            return len(string)
     
    return dp_y(matrix, y, size -1)
     
def dp_x(matrix, x, size): #vertical
    length = 100 - size + 1
    for i in range(length):
        string = ''
        for j in range(i, i + size):
            string += matrix[j][x]
        if(string == string[::-1]):
            return len(string)
     
    return dp_x(matrix, x, size -1) #if palindrome of the given size is not found, look for the smaller one
     
T = 10
 
for test_case in range(1, T + 1):
    n = input()
    matrix = []
    for i in range(100):
        matrix.append(input())
         
    result = 0
    for i in range(100):
        value = max(dp_y(matrix, i, 100), dp_x(matrix, i, 100)) #choose the bigger value
        if(value > result):
            result = value
         
    print(f'#{test_case} {result}')