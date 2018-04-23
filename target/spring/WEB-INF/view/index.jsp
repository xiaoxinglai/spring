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


    <title>航空订票系统</title>
<%@ include file="layout/header.jsp"%>

</head>

<body>


    <div class="container">
        <%@ include  file="layout/nav.jsp"%>
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="jumbotron">
                    <h1>
                        欢迎来到航空订票系统！
                    </h1>
                    <p>
                       在这里您可以查询航班，预定机票，退/改签等等，祝您旅途愉快！
                    </p>
                    <p>
                        <a class="btn btn-primary btn-large" href="/QueryOrder">航班查询</a>
                    </p>
                </div>
            </div>
        </div>

        <div class="row clearfix">
            <div class="col-md-10 column">
                <h2 class="sub-header">航班信息</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>航班编号</th>
                            <th>出发地</th>
                            <th>目的地</th>
                            <th>起飞时间</th>
                            <th>降落时间</th>
                            <th>航空公司</th>
                            <th>航班型号</th>
                            <th>票价</th>
                            <th>余票</th>
                            <th>票类型</th>
                            <th>订票</th>
                        </tr>
                        </thead>
                        <tbody id="tab">


                        </tbody>
                    </table>
                    <nav class="pull-right">
                        <ul class="pagination" id="pagination1"><!--分页导航 pagination  &laquo;箭头图标-->
                        </ul>
                    </nav>
                    <script src="/js/jquery.min.js"></script>
                    <script src="/js/bootstrap.min.js"></script>
                    <script type="text/javascript" src="/js/jqPaginator.js"></script>
                    <script type="text/javascript">
                        $.jqPaginator('#pagination1', {
                            totalPages: 10,
                            visiblePages: 10,
                            currentPage: 1,
                            prev: '<li class="prev"><a href="javascript:;">Previous</a></li>',
                            next: '<li class="next"><a href="javascript:;">Next</a></li>',
                            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                            /*  <![CDATA[*/
                            onPageChange: function (num, type) {
                                $.ajax({
                                    url: "/fight/ajaxList?currPage="+num,
                                    type: "get",
                                    success: function (data) {

                                        $('#pagination1').jqPaginator('option', {
                                            currentPage: data.currentPage,
                                            totalPages: data.totalCount
                                        });
                                        $("#tab").html(
                                            '<div></div>'
                                        );



                                        for (var i = 0; i < data.data.length; i++) {
                                            $("#tab").append(
                                                '<tr>' +
                                                '<td>'+data.data[i].fightNo+'</td>'+
                                                '<td>'+data.data[i].departure+'</td>'+
                                                '<td>'+data.data[i].destination+'</td>'+
                                                '<td>'+data.data[i].departureTime+'</td>'+
                                                '<td>'+data.data[i].destinationTime+'</td>'+
                                                '<td>'+data.data[i].fightCompany+'</td>'+
                                                '<td>'+data.data[i].fightType+'</td>'+
                                                '<td>'+data.data[i].fightPay+'</td>'+
                                                '<td>'+data.data[i].fightNum+'</td>'+
                                                '<td>'+data.data[i].ticketType+'</td>'+
                                                '<td><a href="/toOrder?fightId='+data.data[i].fightId+'">'+"订票"+'</a></td>'+
                                                '</tr>');

                                        }




                                    }
                                });


                            }


                        });


                    </script>
                </div>

            </div>

            <div class="col-md-2 column">
                <h5>
                    用户登陆
                </h5>
                <%
                  Object name=request.getAttribute("name");
                if (name!=null){
                        %>
                <h6>欢迎！<%=name.toString()%>先生的到来!</h6>

                <%
                    }else{
                %>
                <form class="form-signin" method="post" action="/user/doLogin">
                    <h6 class="form-signin-heading">Please sign in</h6>
                    <label for="no" class="sr-only">账号</label>
                    <input  id="no" name="uNo" class="form-control" placeholder="请输入账号" required autofocus>
                    &nbsp;
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password"  name="password" id="inputPassword" class="form-control" placeholder="Password">
                    &nbsp;
                    <button class="btn btn-sm btn-primary btn-block" type="submit">登陆</button>&nbsp;
                    <a class="btn btn-sm btn-primary btn-block" href="/sign">注册</a>
                </form>
                <%
                    }%>

            </div>
        </div>
    </div>

</body>
</html>

