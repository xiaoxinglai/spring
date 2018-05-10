<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.detatilVO" %>
<%@ page import="sell.dao.VO.RelationUser" %>
<%@ page import="sell.pojo.CompanyInfo" %>
<%@ page import="sell.dao.VO.FightInfoVO" %>
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
        <div class="col-md-10 column">
            <h2 class="sub-header">管理员信息</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>管理员姓名</th>
                        <th>管理员账号</th>
                        <th>管理员密码</th>
                        <th>管理员邮箱</th>
                        <th>管理员电话</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<User> userList=(List<User>)request.getAttribute("AdminUserList");

                        if (userList!=null&&userList.size()!=0){
                            for (User user : userList) {



                            %>
                    <tr>
                    <td><%=user.getUserName()%></td>
                    <td><%=user.getuNo()%></td>
                    <td><%=user.getPassword()%></td>
                    <td><%=user.getEmail()%></td>
                    <td><%=user.getPhone()%></td>
                        <td><a onclick="deleteUser(<%=user.getuId()%>)">删除</a></td>
                    </tr>
                    <%
                            }

                        }
                    %>


                    </tbody>
                    <script>
                        function deleteUser(id) {
                            $.ajax({
                                url: "/user/delUser?id=" + id,
                                type: "get",
                                success: function (data) {
                                    if (data == 1) {
                                        alert("删除成功")
                                        window.location.reload();
                                    }

                                    if (data == -2) {
                                        alert("请登录")
                                    }
                                    if (data == -1) {
                                        alert("删除失败，请重试");
                                    }

                                }
                            });
                        }


                    </script>
                </table>

            </div>

        </div>


    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

