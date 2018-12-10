import sys

class VirtualMachine:
    def __init__(self, name, ram=1, cpu=1.3, hdd=100, os="debian"):
        self.name = name
        self.ram = ram
        self.cpu = cpu
        self.hdd = hdd
        self.os = os
        self.status = 0
        self.proc = []

    def stop(self):
        self.status = 0
        self.proc = []

    def start(self):
        self.status = 1

    def suspend(self):
        self.status = 2

    def reboot(self):
        self.stop()
        self.start()

    def run(self, pid, ram, cpu, hdd):
        self.proc.append({
            'pid' : pid,
            'ram' : ram,
            'cpu' : cpu,
            'hdd' : hdd
            }
        )
        print(f' -Ejecutamos el proceso {pid}')

    def ram_usage(self):
        uso_ram = 0
        for proceso in self.proc:
            uso_ram += proceso['ram']
        return round(uso_ram / self.ram * 100, 2)

    def cpu_usage (self):
        uso_cpu = 0
        for proceso in self.proc:
            uso_cpu += proceso['cpu']
        return round(uso_cpu / self.cpu * 100, 2)

    def hdd_usage (self):
        uso_hdd = 0
        for proceso in self.proc:
            uso_hdd += proceso['hdd']
        return round(uso_hdd / self.hdd * 100, 2)

    def __str__(self):
        estado = ''
        if self.status == 0:
            estado = 'Stopped'
        elif self.status == 1:
            estado = 'Running'
        else:
            estado = 'Suspended'
        return f'Nombre: {self.name} | SO: {self.os} | {estado} | RAM: {self.ram} | CPU: {self.cpu} | HDD: {self.hdd} | {self.ram_usage()}% RAM used | {self.cpu_usage()}% CPU used | {self.hdd_usage()} % HDD used'

if __name__ == '__main__':
   print('═════════════════')
   print('Máquina virtual 1')
   print('═════════════════')
   print('1. Creamos la máquina virtual Minas Tirith')
   vm1 = VirtualMachine('Minas Tirith', 8, 2.3, 380, 'ubuntu')
   print(vm1)
   print('2. Arrancamos la máquina virtual')
   vm1.start()
   print(vm1)
   print('3. Lanzamos los procesos 1, 4 y 7')
   vm1.run(1, 1.7, 0.3, 20)
   vm1.run(4, 4, 0.9, 100)
   vm1.run(7, 0.4, 1.1, 250)
   print(vm1)
   print('4. Paramos la máquina virtual')
   vm1.stop()
   print(vm1)
   print(' ')

   print('═════════════════')
   print('Máquina virtual 2')
   print('═════════════════')
   print('1. Creamos la máquina virtual Rohan')
   vm2 = VirtualMachine ('Rohan', 6, 1.9, 250, 'debian')
   print(vm2)
   print('2. Arrancamos la máquina virtual')
   vm2.start()
   print(vm2)
   print('3. Lanzamos los procesos 2, 5 y 8')
   vm2.run(2, 0.6, 0.7, 50)
   vm2.run(5, 2.1, 0.2, 75)
   vm2.run(8, 2.5, 0.4, 30)
   print(vm2)
   print('4. Paramos la máquina virtual')
   vm2.stop()
   print(vm2)
   print(' ')

   print('═════════════════')
   print('Máquina virtual 3')
   print('═════════════════')
   print('1. Creamos la máquina virtual Rivendel')
   vm3 = VirtualMachine ('Rivendel', 16, 3, 1000, 'opensuse')
   print(vm3)
   print('2. Arrancamos la máquina virtual')
   vm3.start()
   print(vm3)
   print('3. Lanzamos los procesos 3, 6 y 9')
   vm3.run(3, 2, 1, 25)
   vm3.run(6, 0.3, 0.5, 12)
   vm3.run(9, 1.4, 0.8, 65)
   print(vm3)
   print('4. Paramos la máquina virtual')
   vm3.stop()
   print(vm3)
