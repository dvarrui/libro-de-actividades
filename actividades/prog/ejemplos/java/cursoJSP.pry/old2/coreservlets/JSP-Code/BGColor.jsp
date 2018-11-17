<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!--    
Taken from Core Servlets and JavaServer Pages
from Prentice Hall and Sun Microsystems Press,
http://www.coreservlets.com/.
&copy; 2000 Marty Hall; may be freely used or adapted.
-->
<HTML>
<HEAD>
  <TITLE>Color Testing</TITLE>
</HEAD>

<%
String bgColor = request.getParameter("bgColor");
boolean hasExplicitColor;
if (bgColor != null) {
  hasExplicitColor = true;
} else {
  hasExplicitColor = false;
  bgColor = "WHITE";
}
%>
<BODY BGCOLOR="<%= bgColor %>">
<H2 ALIGN="CENTER">Color Testing</H2>

<%
if (hasExplicitColor) {
  out.println("You supplied an explicit background color of " +
              bgColor + ".");
} else {
  out.println("Using default background color of WHITE. " +
              "Supply the bgColor request attribute to try " +
              "a standard color, an RRGGBB value, or to see " +
              "if your browser supports X11 color names.");
}
%>

</BODY>
</HTML>