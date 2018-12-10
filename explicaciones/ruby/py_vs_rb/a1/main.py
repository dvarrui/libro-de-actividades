import sys
dinero = int(sys.argv[1])

print('Para el importe solicitado tienes: ')

if  dinero // 100 >= 1:
    print(dinero // 100,'billete/s de 100 euros')
    dinero = dinero % 100

if  dinero // 50 >= 1:
    print(dinero // 50,'billete/s de 50 euros')
    dinero = dinero % 50

if  dinero // 20 >= 1:
    print(dinero // 20,'billete/s de 20 euros')
    dinero = dinero % 20

if  dinero // 10 >= 1:
    print(dinero // 10,'billete/s de 10 euros')
    dinero = dinero % 10

if  dinero // 5 >= 1:
    print(dinero // 5,'billete/s de 5 euros')
    dinero = dinero % 5

if  dinero // 2 >= 1:
    print(dinero // 2,'moneda/s de 2 euros')
    dinero = dinero % 2

if  dinero // 1 >= 1:
    print(dinero // 1,'moneda/s de 1 euro')
    dinero = dinero % 1
