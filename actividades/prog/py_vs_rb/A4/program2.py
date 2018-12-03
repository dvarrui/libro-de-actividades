import random

NUCLEOBASES = "ATGC"
DNA_SIZE = 100

sequence = ''.join([random.choice(NUCLEOBASES) for i in range(DNA_SIZE)])

a = 0
c = 0
g = 0
t = 0

for i in sequence:
    if i == 'A':
        a = a + 1
    if i == 'G':
        g = g + 1
    if i == 'C':
        c = c + 1
    if i == 'T':
        t = t + 1

print(f'Adenine: {a}')
print(f'Guanine: {g}')
print(f'Cytosine: {c}')
print(f'Thymine: {c}')

#
