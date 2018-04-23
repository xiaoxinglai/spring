<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.detatilVO" %>
<%@ page import="sell.pojo.Message" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
                    <tbody>
                    <%
                        SimpleDateFormat format = new SimpleDateFormat("YYYYMMDD:hh:mm:ss");
                        List<Message> messages = (List<Message>) request.getAttribute("messageList");
                        if (messages != null && messages.size() != 0) {
                            for (Message message : messages) {


                                String aTime;
                                String answer;
                                String aName;
                                if (message.getaTime() != null) {
                                    aTime = format.format(message.getaTime());
                                    answer = message.getmAnswer();
                                    aName=message.getaUserName();
                                } else {
                                    answer = "未回复";
                                    aTime = "未回复";
                                    aName="未回复";
                                }


                    %>
                    <tr>

                        <p>标题:<%=message.getmTitle()%> &nbsp;发布时间：<%=format.format(message.getqTime())%><a href="/delMessage?mId=<%=message.getmId()%>">删除</a></p>

                        提问内容：<p><%=message.getmQuestion()%></p>
                       回复内容：<p><%=answer%></p>
                        <p>回复人：<%=aName%>&nbsp;回复时间：<%=aTime%></p>

                        <hr/>
                        <br/>
                        <br/>
                    </tr>


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

