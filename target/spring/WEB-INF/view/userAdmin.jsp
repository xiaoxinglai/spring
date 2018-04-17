<%@ page import="sell.dao.VO.FightOrderVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
  <%@include file="layout/header.jsp"%>

</head>

<body>
<%@include file="layout/admin_nav.jsp"%>

<div class="container">
    <div class="row">
        <%@include file="layout/left_nav.jsp"%>
        <div class="col-md-10 main">
            <h2 class="sub-header">我的订单列表</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>订单号</th>
                        <th>航班号</th>
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
                    </thead>
                    <tbody>

                        <%

                       List<FightOrderVO> fightOrderVOS= ( List<FightOrderVO>)request.getAttribute("fightOrderVO");

                       if (fightOrderVOS!=null&&fightOrderVOS.size()!=0){

                           for (FightOrderVO fightOrderVO : fightOrderVOS) {

                               %>
                        <tr>
                        <td><%=fightOrderVO.getOrderNo()%></td>
                        <td><%=fightOrderVO.getFightNo()%></td>
                        <td><%=fightOrderVO.getDeparture()%></td>
                        <td><%=fightOrderVO.getDepartureTime()%></td>
                        <td><%=fightOrderVO.getDestination()%></td>
                        <td><%=fightOrderVO.getDestinationTime()%></td>
                        <td><%=fightOrderVO.getFightCompany()%></td>
                        <td><%=fightOrderVO.getFightType()%></td>
                        <td><%=fightOrderVO.getFightPay()%></td>
                        <td><%=fightOrderVO.getUserName()%></td>
                        <td><a href="xxxx?fightId=<%=fightOrderVO.getFightId()%>">退票</a></td>
                        <td><a href="xxxx?fightId=<%=fightOrderVO.getFightId()%>">改签</a></td>
                    </tr>
                        <%

                           }



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

