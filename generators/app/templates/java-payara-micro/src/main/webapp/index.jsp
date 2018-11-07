<%--
  Created by IntelliJ IDEA.
  User: mak
  Date: 11/6/18
  Time: 01:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Payara Micro</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/material-design-icons/3.0.1/material-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/materializecss/1.0.0/css/materialize.min.css">
  <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/favicon.ico">
</head>
<body>
<div id="app"></div>
<script src="${pageContext.request.contextPath}/webjars/materializecss/1.0.0/js/materialize.min.js"></script>
<script>M.AutoInit(document.body);</script>
<script>
  (function ready() {
    document.addEventListener('DOMContentLoaded', function (evt) {
      fetch('/v1')
        .then(function (data) {
          return data.text();
        })
        .then(function (text) {
          document.querySelector('#app').textContent = text;
        });
    }, false);
  }());
</script>
</body>
</html>
