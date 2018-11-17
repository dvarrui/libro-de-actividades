<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Curso JSP (Java Server Pages)</title>
</head>
<body bgcolor=white>

<table border="0">
<tr>
<td align=center>
<img src="../images/java_logo.gif">
</td>
<td>
<h1>JSP: Comparando manzanas y naranjas</h1>
</td>
</tr>
</table>

<CENTER><H2>Comparando manzanas y naranjas</H2>

<% 
String format = request.getParameter("format");
if ((format != null) && (format.equals("excel"))) 
{ response.setContentType("application/vnd.ms-excel"); }  
%>

<TABLE BORDER=1>
  <TR><TH></TH><TH>Apples<TH>Oranges
  <TR><TH>First Quarter<TD>2307<TD>4706
  <TR><TH>Second Quarter<TD>2982<TD>5104
  <TR><TH>Third Quarter<TD>3011<TD>5220
  <TR><TH>Fourth Quarter<TD>3055<TD>5287
</TABLE>

</CENTER>
</BODY>
</HTML>
