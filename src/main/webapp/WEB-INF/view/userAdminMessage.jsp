<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.detatilVO" %>
<%@ page import="sell.pojo.Message" %>
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
            <h2 class="sub-header">我的留言列表</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>提问时间</th>
                        <th>提问内容</th>
                        <th>回复时间</th>
                        <th>回复内容</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Message> messages = (List<Message>) request.getAttribute("messageList");
                        if (messages != null && messages.size() != 0) {
                            for (Message message : messages) {
                    %>
                    <td><%=message.getmTitle()%></td>
                    <td><%=message.getqTime()%></td>
                    <td><%=message.getmQuestion()%></td>
                    <td><%=message.getaTime()%></td>
                    <td><%=message.getmAnswer()%></td>
                    <td><a href="/xx?<%=message.getmId()%>"></a></td>



                    <% }
                        }%>

                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

