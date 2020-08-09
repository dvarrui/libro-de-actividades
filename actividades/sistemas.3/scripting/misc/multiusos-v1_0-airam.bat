cls
@ECHO OFF
color 17
title Cositas Airam v1.0

#PONERLE CONTRASEÑA (FÚTURO)
#SET /P contraseña= Introduce pass:
#if %contraseña%== airamsz9 goto MENU
#set /p ver= Intentando usar algo sin permiso.
#EXIT

:MENU
cls
echo Voy ayudarte un poquito:
echo 1 para organizar su escritorio.
echo 2 para cambiar la fecha y hora.
echo 3 para renovar nuestra ip y limpiar nuestro cache de DNS.
echo 4 para activar o desactivar el firewall.
echo 5 para hacer un ping a google (asi sabes si tienes internet).
echo 6 para abrir el administrador de tareas.
echo 7 para abrir desinstalador de programas o teclado en pantalla.
echo 8 para agregar/borrar usuarios en el equipo o dominio.
echo 9 para abrir el administador de usuarios.
echo 10 para SALIR
echo (((((((((((((((((((Realizado por Airam Suarez)))))))))))))))))))))))
set/p "cho=>>"
if %cho%== 1 goto 1
if %cho%== 2 goto 2
if %cho%== 3 goto 3
if %cho%== 4 goto 4
if %cho%== 5 goto 5
if %cho%== 6 goto 6
if %cho%== 7 goto 7
if %cho%== 8 goto 8
if %cho%== 9 goto 9
if %cho%== 10 goto 10

set /p ver= Eso no esta disponible. Repite con un numero disponible.
goto MENU

:1
cls

echo Iniciando...
echo Buscando archivos...
echo Porfavor espere...

#Variables
SET bac=%USERPROFILE%\Desktop\Backup\
SET usr=%USERPROFILE%\Desktop\
SET img=%USERPROFILE%\Desktop\Backup\Imagenes
SET doc=%USERPROFILE%\Desktop\Backup\Documentos
SET gen=%USERPROFILE%\Desktop\Backup\General
SET vid=%USERPROFILE%\Desktop\Backup\Videos
SET mus=%USERPROFILE%\Desktop\Backup\Musica
SET mus=%USERPROFILE%\Desktop\Backup\Ejecutables

#Creamos las carpetas

mkdir %doc% 
mkdir %img%
mkdir %gen%
mkdir %vid%
mkdir %mus%
mkdir %pro%

#Movemos los ficheros a las carpetas.
move %usr%\*.PNG %img%  
move %usr%\*.JPEG %img%
move %usr%\*.JPG %img%
move %usr%\*.DOC %doc%
move %usr%\*.PDF %doc%
move %usr%\*.DOCX %doc%
move %usr%\*.ODT %doc%
move %usr%\*.PPT %doc%
move %usr%\*.rar %gen%
move %usr%\*.torrent %gen%
move %usr%\*.txt %doc%
move %usr%\*.zip %gen%
move %usr%\*.7z %gen%
move %usr%\*.mp3 %mus%
move %usr%\*.aac %mus%
move %usr%\*.mp4 %vid%
move %usr%\*.avi %vid%
move %usr%\*.mkv %vid%
move %usr%\*.flv %vid%
move %usr%\*.exe %pro%
move %usr%\*.msi %pro%
move %usr%\*.bat %pro%
move %usr%\*.wmv %vid%
move %usr%\*.iso %gen%
move %usr%\*.gif %img%
move %usr%\*.ico %img%
move %usr%\*.bmp %img%
move %usr%\*.xls %doc%
move %usr%\*.html %doc%
move %usr%\*.xml %doc%
move %usr%\*.pps %doc%

START %bac%

echo Los ficheros disponibles se han movido correctamente.
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:2
date
time
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:3
ipconfig /release && ipconfig /flushdns && ipconfig /renew
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:4
echo Teclea a para "activar" o d para "desactivar" el firewall (que original soy...)
set/p "cho=>>"
if %cho%== a goto a
if %cho%== d goto d
:a
netsh advfirewall set allprofiles state on
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU
:d
netsh advfirewall set allprofiles state off
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:5
ping -n 3 www.google.es
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:6
TASKMGR
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:7
echo Teclea t para abrir teclado en pantalla.
echo Teclea p para abrir desinstalador de programas
set/p "cho=>>"
if %cho%== t goto t
if %cho%== p goto p

:t 
osk
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:p 
appwiz.cpl
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:8
echo Teclea x para agregar usuario
echo Teclea y para borrar usuario
echo Teclea z para borrar usuario al DOMINIO
echo Teclea w para agregar usuario al DOMINIO

set/p "cho=>>"
if %cho%== x goto x
if %cho%== z goto z
if %cho%== y goto y
if %cho%== w goto w

:x
SET /P USER_NAME=Introduce tu nombre:
SET /P USER_PASS=Introduce tu passwd:
net user %USER_NAME% %USER_PASS% /ADD
set /p ver= :D
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:y
net user %USER_NAME% %USER_PASS% /DEL
set /p ver= :D
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:z
set eq=hostname
set dom= echo %USERDOMAIN%
SET /P USER_NAME=Introduce tu nombre:
SET /P USER_PASS=Introduce tu passwd:
netdom remove %eq% /%dom% /%USER_NAME% /%USER_PASS% /Force
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:w
set eq=hostname
set dom=echo %USERDOMAIN%
SET /P USER_NAME=Introduce tu nombre:
SET /P USER_PASS=Introduce tu passwd:
netdom add %eq% /%dom% /%USER_NAME% /%USER_PASS% /Force
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:9
CONTROL USERPASSWORDS
set /p ver=Pulsa cualquier tecla para volver al menu.
goto MENU

:10
EXIT