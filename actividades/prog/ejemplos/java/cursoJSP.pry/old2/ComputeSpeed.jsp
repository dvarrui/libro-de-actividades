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
<h1>JSP: Compute Speed</h1>

<%@ page errorPage="SpeedErrors.jsp" %>

<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">Computing Speed</TABLE>

<%! 
// Note lack of try/catch for NumberFormatException if
// value is null or malformed.

private double toDouble(String value) 
{ return(Double.valueOf(value).doubleValue());}
%>

<% 
double furlongs = toDouble(request.getParameter("furlongs")); 
double fortnights = toDouble(request.getParameter("fortnights"));
double speed = furlongs/fortnights;
%>

<UL>
  <LI>Distance: <%= furlongs %> furlongs.
  <LI>Time: <%= fortnights %> fortnights.
  <LI>Speed: <%= speed %> furlongs per fortnight.
</UL>

</BODY>
</HTML>
