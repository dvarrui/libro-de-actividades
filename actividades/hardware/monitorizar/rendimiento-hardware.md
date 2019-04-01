

# 1. Introducción

Vamos a estudiar el rendimiento de dos equipos, monitorizando el hardware,
y optimizando el sistema.

> Consultar documentación de Moodle.

---

# 2. Preparativos

* Trabajar en parejas.
* Usaremos 2 máquinas entre las siguientes que tengan el mismo SO:
    * Máquina real, propia de cada alumno.
    * Una máquina virtual.
    * Una máquina real del taller y/o departamento.
* Entregar un informe en formato ODT o PDF. Si fueran varios documentos,
entregar un comprimido ZIP.

---

# 3. Características de la Máquina

* Detallar las características de las dos máquinas que vamos a usar.
    * CPU
    * RAM
    * Disco duro
    * Tarjeta gráfica
    * etc.

> NOTA:
> * Debemos asegurarnos de tener correctamente los drivers de nuestra máquina. Consultar "Administrador de dispositivos".
> * Buscar drivers para nuestro equipo [enlace](https://support.hp.com/es-es/product/hp-compaq-dx6100-microtower-pc/402168/drivers)
, para descargar los drivers de los equipos HP que estamos utilizando para la práctica en clase.
> * Podemos obtener una ISO de Hiren Boot, descargándola de Leela.

---

# 4. Monitorización

Ejecutar las siguientes herramientas:

| Herramienta | Windows | GNU/Linux |
| ----------- | ------- | --------- |
| Realizar inventariado del equipo | AIDA/EVEREST Home Edition | Hardinfo |
| Rendimiento de la CPU | CPU‐Z | LMBench: test de rendimiento (benchmarking). |
| Comprobar la RAM | Memtest86+ | Memtest86+ |
| Rendimiento de los discos duros | HD tune | SmartMonTools: monitorización de los discos usando el sistema S.M.A.R.T. de los propios discos |
| Rendimiento de la tarjeta gráfica | GPU‐Z (En las máquinas virtuales no muestra información) | XNVCtrl: Monitorización de GPUs de NVIDIA |


Otros programas

| Programa | Descripción |
| -------- | ----------- |
| Hiren | CD con muchas herramientas |
| CPUCool | HW Monitor |
| GNU Krell Monitors | Herramienta gráfica para monitorizar distintos parámetros del equipo (depende de “lmsensors” y “hdd-temp”, entre otros) |
| PSensor | Herramienta gráfica para monitorizar la temperatura del hardware (CPU, GPU, placa base, discos duros, velocidad de rotación de los ventiladores,…). Es posible monitorizar una máquina remota (psensorserver). |
| CPUBurn | pruebas de estrés de la CPU (PELIGROSO; úsalo bajo tu propia responsabilidad; vigilar la temperatura del micro cuando se hagan las pruebas). Para microprocesadores Intel(burnP6). Para micros AMD (burnK7) |
| GParted | Herramienta de gestión de particiones |

---

# 5. Comparar

* Comparar los resultados obtenidos entre cada máquina.

---

# 6. Optimización

* Usar utilidades de optimización para mejorar el rendimiento del sistema en una de las máquinas.
    * Comprobación de errores del disco, chkdsk o Scandisk
    * Desfragmentador del disco
* Usar estas herramientas en la máquina virtual y/o real del taller:
    * Utilidad TuneUp Utilites
    * Utilidad Revo Uninstaller Pro
    * Utilidad Registry Healer

> Podemos usar el CD de Hiren y otras herramientas.

---

# 7. Conclusiones

* Comentarios/análisis sobre los datos obtenidos.

---

# ANEXO

Otras herramientas:
* [Phoronix Test Suit](www.phoronix-test-suit.com) (MuyLinux): para varias plataformas
* FurMark: para hacer pruebas de estrés de la tarjeta gráfica.
