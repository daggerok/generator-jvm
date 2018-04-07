<%@ page import="io.vavr.control.Try" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="daggerok.ejb.api.HelloService" %><%--
  Created by IntelliJ IDEA.
  User: mak
  Date: 3/19/18
  Time: 03:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
	java:global/ear-0.0.1/ejb-services-0.0.1/HelloServiceBean!daggerok.ejb.api.HelloService
	java:app/ejb-services-0.0.1/HelloServiceBean!daggerok.ejb.api.HelloService
	java:module/HelloServiceBean!daggerok.ejb.api.HelloService
	java:global/ear-0.0.1/ejb-services-0.0.1/HelloServiceBean
	java:app/ejb-services-0.0.1/HelloServiceBean
	java:module/HelloServiceBean
--%>
<%
  final HelloService helloservice = HelloService.class.cast(
      Try.of(() -> new InitialContext().lookup("java:app/ejb-services-0.0.1/HelloServiceBean"))
         .getOrElseThrow(throwable -> new RuntimeException(
             "Cannot lookup HelloServiceBean: " + throwable.getLocalizedMessage()
         ))
  );
%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form method="post" action="/servlet/api/greeter-resource">
  <input type="text" name="name" placeholder="What's your name, my friend?" />
  <input type="submit" hidden="hidden" />
</form>
<p>db: <%= helloservice.getAllGreetings() %></p>
</body>
</html>
