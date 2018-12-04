import sys

num = int(sys.argv[1])
sum = 0

if num <= 0:
    exit('¡ERROR! Debes introducir un número entero positivo')
else:
    for i in range(1, num + 1):
        sum = sum + (i ** 2)
print("La suma de los cuadrados de los",num,'primeros números naturales es', sum)
