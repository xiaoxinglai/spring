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
            <form class="form-signin" id="sign" method="post" action="/user/userChangePwd">
                <h2 class="form-signin-heading">密码修改</h2>
                请输入原密码：<input name="pwd" id="pwd" class="form-control"/><br/>
                请输入新密码:<input  name="newPwd" id="newPwd" class="form-control"/><br/>
                再次输入新密码：<input  name="newRePwd" id="newRePwd" class="form-control" onblur="valid()"/><br/>
                <script>
                    function valid() {
                        if ($.trim($("#newPwd").val())!=$.trim($("#newRePwd").val())&&$.trim($("#newPwd").val())!=null&&$.trim($("#newRePwd").val())!=null){
                            $("#btn").attr("type","hidden");

                            alert("两次新密码不一致,请重新输入");
                        }else{

                            $("#btn").attr("type","submit");
                        }
                    }

                </script>


                <input id="btn" value="提交" type="hidden"  class="btn btn-lg btn-primary btn-block"/>
            </form>



        </div>
    </div>
</div>


<%@include file="layout/footer.jsp"%>
</body>
</html>

