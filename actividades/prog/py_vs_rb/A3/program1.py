import sys

num = int(sys.argv[1])
if num <= 0:
    exit('¡ERROR! Debes introducir un número entero positivo')
else:
    for i in range(2, num):
        if num % i == 0:
            print("No es un número primo")
            break
    else:
        print('Es un número primo')
