import sys

numa = int(sys.argv[1])
numb = int(sys.argv[2])

if numa < numb:
    menor = numa
else:
    menor = numb

if numa <= 0 or numb <= 0:
    exit('¡ERROR! Debes introducir un número entero positivo')
else:
    for i in range (menor, 0, -1):
        if numa % i == 0 and numb % i == 0:
            break
    print('El máximo común divisor de los números', numa,'y', numb,'es', i)
