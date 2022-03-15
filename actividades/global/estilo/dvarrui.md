
# Modo de trabajo

1. [Requisitos](#1-requisitos)
1. [Dudas](#2-dudas)
1. [Entregas](#3-entregas)

# 1. Requisitos

* Tener algo donde "apuntar" o escribir anotaciones. Bien puede ser una libreta con bolígrafo, ficheros TXT en portátil, etc.
* En casa hará falta tener acceso a Internet, y tener VirtualBox instalado en tu PC.
* Un disco duro externo USB y/o pendrive de tamaño recomendado 300 GB para que te quepan todas las máquinas virtuales que vas a necesitar.
* [OPCIONAL] Este disco duro externo puede tener su propio SO instalado... lo que te hará la vida mucho más sencilla.

# 2. Dudas

Pasos que debemos hacer cuando tengamos dudas y/o queramos resolver un problema:
1. Atenderemos la explicación del profesor/a.
2. Le preguntamos dudas de la explicación. Apunta lo importante.
3. Leemos la actividad completa al menos durante 10 minutos.
4. Si tenemos dudas... volvemos a leer la actividad completa por segunda vez pero más despacio.
5. Empezamos la práctica. Paso a paso. Vamos registrando, apuntando, documentando los pasos que ha
cemos, las decisiones que vamos tomando.
6. Avanza paso a paso entendiendo lo que se hace. Si no lo entiendes... para ahora por que te vas a bloquear dentro de 3 pasos. NO sigas... la duda... la tienes AQUI. Hasta que no lo entiendas... no sigas. NO te sirve de nada hacer las cosas sin entender. Cuando tenemos un problema/duda... paramos.
7. Tenemos un problema o duda. Volvemos a leer... y nos preguntamos
    * ¿He escrito algo mal en este punto N? Lo compruebo.
    * Estoy entendiendo este punto... Busco entender. Se supone que has entendido todo lo anterior... en caso contrario... vuelve a empezar porque te has saltado pasos.
8. Si tienes que preguntar... escribe en el **foro de dudas** todos los detalles del problema, de modo que quien lo lea tenga toda la información para poder ayudarte.

# 3. Entregas

¿Cómo haremos las entregas de los trabajos? Bueno tenemos 3 formas:
* Entregar informe/guía/tutorial de la actividad realizada.
* Tener SSH habilitado, para que el profesor pueda corregir de forma remota.
* Esperar a que el profesor pase por tu máquina y la evalúe.

## 3.1 Informes, guías o tutoriales

Los informes los crearemos haciendo uso de:
* GitHub y
* Markdown

Entregaremos la tarea por la plataforma Moodle:
* Entregando URL completo del documento Markdown
* Valor o ńumero que idetifica al _commit_ de la entrega por GitHub.

Reglas para los nombres de ficheros y directorio

1. **Nombres de ficheros y directorios**: Escribir los nombres de ficheros y directorios en minúsculas, sin espacios (usar - o _ para separar las palabras), ni usar caracteres que no sean internacionales (Como ñ, tildes, etc.)
2. **Escribir en castellano o inglés**: usaremos el castellano para comunicarnos, si se decide podremos cambiar al inglés. Eso sí, siempre respetando las normas del propio idioma. Si no se respetan... es otra cosa diferente y puede NO entenderse.

## 3.2 SSH

* Activar SSH en tus MVs.
* Configurar /etc/ssh/sshd_config para permitir acceso remoto con root.
* Poner el fichero `profesor_rsa.pub` en el directorio `/root/.ssh`.
* Comprobar que se puede acceder a la MV de forma remota usando SSH con el usuario root.

# 4. Rutinas

* Los primeros 15 minutos de la clase del primer día de cada semana, un compañero hará una exposición en clase sobre un tema de libre elección basado en los contenidos del módulo.
* Al final de cada actividad, un compañero expondrá en clase durante 15 minutos, el trabajo realizado, los problemas encontrados y soluciones. Se establecerá un debate de 15 minutos en clase donde participaremos todos.
* Al final de cada trimestre se pasará una folio en blanco para que los alumn@s escriban por un lado todos los aspectos positivos
