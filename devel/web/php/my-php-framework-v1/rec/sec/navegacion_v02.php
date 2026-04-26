<!-- navegación-->
<td class="textoNavegacion" align="left">
<?
	if ($pagina=="index") {
		echo '::Principal';
	} else {
		echo ' ::<a href=javascript:history.back()>Volver</a> ';
		?> ::<a href="<? echoPagina('index');?>">Principal</a> <?
		echo $navegacion .$nombre;
		//echo $navegacion .$descripcion;
	}
?>
</td>


<!--<td class="textoNavegacion" align="right">::
<a href="<? echoPagina('padres.index'); ?>">Padres</a> | 
<a href="<? echoPagina('alumnos.index'); ?>">Alumn@s</a> | 
<a href="<? echoPagina('secretaria.index'); ?>">Secretar&iacute;a</a> | 
<a href="<? echoPagina('direccion.index'); ?>">Direcci&oacute;n</a> | 
<a href="<? echoPagina('centro.index'); ?>">Centro</a> | 
<a href="<? echoPagina('profesorado.index'); ?>">Profesorado</a>
</td>-->