#swe 1209

T = 10
 
for test_case in range(1, T + 1):
     
    num = input()
    matrix = [list(map(int, list(input().split()))) for _ in range(100)]
     
    vertical = [0 for _ in range(100)]
    horizontal = [0 for _ in range(100)]
    diagonal = 0
    r_diagonal = 0
     
    for i in range(100):
        for j in range(100):
            vertical[i] += matrix[i][j] #vertical sum
            horizontal[j] += matrix[i][j] #horizontal sum
            if(i == j):
                diagonal += matrix[i][j] #diagonal
            if(99 - i == j):
                r_diagonal += matrix[i][j] #reverse_diagonal
     
     
    max_vert = max(vertical)
    max_hori = max(horizontal)
     
    max_num = max([max_vert, max_hori, diagonal, r_diagonal])
 
    print(f'#{num} {max_num}')