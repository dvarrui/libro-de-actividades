<html>
<title>Counter JavaBean Demo</title>
<!-- $Id: counter.jsp,v 1.3 2004/09/10 01:53:51 ian Exp $ -->
<body>
<jsp:useBean class="jsp.darwin.Counter" scope="session" id="myCount"/>
<h1>Counter JavaBean Demo</h1>
The counter is now <jsp:getProperty name="myCount" property="count"/>.
<% myCount.incr(); %>
</body>
</html>
