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
            <h2 class="sub-header">公司列表</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>公司名</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<CompanyInfo> companyInfos = (List<CompanyInfo>) request.getAttribute("companyInfos");

                        if (companyInfos != null && companyInfos.size() != 0) {
                            for (CompanyInfo companyInfo : companyInfos) {
                    %>

                    <tr>
                        <td><%=companyInfo.getCompanyId()%>
                        </td>
                        <td><%=companyInfo.getCompanyName()%>
                        </td>
                        <td><a onclick="deleteUser(<%=companyInfo.getCompanyId()%>)">删除</a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <script>
                        function deleteUser(id) {
                            $.ajax({
                                url: "/Company/delCompany?CompanyId=" + id,
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
                    </tbody>
                </table>

            </div>
            添加公司:
            请输入名字：<input id="name" type="text"/>
            <button onclick="addRalation()">提交</button>
        </div>
        <script>

            function addRalation() {

                var name = $.trim($("#name").val());
                if (name == null || name == "") {
                    alert("不能留空，请填公司名字");

                } else {
                    $.ajax({
                        url: "/Company/addCompany?name=" + name,
                        type: "get",
                        success: function (data) {
                            if (data == 1) {
                                alert("添加成功")
                                window.location.reload();
                            }

                            if (data == -2) {
                                alert("请登录")
                            }
                            if (data == -1) {
                                alert("添加失败，请重试");
                            }


                            window.location.reload();
                        }
                    });

                }

            }
        </script>
    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

