```
Curso       : 202122
Area        : Sistemas operativos, git, trabajo en equipo
Requisitos  : Git, editor texto plano
Tiempo      : 6 sesiones
Descripción : Resolver un problema en equipo usando git
```

# Trabajar en equipo con GIT

El objetivo es trabajar todos juntos de forma colaborativa en un proyecto común usando la herramienta GIT y un repositorio de GitHub.

## Descripción del problema

La IOC (Institut Obert de Catalunya) imparte cursos de formación profesional en la modalidad a distancia. Disponen de una página web (http://ioc.xtec.cat) con muchos recursos para el alumnado (https://ioc.xtec.cat/educacio/recursos).

En la seccin "ADMINISTRACIÓ DE SISTEMES INFORMÀTICS EN XARXA (CFGS)", hay apuntes para los módulos de ASIR con licencia libre (CC-BY-NC-SA), pero la dificultad para nosotros en que están en catalán.

## Objetivo

Vamos a trabajar de forma colaborativa toda la clase en los apuntes de la IOC asociados de nuestro módulo para traducir uno de los PDF's al castellano.

> **IMPORTANTE**: tenemos que respetar la licencia CC-BY-NC-SA, y hay que mantener la información de la misma en la traducción y por supuesto a los autores originales.

## Tareas

* El profesor creará un repositorio de GitHub, donde añadirá como colaboradores a todos los miembros de la clase.
* Se creará una estructura de la forma:
```
aasir
└── add
    └── scripting
        ├── 01
        │   ├── images
        │   └── README.md
        ├── 02
        │   ├── images
        │   └── README.md
        ├── 03
        │   ├── images
        │   └── README.md
        └── README.md
```
* Los alumnos se descargarán el PDF de "Llenguatges de guions i
automatització de tasques", que tiene 110 páginas.
* Nos repartiremos las páginas del documento entre cada uno de los miembros de la clase y empezaremos a traducir (Google translator).
* Incluiremos también las imágenes pero no hay que traducirlas.
* En el documento README.md de la sección 01 pondremos la traducción del apartado 1 del PDF, y así sucesivamente.
* Usaremos la carpeta "images" de cada sección para poner las imágenes.

## Modo de trabajo 1(todos en el mismo nivel)

Trabajaremos en nuestro equipo local con una copia del repositorio original (https://github.com/dvarrui/contenidos-cfgs-ioc) e iremos subiendo los cambios que vayamos realizando.

Durante el proceso surgirán "conflictos" normales del trabajo conjunto que iremos resolviendo sobre la marcha.

Primero vamos a trabajar, de modo que todos los miembros tenemos acceso de lectura/escritura directo al repositorio.

Cuando el profesor lo indique pasaremos al siguiente apartado.

## Modo de trabajo 2(Dos niveles)

En este, modo de trabajo, haremos 3 grupos. Sólo el responsable de cada grupo tendrá permisos de lectura/escritura sobre el repositorio principal.

Cada grupo deberá trabajar en un repositorio creado por su responsable (repositorio secundario). Y será el responsable de cada repositorio secundario el encargado de realizar y/o solicitar las "fusiones" con el repositorio principal.

# Entrega

Realizar un breve informe individual:
* Comentando lo que se ha hecho.
* Explicar en detalle los problemas que han surgido y
* Explicar en detalle como se han solucionado.

El resto del trabajo realizado se podrá analizar consultando los "commits" del repositorio.

# ANEXO

* https://ioc.xtec.cat/educacio/cfgs-dam
* https://ioc.xtec.cat/educacio/cfgs-daw
* https://ioc.xtec.cat/materials/FP/Recursos/fp_asix_m08_/web/fp_asix_m08_htmlindex/index.html
