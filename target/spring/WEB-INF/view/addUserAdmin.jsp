<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.detatilVO" %>
<%@ page import="sell.dao.VO.RelationUser" %>
<%@ page import="sell.pojo.CompanyInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>个人中心</title>

    <!-- Bootstrap core CSS -->
    <%@include file="layout/header.jsp" %>

</head>

<body>
<%@include file="layout/admin_nav.jsp" %>

<div class="container">
    <div class="row">
        <%@include file="layout/left_nav.jsp" %>
        <div class="col-md-10 main">
            <h2 class="sub-header">管理员添加</h2>

            <form method="post" action="/user/addUserAdminInfo">
                管理员姓名：<input name="userName"/><br>
                管理员密码：<input name="password"/> </br>
                管理员账号：<input name="uNo"/><br>
                管理员邮箱：<input name="email"/><br>
                管理员电话：<input name="phone"/><br>
                <button class="button" type="submit">提交</button>
            </form>

        </div>

    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

