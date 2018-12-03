def show_contacts(phone_book):
    while True:
        if phone_book != {}:
            for name, number in phone_book.items():
                print('{}: {}'.format(name, number))
            break
        else:
            print('Tu agenda está vacía. Pulsa 2 para agregar contactos.')
            break

def add_contacts(phone_book, name, phone):
    phone_book[name] = phone
    print('Contacto agregado. ¿Qué deseas hacer a continuación?')

def remove_contacts(phone_book, name):
    del(phone_book[name])
    print('Contacto eliminado. ¿Qué deseas hacer a continuación?')

def menu():
    phone_book = {}
    while True:
        print('╔══════════════════════════╗')
        print('║  Tu agenda de contactos  ║')
        print('╠══════════════════════════╣')
        print('║   1. Mostrar contactos   ║')
        print('║   2. Añadir contacto     ║')
        print('║   3. Eliminar contacto   ║')
        print('║   4. Salir               ║')
        print('╚══════════════════════════╝')

        option = int(input('Elige una opción del menú (1-4):  '))
        if 0 <= option > 4:
            print('¡ATENCIÓN! Opción no disponible.')
        elif option == 1:
            print('Lista de contactos:')
            show_contacts(phone_book)
        elif option == 2:
            name = input('Nombre del nuevo contacto: ')
            if name in phone_book:
                print('¡ATENCIÓN! Ya existe un contacto con ese nombre.')
            else:
                phone = input(f'Escribe el número de teléfono de {name}: ')
                if phone.isnumeric() and len(phone) == 9:
                    add_contacts(phone_book, name, phone)
                else:
                    print('¡ATENCIÓN! Introduce un número de teléfono válido.')
        elif option == 3:
            name = input('Introduce el contacto que deseas eliminar: ')
            if name in phone_book:
                remove_contacts(phone_book, name)
            else:
                print(f'El contacto {name} no existe en la agenda')
        elif option == 4:
            print('Has elegido salir del programa')
            break
menu()
