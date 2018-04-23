<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.detatilVO" %>
<%@ page import="sell.dao.VO.RelationUser" %>
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
            <h2 class="sub-header">我的联系人列表</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>身份证号</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<RelationUser> relationUsers = (List<RelationUser>) request.getAttribute("relationUsers");
                        for (RelationUser relationUser : relationUsers) {
                    %>

                    <tr>
                        <td><%=relationUser.getName()%></td>
                        <td><%=relationUser.getIdentity()%></td>
                        <td><a onclick="deleteUser(<%=relationUser.getUid()%>)">删除</a></td>
                    </tr>
                    <%
                        }
                    %>
                    <script>
                        function deleteUser(uid){
                            $.ajax({
                                url: "/user/DelRelation?RUID="+uid,
                                type: "get",
                                success: function (data) {
                                    if (data==1) {
                                        alert("删除成功")
                                        window.location.reload();
                                    }
                                    
                                    if (data==-2) {
                                        alert("请登录")
                                    }
                                    if (data==-1){
                                        alert("删除失败，请重试");
                                    }

                                }});
                        }



                    </script>
                    </tbody>
                </table>

            </div>
            添加联系人:
            请输入姓名：<input id="name" />请输入身份证：<input id="Identity" /><button onclick="addRalation()">提交</button>
        </div>
        <script>

            function addRalation(){

              var name=$.trim($("#name").val());
              var Identity=$.trim($("#Identity").val());
              if (name==null||name==""||Identity==null||Identity==""){
                  alert("不能留空，请填好姓名和身份证号");

              }else{
                  $.ajax({
                      url: "/user/addRelation?name="+name+"&Identity="+Identity,
                      type: "get",
                      success: function (data) {
                          if (data==1) {
                              alert("添加成功")
                              window.location.reload();
                          }

                          if (data==-2) {
                              alert("请登录")
                          }
                          if (data==-1){
                              alert("添加失败，请重试");
                          }

                          if (data==-3){
                              alert("添加失败，没有查找到该人信息");
                          }
                          if (data==-5){
                              alert("该人已经是你的联系人，请不要重复添加");
                          }

                          window.location.reload();
                      }});

              }

            }
        </script>
    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

