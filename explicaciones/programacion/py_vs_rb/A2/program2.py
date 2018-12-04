import sys
import math

x1 = int(sys.argv[1])
x2 = int(sys.argv[3])
x3 = int(sys.argv[5])
y1 = int(sys.argv[2])
y2 = int(sys.argv[4])
y3 = int(sys.argv[6])

d1 = math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)
d2 = math.sqrt((x1 - x3) ** 2 + (y1 - y3) ** 2)

if d2 > d1:
    print('El punto más cercano a','(',x1,',',y1,
    ') es (',x2,',',y2,') y está a una distancia',d1)
elif d2 < d1:
    print('El punto más cercano a','(',x1,',',y1,
    ') es (',x3,',',y3,') y está a una distancia',d2)
