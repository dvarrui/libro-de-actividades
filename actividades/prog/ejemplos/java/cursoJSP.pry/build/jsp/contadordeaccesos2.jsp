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
<h1>JSP: Contador de Accesos 2</h1>
</td>
</tr>
</table>

<%@ page import="java.util.Date" %>

<%! 
private int  contadorAccesos = 0;
private Date fechaAcceso     = new Date();
private String hostAcceso    = "<I>Primer acceso</I>";
%>

Esta página ha sido accedida <b><%= ++contadorAccesos %></b> veces desde el arranque del servidor.<br>
La última vez fue accedida el <b><%= fechaAcceso %></b> desde el host <b><%= hostAcceso %></b>.

<% hostAcceso  = request.getRemoteHost(); %>
<% fechaAcceso = new Date(); %>

