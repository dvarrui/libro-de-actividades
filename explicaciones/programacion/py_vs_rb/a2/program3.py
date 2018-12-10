import sys

nota = float(sys.argv[1])

if nota < 0 or nota > 10:
    print('¡ERROR! Sólo puedes introducir calificaciones de 0 a 10 puntos')
elif nota >= 0 and nota < 5:
    print('Tu calificación es de',nota,'puntos. Estas suspendido.')
elif nota >= 5 and nota < 7:
    print('Tu calificación es de',nota,'puntos. Tienes un aprobado.')
elif nota >= 7 and nota < 9:
    print('Tu calificación es de',nota,'puntos. Tienes un notable.')
elif nota >= 9 and nota < 10:
    print('Tu calificación es de',nota,'puntos. Tienes un sobresaliente.')
else:
    print('Tu calificación es de',nota,'puntos. Tienes matrícula de honor.')
