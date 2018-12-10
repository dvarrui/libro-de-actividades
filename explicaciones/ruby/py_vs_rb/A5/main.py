import sys


def num_vowels(text):
    vocales = ['a', 'e', 'i', 'o', 'u']
    total_vocales = 0
    for letra in text.lower():
        if letra in vocales:
            total_vocales = total_vocales + 1
    return total_vocales

def num_whitespaces(text):
    total_blank = 0
    for char in text:
        if char in ' ':
            total_blank = total_blank + 1
    return total_blank


def num_digits(text):
    numeros = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    total_numeros = 0
    for numero in text:
        if numero in numeros:
            total_numeros = total_numeros + 1
    return total_numeros


def num_words(text):
    total_palabras = 0
    for palabra in text.split(' '):
        total_palabras = total_palabras + 1
    return total_palabras


def reverse(text):
    invertido = text[-1::-1]
    return invertido


def length(text):
    text_length = len(text)
    return text_length


def halves(text):
    mitad = len(text) // 2
    mitad_uno = text[:mitad]
    mitad_dos = text[mitad:]
    union = mitad_uno + ' | ' + mitad_dos
    return union


def upper_vowels(text):
    vocales = ['a', 'e', 'i', 'o', 'u']
    cadena = ''
    for letra in text:
        if letra in vocales:
            cadena = cadena + letra.upper()
        else:
            cadena = cadena + letra
    return cadena


def sorted_by_words(text):
    palabras = text.split()
    palabras.sort()
    ordenado = ' '.join(palabras)
    return ordenado


def length_of_words(text):
    lista = list()
    for palabra in text.split():
        longitud = len(palabra)
        lista.append(str(longitud))
    muestra = ' '.join(lista)
    return muestra


text = sys.argv[1]
print("Number of vowels:", num_vowels(text))
print("Number of whitespaces:", num_whitespaces(text))
print("Number of digits:", num_digits(text))
print("Number of words:", num_words(text))
print("Reverse of text:", reverse(text))
print("Length of text:", length(text))
print("Halves of text:", halves(text))
print("Text with uppercased vowels:", upper_vowels(text))
print("Sorted by words:", sorted_by_words(text))
print("Length of words:", length_of_words(text))
