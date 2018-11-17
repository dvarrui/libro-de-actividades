<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- 
Example of sharing beans. 
   
Taken from Core Servlets and JavaServer Pages
from Prentice Hall and Sun Microsystems Press,
http://www.coreservlets.com/.
&copy; 2000 Marty Hall; may be freely used or adapted.
-->
<HTML>
<HEAD>
<TITLE>Shared Access Counts: Page 3</TITLE>
<LINK REL=STYLESHEET
      HREF="JSP-Styles.css"
      TYPE="text/css">
</HEAD>

<BODY>

<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">
      Shared Access Counts: Page 3</TABLE>
<P>
<jsp:useBean id="counter" 
             class="coreservlets.AccessCountBean"
             scope="application">
  <jsp:setProperty name="counter" 
                   property="firstPage"
                   value="SharedCounts3.jsp" />
</jsp:useBean>

Of SharedCounts3.jsp (this page), 
<A HREF="SharedCounts1.jsp">SharedCounts1.jsp</A>, and
<A HREF="SharedCounts2.jsp">SharedCounts2.jsp</A>, 
<jsp:getProperty name="counter" property="firstPage" />
was the first page accessed.
<P>
Collectively, the three pages have been accessed 
<jsp:getProperty name="counter" property="accessCount" />
times.
             
</BODY>
</HTML>