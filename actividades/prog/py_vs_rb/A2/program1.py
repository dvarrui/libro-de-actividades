import sys
import math

a = int(sys.argv[1])
b = int(sys.argv[2])
c = int(sys.argv[3])
discriminante = b ** 2 - 4 * a * c

print('Calculadora de Ecuaciones de Segundo Grado')
print('Coeficiente cuadrático (a) ---->', a)
print('Coeficiente lineal (b) ------->', b)
print('Término independiente (c) ----->', c)
print('Ecuación:',a,'x\xb2','+',b,'x','+',c)

input('Pulse ENTER para ver la solución')

if a == 0:
    x = -c / b
    print('Los valores introducidos no se corresponden con una ecuación de segundo grado')
    print('La solución es: x = ', x)

elif discriminante < 0:
    print('La ecuación no tiene solución real')

else:
    x1 = (-b + math.sqrt(discriminante)) / (2 * a)
    x2 = (-b - math.sqrt(discriminante)) / (2 * a)
    print('Soluciones: ')
    print('x1 = ', x1 )
    print('x2 = ', x2 )
