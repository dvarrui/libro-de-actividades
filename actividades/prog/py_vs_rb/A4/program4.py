import sys

numeros = sys.argv[1:]

suma = len(numeros)
contador = 0

for numero in numeros:
    numero = float(numero)
    contador += numero
    media = contador / suma

print(f"La media de los valores introducidos es {media}")
