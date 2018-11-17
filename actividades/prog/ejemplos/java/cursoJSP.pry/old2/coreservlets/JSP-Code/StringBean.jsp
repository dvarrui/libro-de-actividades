<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- 
Simple example of jsp:useBean and manipulating properties
with jsp:setProperty and jsp:getProperty and
with explicit Java code in scriptlets and expressions. 
   
Taken from Core Servlets and JavaServer Pages
from Prentice Hall and Sun Microsystems Press,
http://www.coreservlets.com/.
&copy; 2000 Marty Hall; may be freely used or adapted.
-->
<HTML>
<HEAD>
<TITLE>Using JavaBeans with JSP</TITLE>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</HEAD>

<BODY>

<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">
      Using JavaBeans with JSP</TABLE>
 
<jsp:useBean id="stringBean" class="coreservlets.StringBean" />

<OL>
<LI>Initial value (getProperty):
    <I><jsp:getProperty name="stringBean" 
                        property="message" /></I>

<LI>Initial value (JSP expression):
    <I><%= stringBean.getMessage() %></I>
<LI><jsp:setProperty name="stringBean" 
                     property="message" 
                     value="Best string bean: Fortex" />
    Value after setting property with setProperty:
    <I><jsp:getProperty name="stringBean" 
                        property="message" /></I>

<LI><% stringBean.setMessage("My favorite: Kentucky Wonder"); %>
    Value after setting property with scriptlet:
    <I><%= stringBean.getMessage() %></I>
</OL>
             
</BODY>
</HTML>