#swe 5607

#power operation using divide and conquer
#C^n = if n is even, C^(n/2) * C^(n/2)
#      if n is odd, C^(n/2) * C^(n/2) * C
def power(n, r, mod): #power operation with modular     (n * r) % mod = ((n % mod) * (r % mod)) % mod
    result = 1
    while(r > 0):
        if(r % 2 == 1):
            result *= n
            result %= mod
        n *= n
        n %= mod
        r //= 2
    return result
 
def fac(n, mod):
    result = 1
    for i in range(n, 1, -1):
        result *= i
        result %= mod
    return result
 
mod = 1234567891
T = int(input())
for test_case in range(1, T + 1):
     
    N, R = map(int, input().split())
     
    n_pac = fac(N, mod) # n!
    r_pac = fac(R, mod) # r!
    nr_pac = fac(N-R, mod) # (n-r)!

    #nCr = n!/(r! * (n-r)!)
    
    #used fermat's little theorem
    # a^p = a mod p -> 
    # a^(p-1) = 1 mod p -> 
    # a^(p-2) = a^-1 mod p 
    # (a : int, p : prime num, a & p : coprime)

    # (n!/r!(n-r)!) % mod ->
    # (n! * (r!(n-r)!)^-1) % mod
    # (r!(n-r)!)^-1 % mod = (r!*(n-r)!)^(mod - 2)
    # ( (n! % mod) * (r! * (n-r)!)^(mod - 2) ) % mod
    answer = (n_pac % mod) * power((r_pac * nr_pac), mod -2, mod) 
    answer %= mod
     
    print(f'#{test_case} {answer}')