<%@ page import="sell.pojo.Message" %>
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
        <div class="col-md-10 main">
            <form class="form-signin" id="sign" method="post" action="/DoUpdateMessage">
                <h2 class="form-signin-heading">回复留言</h2>
                <%

                    Message message=(Message)request.getAttribute("message");
                %>
                <input  type="hidden"  name="mId" value="<%=message.getmId()%>"/>
                提问标题：<p><%=message.getmTitle()%></p>
                提问内容:<p><%=message.getmQuestion()%></p>
                提问人:<p><%=message.getqUserName()%></p>
                提问时间:<p><%=message.getqTime()%></p>
                回复内容：<textarea  name="mAnswer" class="form-control" style="height: 280px">请输入内容</textarea><br/>


                <input id="btn" value="提交" type="submit"  class="btn btn-lg btn-primary btn-block"/>
            </form>



        </div>
    </div>
</div>


<%@include file="layout/footer.jsp"%>
</body>
</html>

