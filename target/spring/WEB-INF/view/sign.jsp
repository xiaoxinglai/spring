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


    <title>订票系统用户注册</title>

    <%@include file="layout/header.jsp"%>


</head>

<body>

<div class="container">
    <%@include file="layout/nav.jsp"%>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3">
    <form class="form-signin" id="sign">
        <h2 class="form-signin-heading">用户注册</h2>
        <label for="uno" class="sr-only">用户账号</label>
        <input name="uNo"  id="uno" onblur="onblurNo()" class="form-control" placeholder="请设置账号" required autofocus>
        <p id="tip"></p>
        <br>
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="请设置密码" required>
        <br>
        <label for="username" class="sr-only">姓名</label>
        <input name="userName"  id="username" class="form-control" placeholder="输入您的真实姓名" required>
        <br>

        <select name="sex"  class="form-control">
                    <option value="1">男</option>
                    <option value="0">女</option>
        </select>


        <br>
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input  name="email"  id="inputEmail" class="form-control" placeholder="请输入邮箱" required autofocus>
        <br>
        <label for="userphone" class="sr-only">电话</label>
        <input  name="phone" type="email" id="userphone" class="form-control" placeholder="请输入联系电话" required autofocus>
        <br>
        <label for="identity" class="sr-only">身份证</label>
        <input  name="identity"  id="identity" class="form-control" placeholder="请输入身份证号" required autofocus>
        <br>
        <label for="address" class="sr-only">地址</label>
        <input  name="address"  id="address" class="form-control" placeholder="请输入地址" required autofocus>
        <br>
        <button class="btn btn-lg btn-primary btn-block disabled" type="button" id="zhuce">注册</button>
    </form>
    </div>

</div>
    <script>

        function onblurNo(){

            var  text=$("#uno").val();
            console.log(text);

            $.ajax({
                url: "/user/doValiNo?uno="+text,
                type: "get",
                dataType: "json",//预期服务器返回的数据类型
                processData: false,  // 不处理数据
                success: function (data) { //返回json结果

                    if (data==0){
                        $("#zhuce").addClass("disabled");
                        $("#zhuce").removeAttr("onclick","addForm()");
                        $("#tip").attr("style","color: red");

                        $("#tip").text("账号不能为空");
                    }else{

                        if (data==1) {
                            $("#zhuce").removeClass("disabled");
                            $("#zhuce").attr("onclick","addForm()");
                            $("#tip").attr("style","color: green");
                            $("#tip").text("该账户可以使用");
                        }else{
                            $("#zhuce").addClass("disabled");
                            $("#zhuce").removeAttr("onclick","addForm()");
                            $("#tip").attr("style","color: red");

                            $("#tip").text("该账户在数据库中已经存在");
                        }
                    }

                }
            });
        }




        function addForm(){

            var fd =  JSON.stringify($('#sign').serializeJSON());

            console.log(fd);
            $.ajax({
                url: "/user/doSign",
                type: "POST",
                dataType: "json",//预期服务器返回的数据类型
                contentType:"application/json",
                data:fd  ,  //data表示要发送的数据
                processData: false,  // 不处理数据
                success: function (data) { //返回json结果
                    if (data==1) {
                        alert("注册成功");
                        window.location.href="/index";
                    }else{
                        alert("注册失败,请重试且注意输入框不能为空");
                    }


                }
            });
        }
    </script>
    

</div>
<%@include file="layout/footer.jsp"%>
</body>
</html>

