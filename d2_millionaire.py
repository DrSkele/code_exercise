T = int(input())

for test_case in range(1, T + 1):
    
    cnts = int(input())
    products = list(map(int, input().split()))
    profit = 0
    
    while(len(products) > 0):
        highest_price = max(products)
        index = products.index(highest_price)
        
        prices = 0
        cnt = 0
        for price in products[0 : index] :
            cnt += 1
            prices -= price
        prices += cnt * highest_price
        if(prices > 0):
            profit += prices
        
        print(products)
        products = products[index + 1 :]
    
    print(profit)
            
        
