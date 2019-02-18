
# Crear nuestro paquete rpm en OpenSUSE

Esta práctica está basada en el tutorial [Build rpm packages with the rpmbuild command](http://www.linuxintro.org/wiki/Build_rpm_packages_with_the_rpmbuild_command)

# 1. Introducción

Vamos a construir un paquete RPM con el comando `rpmbuild` y el fichero `SPEC`.
Crearemos un programa que muestra en pantalla el mensaje "hello world", añadiremos
el ficheros `makefile` para construirlo, y crearemos un fichero `SPEC` para empaquetar
el software como un fichero ROM.

# 2. Instalación del software

* `yast -i rpm-build`, instalamos el paquete rpmbuild.

# 3. Crear los ficheros del programa hello

## 3.1 Creamos nuestro programa hello

```
mkdir /root/hello-1.0
cd /root/hello-1.0
cat >main.c <<EOF
#include <stdio.h>

int main()
{
  printf("Hello World! (nombre-alumnoXX)\n");
}
EOF
```

## 3.2 Fichero Makefile

* Creamos el fichero `/root/hello-1.0/Makefile` correspondiente:
```
cat >Makefile <<EOF
all:hello

hello: main.c
        gcc main.c -o hello

install: hello
        mkdir -p \${prefix}/usr/local/bin
        cp hello \${prefix}/usr/local/bin
EOF
sed -i "s/        /\t/g" Makefile
```

## 3.3 Fichero SPEC

* Creamos el fichero `SPEC` en `/root/hello-1.0/hello.spec`.
Se necesita el fichero SPEC para crear el paquete RPM.

```
cat >hello.spec<<EOF
Summary: hello greets the world
Name: hello
Version: 1.0
Release: 1
License: GPL
Group: Applications/Tutorials
Source: hello.tar.gz
URL: http://www.iespuertodelacruz.es
Distribution: OpenSUSE
Vendor: -
Packager: NOMBRE-DEL-ALUMNO-XX

%description
hello greets the world

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

> License is a free-text field. You can enter what you want.
> Source is the file that will be stored in /usr/src/packages/SOURCES.

---

# 4. Construir el RPM

* Almacenar el código fuente en el lugar apropiado:
```
cd
tar cvzf hello.tar.gz hello-1.0
cp hello.tar.gz /usr/src/packages/SOURCES
```
* `rpmbuild -ba hello-1.0/hello.spec`
* Encontraremos nuestro RPM en `/usr/src/packages/RPMS`.

----

# 5. Comprobar RPM

* `zypper se hello`
* `zypper info hello`
* `rpm -ivh /usr/src/packages/RPMS/x86_64/hello-1.0-1.x86_64.rpm`, instalar RPM.
* `rpm -ql hello`, listar los ficheros que contiene el paquete.
* `rpm -qi hello`, consultar información del paquete.

* `ll /usr/local/bin/hello`, el programa hello está instalado.
* `rpm -e hello`,desinstalar el paquete.
* `ll /usr/local/bin/hello`, el programa ya no existe.

> **Clean up**
> To clean up, run
> rm /usr/src/packages/SOURCES/hello.tar.gz
> rm -rf /usr/src/packages/BUILD/hello-1.0

---

# ANEXO

Enlaces de interés:
* [OpenSUSE – Build a rpm package](https://eureka.ykyuen.info/2009/12/28/opensuse-build-a-rpm-package/)
* [Build RPM Package for Installation and Management by System Package Manager](https://www.ordinatechnic.com/os-specific-guides/opensuse/build-rpm-package-for-local-installation)

```
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
