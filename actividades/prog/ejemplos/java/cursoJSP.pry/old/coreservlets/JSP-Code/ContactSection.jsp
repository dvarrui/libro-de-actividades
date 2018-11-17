<%@ page import="java.util.Date" %>

<%-- The following become fields in each servlet that
     results from a JSP page that includes this file. --%>
<%! 
private int accessCount = 0;
private Date accessDate = new Date();
private String accessHost = "<I>No previous access</I>";
%>

<P>
<HR>
This page &copy; 2000 
<A HREF="http//www.my-company.com/">my-company.com</A>.
This page has been accessed <%= ++accessCount %>
times since server reboot. It was last accessed from 
<%= accessHost %> at <%= accessDate %>.

<% accessHost = request.getRemoteHost(); %>
<% accessDate = new Date(); %>

