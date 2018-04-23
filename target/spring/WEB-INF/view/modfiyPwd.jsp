<%@ page import="sell.dao.VO.OrderQueryVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sell.pojo.FightInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.pojo.CompanyInfo" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>


    <title>密码修改</title>

    <%@include file="layout/header.jsp"%>


</head>

<body>

<div class="container">
    <%@include file="layout/admin_nav.jsp"%>
<div class="row">

    <div class="col-lg-6 col-lg-offset-3">
    <form class="form-signin" id="sign" method="post" action="#">
        <h2 class="form-signin-heading">密码修改</h2>
        请输入原密码：<input  id="pwd" class="form-control"/><br/>
        请输入新密码:<input id="newPwd" class="form-control"/><br/>
        再次输入新密码：<input  id="newRePwd" class="form-control" onblur="valid()"/><br/>
<script>
    function valid() {
        if ($("#newPwd").val()!=$("#newRePwd").val()){
            $("#btn").addClass("disabled");

            alert("两次新密码不一致,请重新输入");
        }else{
            $("#btn").removeClass("disabled");
            $("#btn").attr("type","submit");
        }
    }

</script>


        <button id="btn" class="btn btn-lg btn-primary btn-block disabled" >提交查询</button>
    </form>
    </div>

</div>

</div>
<%@include file="layout/footer.jsp"%>
</body>
</html>

