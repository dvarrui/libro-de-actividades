import sys

num = int(sys.argv[1])
texto = sys.argv[2]

if num <= 0:
    print('¡ERROR! El número introducido no es correcto')
else:
    palabras = texto.split(' ')
    size = len(palabras)
    contador = 0
    for i in range(0, size):
        if len(palabras[i]) == num:
            contador += 1
    print(f'Hay {contador} palabras de tamaño {num}')
