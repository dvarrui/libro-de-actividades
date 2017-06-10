@echo off
:: Borrar 29 usuarios
set minimo=0
:bucle
if %minimo% LSS 29 (
echo CREANDO USUARIO usuario%minimo%
net user usuario%minimo% /del
set /a minimo=%minimo%+1
echo USUARIO usuario%minimo% BORRADO
goto :bucle
)
echo Fin de programa &pause>nul
exit
