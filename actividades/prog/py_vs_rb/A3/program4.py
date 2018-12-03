import sys

num = int(sys.argv[1])

if num <= 0:
    exit('¡ERROR! Debes introducir un número entero positivo')
else:
    for i in range(1, num + 1):
        factor = 1
        for j in range(1, i + 1):
            factor = factor * j
        print(i, "! =", factor)
