#swe 1208

import sys
sys.stdin = open("input.txt", "r")

def dump(boxlist):
    
    boxlist[0] += 1 #add
    boxlist[-1] -= 1 #reduce
    
    for i in range(1,len(boxlist)): #with smaller number
        if(boxlist[i - 1] <= boxlist[i]): #bubble sort
            break
        else:
            boxlist[i], boxlist[i-1] = boxlist[i-1], boxlist[i]
    
    for i in range(len(boxlist) - 1, 0, -1): #with larger number
        if(boxlist[i - 1] <= boxlist[i]):
            break
        else:
            boxlist[i], boxlist[i-1] = boxlist[i-1], boxlist[i]

    
T = 10
for test_case in range(1, T + 1):
    dumps = int(input())
    boxes = list(map(int, input().split()))
    boxes.sort() #sort to ascending
    
    for i in range(dumps):
        dump(boxes)
        
    box_min, box_max = boxes[0], boxes[len(boxes) -1]
    diff = box_max - box_min
    
    print(f'#{test_case} {diff}')
