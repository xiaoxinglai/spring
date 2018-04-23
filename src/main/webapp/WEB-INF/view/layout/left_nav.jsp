<%@ page import="sell.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-2 sidebar">
    <%

       User admin= (User)request.getAttribute("admin");
       if (admin!=null){
            if (admin.getPower().equals(2)){
                %>

    <ul class="nav nav-sidebar">
        <li class="active"><a href="/user/AdminCenter">订单管理 <span class="sr-only">(current)</span></a></li>
        <li><a href="/user/relation">航空公司</a></li>
        <li><a href="/user/userPwd">修改密码</a></li>
        <li><a href="/toAddMessage">留言发布</a></li>
        <li><a href="/toMessageList">留言管理</a></li>
    </ul>

    <%
            }
       }
    %>
    <ul class="nav nav-sidebar">
        <li class="active"><a href="/user/center">订单管理 <span class="sr-only">(current)</span></a></li>
        <li><a href="/user/relation">联系人管理</a></li>
        <li><a href="/user/userPwd">修改密码</a></li>
        <li><a href="/toAddMessage">留言发布</a></li>
        <li><a href="/toMessageList">留言管理</a></li>
    </ul>

</div>