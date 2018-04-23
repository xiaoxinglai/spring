<%--
  Created by IntelliJ IDEA.
  User: user12
  Date: 2018/4/20
  Time: 下午8:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    String msg=(String)request.getAttribute("msg");
%>

<%=msg%>
</body>
</html>
