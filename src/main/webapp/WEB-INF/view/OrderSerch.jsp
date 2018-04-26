<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.detatilVO" %>
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

            <form method="post" action="/adminOrder">
                请输入订单号： <input name="orderNo" type="text"/> <button type="submit">查询</button>
            </form>


            <h2 class="sub-header">查询结果</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>订单号</th>
                        <th>航班号</th>
                    </tr>
                    </thead>
                    <tbody>

                        <%

                      FightOrderVO fightOrderVO= (FightOrderVO)request.getAttribute("fightOrderVO");

                       if (fightOrderVO!=null){

                               %>
                        <tr>
                        <td><%=fightOrderVO.getOrderNo()%></td>
                        <td><%=fightOrderVO.getFightNo()%></td>
                        </tr>
                        <tr>
                            <th>起始地</th>
                            <th>起飞时间</th>
                            <th>目的地</th>
                            <th>到达时间</th>
                            <th>航空公司</th>
                            <th>航班类型</th>
                            <th>票价</th>
                            <th>乘机人</th>
                            <th>退票</th>
                            <th>改签</th>
                        </tr>

                        <%
                            for(detatilVO detatilVO:fightOrderVO.getDetatilVOS()){

                                %>
                        <tr>
                        <td><%=detatilVO.getDeparture()%></td>
                        <td><%=detatilVO.getDepartureTime()%></td>
                        <td><%=detatilVO.getDestination()%></td>
                        <td><%=detatilVO.getDestinationTime()%></td>
                        <td><%=detatilVO.getFightCompany()%></td>
                        <td><%=detatilVO.getFightType()%></td>
                        <td><%=detatilVO.getFightPay()%></td>
                        <td><%=detatilVO.getUserName()%></td>
                        <td><a href="/refund?detailId=<%=detatilVO.getDetatilId()%>">退票</a></td>
                        <td><a href="/toChangeTicket?detailId=<%=detatilVO.getDetatilId()%>">改签</a></td>
                    </tr>
<%

                        }

                        %>



                    </tr>
                        <%


                       }

                        %>



                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>


<%@include file="layout/footer.jsp"%>
</body>
</html>

