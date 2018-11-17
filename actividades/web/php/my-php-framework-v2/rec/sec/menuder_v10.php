
<!--menu derecho-->
<div class='menu_derecho'>
<table align='center' border="0" cellpadding="2" cellspacing="0">
<tbody>
  <? 
  	include('rec/php/subcategorias.php');
  	echoCategorias($pagina,$mapping);
  	echoSubcategorias($pagina,$mapping);
  ?>


  <tr>
    <td class="textoTituloMenu" align="left" bgcolor="#000000">
    <img src="rec/ico/info.gif" alt="buscar" align="middle" height="30"> 
    Buscar
    </td>
   </tr>

   <tr>
      <td align="center">
         <FORM ACTION="cargador.php" METHOD="GET">
         <INPUT TYPE="hidden" NAME="pagina"  value="buscador">
         <INPUT TYPE="text" NAME="buscar">
         <INPUT TYPE="submit"  name="Buscar">
         </FORM>
      </td>
   </tr>

   <tr>
      <td>&nbsp;</td>
   </tr>

         		<tr>
      		<td class="textoTituloMenu" align="left" bgcolor="#000000">
         	<img src="rec/ico/network.gif" alt="anillo" align="middle" height="30" width="30"> 
         	Anillo
      		</td>
   			</tr>

   			<tr>
      		<td class="enlaceMenu" align="left">
         	<img src="rec/ico/arrow.gif" alt="flecha" align="middle" height="9" width="11">
         	<a href="http://www.iespuertodelacruz.es/">Anterior</a>
      		</td>
   			</tr>

   			<tr>
      		<td class="enlaceMenu" align="left">
         	<img src="rec/ico/arrow.gif" alt="flecha" align="middle" height="9" width="11">
         	<a href="http://www.iesroqueamagro.es/">Siguiente</a>
      		</td>
   			</tr>

  <tr>
    <td class="enlaceMenu" align="left">
    <img src="rec/ico/arrow.gif" alt="flecha" align="middle" height="9" width="11"> Al azar 
    </td>
  </tr>
		
</tbody>
</table>
</div>