@echo off
:: Crear 29 usuarios
set maximo=0
:bucle
if %maximo% LSS 29 (
echo CREANDO USUARIO usuario%maximo%
net user usuario%maximo% /add
set /a maximo=%maximo%+1
echo USUARIO usuario%maximo% CREADO
goto :bucle
)
echo Fin de programa &pause>nul
exit
