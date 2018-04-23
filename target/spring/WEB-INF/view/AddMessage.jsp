<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>个人中心</title>

    <!-- Bootstrap core CSS -->
  <%@include file="layout/header.jsp"%>

</head>

<body>
<%@include file="layout/admin_nav.jsp"%>

<div class="container">
    <div class="row">
        <%@include file="layout/left_nav.jsp"%>
        <div class="col-md-10 main">
            <form class="form-signin" id="sign" method="post" action="/doAddMessage">
                <h2 class="form-signin-heading">发布留言</h2>
                请输入标题：<input name="title"  class="form-control"/><br/>
                请输入内容:<textarea  name="question" class="form-control" style="height: 280px">请输入内容</textarea><br/>


                <input id="btn" value="提交" type="submit"  class="btn btn-lg btn-primary btn-block"/>
            </form>



        </div>
    </div>
</div>


<%@include file="layout/footer.jsp"%>
</body>
</html>

