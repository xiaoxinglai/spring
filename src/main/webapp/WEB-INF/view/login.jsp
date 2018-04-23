<%--
  Created by IntelliJ IDEA.
  User: user12
  Date: 2018/4/12
  Time: 下午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>


    <title>登陆</title>

    <%@ include file="layout/header.jsp"%>

</head>

<body>

<div class="container">
    <%@ include  file="layout/nav.jsp"%>
<div class="row">
    <div class="col-lg-4 col-lg-offset-4">
        <form class="form-signin" method="post" action="/user/doLogin">
            <h6 class="form-signin-heading">Please sign in</h6>
            <label for="no" class="sr-only">账号</label>
            <input  id="no" name="uNo" class="form-control" placeholder="请输入账号" required autofocus>
            &nbsp;
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password"  name="password" id="inputPassword" class="form-control" placeholder="Password">
            &nbsp;
            <button class="btn btn-sm btn-primary btn-block" type="submit">登陆</button>&nbsp;
            <a class="btn btn-sm btn-primary btn-block" href="/sign">注册</a>
        </form>
    </div>
</div> <!-- /container -->

</div>

</body>
</html>

