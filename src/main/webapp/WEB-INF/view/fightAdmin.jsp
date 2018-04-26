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
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<FightInfoVO> fightInfoVOS=(List<FightInfoVO>)request.getAttribute("fightInfoVOList");

                        if (fightInfoVOS!=null&&fightInfoVOS.size()!=0){
                            for (FightInfoVO fightInfoVO : fightInfoVOS) {



                            %>
                    <tr>
                    <td><%=fightInfoVO.getFightNo()%></td>
                    <td><%=fightInfoVO.getDeparture()%></td>
                    <td><%=fightInfoVO.getDestination()%></td>
                    <td><%=fightInfoVO.getDepartureTime()%></td>
                    <td><%=fightInfoVO.getDestinationTime()%></td>
                    <td><%=fightInfoVO.getFightCompany()%></td>
                    <td><%=fightInfoVO.getFightType()%></td>
                    <td><%=fightInfoVO.getFightPay()%></td>
                    <td><%=fightInfoVO.getFightNum()%></td>
                    <td><%=fightInfoVO.getTicketType()%></td>
                    <td><a href="/fight/delFight?id=<%=fightInfoVO.getFightId()%>">删除</a></td>
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


<%@include file="layout/footer.jsp" %>
</body>
</html>

