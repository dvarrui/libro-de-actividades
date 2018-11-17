<!-- 
 Login bean usage.
 $Id: login.jsp,v 1.3 2004/09/10 01:53:51 ian Exp $
 -->
<jsp:useBean id="pwck" class="jsp.darwin.PasswordChecker"/>
<jsp:setProperty name="pwck" property="*"/>
<% if (pwck.isValidPassword()) out.println("You are logged in");
 else out.println("Login name and/or password incorrect.");
 %>
<br>
