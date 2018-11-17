<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--plantilla de tipo 07-->
<html>
<head>
   <!--head-->
   <? include("rec/sec/head_v01.php"); ?>
</head>

<body>
   <table border="0" cellpadding="0" cellspacing="0" width="100%">
   <tbody>
      <tr>
         <td align="center" height="120" valign="bottom">
            <table class="tablaMenu" align="center" border="0" cellpadding="0" cellspacing="0" height="110" width="90%">
            <tbody>
               <tr>
                  <td>
                     <!--cabecera-->
                     <? include("rec/sec/cabecera_v01.php"); ?>
                  </td>
               </tr>
            </tbody>
            </table>
         </td>
      </tr>


      <tr>
         <td align="center" height="20">
            <table border="0" cellpadding="0" cellspacing="0" width="90%">
            <tbody>
               <tr>
                  <!--navegacion-->
                  <? include ("rec/sec/navegacion_v01.php"); ?>
               </tr>
            </tbody>
            </table>
         </td>
      </tr>


      <tr> <!--fila central con texto principal-->
         <td align="center" valign="top">
         <table border="0" cellpadding="2" cellspacing="0" width="90%">
         <tbody>

            <tr valign="top">
               <td class="tablaMenu" width="150">
                  <!--menu izq-->
                  <? include ("rec/sec/menuizq_v01.php"); ?>
               </td>

               <td width="10"><!-- columna vacía-->&nbsp;</td>

               <td class="tablaMenu">
                  <table border="0" cellpadding="2" cellspacing="0" width="100%">
                  <tbody>
                     <tr>
                        <td class="textoCuerpo" align="left">
                        <!--menu central-->
                        <!--PLANTILLA.BEGIN-->

                        <!--contenidos-->
                        <? include($contenido); ?>

                        <!--PLANTILLA.END-->
                        </td>
                     </tr>
                  </tbody>
                  </table>
               </td>

            </tr>
         </tbody>
         </table>
         </td>
      </tr>

      <tr> <!--fila del pie de página-->
         <td align="center" height="20">
            <!--piedepagina-->
            <? include ("rec/sec/piedepagina_v01.php"); ?>
         </td>
      </tr>

   </tbody>
   </table>
</body>
</html>
