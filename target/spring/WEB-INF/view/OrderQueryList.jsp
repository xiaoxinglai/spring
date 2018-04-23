
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sell.pojo.FightInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.dao.VO.FightInfoVO" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>


    <title>航班查询</title>

    <%@include file="layout/header.jsp"%>


</head>

<body>

<div class="container">
    <%@include file="layout/nav.jsp"%>
<div class="row">

    <div class="col-lg-10 col-lg-offset-1">
        <h2 class="text-center form-signin-heading">查询结果列表</h2>
<%
    List<FightInfoVO> fightInfos=(List<FightInfoVO>)request.getAttribute("fightInfos");
   if (fightInfos!=null||fightInfos.size()!=0){

       for (FightInfoVO fightInfo : fightInfos) {
%>

           <p>航班号<%=fightInfo.getFightNo()%>：
            起始地：<%=fightInfo.getDeparture()%>
               出发时间：<%=fightInfo.getDepartureTime()%>
            目的地：<%=fightInfo.getDestination()%>
               到达时间：
               <%=fightInfo.getDestinationTime()%>
               航空公司:<%=fightInfo.getFightCompany()%>
               票价<%=fightInfo.getFightPay()%>
               <a href="/toOrder?fightId=<%=fightInfo.getFightId()%>">订票</a>

           </p>




<%} }%>




    </div>

</div>

</div>
<%@include file="layout/footer.jsp"%>
</body>
</html>

