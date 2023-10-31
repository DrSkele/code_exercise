#swe 5215 

def cook(idx, cur_cal): #dynamic programming
    if(idx == len(recipe)): #check for the end of the list
        return 0
    
    added_cal = cur_cal + recipe[idx][1] #calorie including current ingredient
    
    if(added_cal > calorie): #check for excess calorie
        return cook(idx+1, cur_cal)
    
    first = recipe[idx][0] + cook(idx+1, added_cal) #score including current ingredient
    second = cook(idx+1, cur_cal) #score not including current ingredient
    
    return max(first, second) #get higher score

T = int(input())
for test_case in range(1, T + 1):
    
    N, calorie = map(int, input().split())
    recipe = []
    for i in range(N):
        recipe.append(list(map(int, input().split())))
        
    print(f'#{test_case} {cook(0,0)}')

#dfs version
#because of the loops, it's very slow
'''
def get_calories(used):
    sum = 0
    for ingredient in used:
        sum += ingredient[1]
    return sum

def get_scores(used):
    sum = 0
    for ingredient in used:
        sum += ingredient[0]
    return sum

def cook(recipe, used):
    if(get_calories(used) > calorie):
        return get_scores(used[:-1])
    
    score = 0
    
    for other in recipe:
        if(other not in used):
            used.append(other)
            new_score = cook(recipe, used)
            if(new_score > score):
                score = new_score
            used.pop()
            
    return score

T = int(input())
for test_case in range(1, T + 1):
    
    N, calorie = map(int, input().split())
    recipe = []
    scores = []
    
    for i in range(N):
        recipe.append(list(map(int, input().split())))
        
    cur_cal = 0
    used = []
    
    print(f'#{test_case} {cook(recipe, used)}')
'''