import sys

dni = int(sys.argv[1])
cadena = "TRWAGMYFPDXBNJZSQVHLCKE"
letra = cadena[dni % 23]
print(f'Tu DNI completo es {dni}{letra}')
