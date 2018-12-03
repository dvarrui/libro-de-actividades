import sys
import math
from math import pi

radio = float(sys.argv[1])

print('Operaciones disponibles')
print('(1) - Calcular el diámetro de la circunferencia')
print('(2) - Calcular el perímetro de la circunferencia')
print('(3) - Calcular el área del círculo')
print('(4) - Salir')

opcion = int(input('Elige una opción del menú (1-4) '))

if opcion < 1 or opcion > 4:
    print('¡ERROR! Sólo puedes elegir una opción entre 1 y 4')

elif opcion == 1:
    diametro = 2 * radio
    print('El diámetro de la circunferencia es de', diametro)

elif opcion == 2:
    perimetro = 2 * pi * radio
    print('El perímetro de la circunferencia es de', perimetro)

elif opcion == 3:
    area = pi * radio ** 2
    print('El área del círculo es de', area)

elif opcion == 4:
    exit('Has elegido salir de la aplicación')
