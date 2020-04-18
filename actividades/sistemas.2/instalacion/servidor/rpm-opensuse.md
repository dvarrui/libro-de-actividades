
```
Curso       : EN CONSTRUCCION!!!
Area        : Sistemas operativos, programación, administración
Descripción : Creación de paquetes RPM
Requisitos  : SO OpenSUSE
Tiempo      :
```

# 1. Crear paquetes RPM

## 1.1 Introducción

En esta práctica vamos a construir un paquete RPM con la herramienta `rpmbuild`. Nos basaremos principalmente en el primer enlace de la lista de tutoriales.

> Enlaces de interés:
> * EN [Build rpm packages with the rpmbuild command](http://www.linuxintro.org/wiki/Build_rpm_packages_with_the_rpmbuild_command)
> * EN [RPM Packaging Tutorial - Creating packages](http://duncan.codes/tutorials/rpm-packaging/index.html#creating-packages)
> * EN [Centos - rpmbuild tutorial - how to build rpm packages](https://rogerwelin.github.io/rpm/rpmbuild/2015/04/04/rpmbuild-tutorial-part-1.html)

## 1.2. Instalación del software

* `yast -i rpm-build`, instalamos el paquete rpmbuild.

---
# 2. Preparativos

Preparar los ficheros para construir nuestro paquete:
1. Código fuente
1. Makefile
1. SPECS

## 2.1 Código fuente del programa

Vamos a crear un programa en C de ejemplo (hello). Este programa sólo muestra un saludo por pantalla cuando se ejecuta.

* Crear un fichero `/root/helloXX/main.c`.
```
mkdir /root/helloXX
cd /root/helloXX
cat > main.c << EOF
#include <stdio.h>

int main()
{
  printf("nombre-alumnoXX: Hello World!\n");
}
EOF
```

## 2.2 Fichero Makefile

Tenemos que crear un fichero `makefile` que nos ayudará a construir el paquete RPM a partir del código fuente anterior.

* Creamos el fichero `/root/helloXX/Makefile` correspondiente:
```
cat > Makefile << EOF
all:hello

hello: main.c
        gcc main.c -o hello

install: hello
        mkdir -p \${prefix}/usr/local/bin
        cp hello \${prefix}/usr/local/bin
EOF
sed -i "s/        /\t/g" Makefile
```

## 2.3 Fichero SPEC

En el fichero `SPEC` se incluye metadatos del paquete RPM.

* Creamos el fichero `SPEC` en `/root/helloXX/hello.spec`.

```
cat >hello.spec<<EOF
Summary: Hello world from XX
Name: helloXX
Version: 1.0
Release: 1
License: GPL
Group: Applications/Tutorials
Source: helloXX.tar.gz
URL: http://www.iespuertodelacruz.es
Distribution: GNU/Linux
Vendor: -
Packager: NOMBRE-DEL-ALUMNO-XX

%description
Hello world from XX

%prep
%setup

%build
make

%install
make install prefix=\$RPM_BUILD_ROOT

%files
%defattr(-, root, root)
"/usr/local/bin/hello"
EOF
```

> NOTA:
> * License is a free-text field. You can enter what you want.
> * Source is the file that will be stored in /usr/src/packages/SOURCES.

---
# 3. Construir el RPM

* Almacenar el código fuente en el lugar apropiado:
```
cd /root
tar cvzf helloXX.tar.gz helloXX
cp helloXX.tar.gz /usr/src/packages/SOURCES
```
* `rpmbuild -ba helloXX/hello.spec`
* Encontraremos nuestro RPM en `/usr/src/packages/RPMS`.

----

# 5. Comprobamos

> Por curiosidad:
> * `zypper se hello`,
> * `zypper info helloXX`

Instalamos el paquete:
* `rpm -ivh /usr/src/packages/RPMS/x86_64/helloXX-1.0-1.x86_64.rpm`, instalar RPM.

Consultamos información del paquete ya instalado:
* `rpm -ql helloXX`, listar los ficheros que contiene el paquete.
* `rpm -qi helloXX`, consultar información del paquete.
* `ll /usr/local/bin/hello`, el programa hello está en nuestro sistema.
* `hello`, ejecutamos nuestro programa de HelloWorld.

Desinstalamos el paquete:
* `rpm -e helloXX`,desinstalar el paquete.
* `ll /usr/local/bin/hello`, el programa ya no existe.

> **Clean up**: To clean up, run
> rm /usr/src/packages/SOURCES/helloXX.tar.gz
> rm -rf /usr/src/packages/BUILD/helloXX-1.0

---
# ANEXO

Enlaces de interés:
* [OpenSUSE – Build a rpm package](https://eureka.ykyuen.info/2009/12/28/opensuse-build-a-rpm-package/)
* [Build RPM Package for Installation and Management by System Package Manager](https://www.ordinatechnic.com/os-specific-guides/opensuse/build-rpm-package-for-local-installation)

Rebuilding an existing src.rpm is probably the easiest. I would definitely not go the way of configure, make, make install because (a) that doesn't scale, (b) doesn't provide for easy removal / upgrading of the package and (c) is not atomic.

Building an RPM is not so hard. There used to be a pretty good beginners tutorial at Linux.com, before they destroyed the site. You can try this one, but it's a lot of text, not easy to digest. Still, it's better than nothing.

I'll give you a few pointers, out of the back of my head:

   setup an .rpmmacros file in ~
   create ~/rpmbuild/{RPMS,SRPMS,SPECS,BUILD,BUILDROOT,SOURCES}
   drop the source tarball in ~/rpmbuild/SOURCES
   drop the the spec file(s) in ~/rpmbuild/SPECS
   run rpmbuild -bp YOURSPEC in ~/rpmbuild/SPECS (runs prepare phase)
   run rpmbuild -bc YOURSPEC in ~/rpmbuild/SPECS (above and runs compile phase)
   run rpmbuild -bb YOURSPEC in ~/rpmbuild/SPECS (above and builds actual package)

If everything worked out, your RPM will have appeared in ~/rpmbuild/RPMS/${arch}.

I tend to use 5, 6 and 7 separately when creating new RPM's, because I like to inspect the ouput in detail. You might want to go for 7 immediately if you have a vendor provided specfile.

I usually have something like this in my .rpmmacros.

%_topdir /home/YOURNAME/rpmbuild
%packager YOUR NAME <YOUR@EMA.IL>
%_tmppath /tmp

As for the syntax of the specfile: it is not that hard. There exists a very detailed, be it very old, reference work called 'Maximum RPM'. Everything you want to know is in there.
```

## OBS

* [Beginnerʼs Guide | Open Build Service](https://openbuildservice.org/help/manuals/obs-beginners-guide/)
