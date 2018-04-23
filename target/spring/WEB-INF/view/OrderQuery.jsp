<%@ page import="sell.dao.VO.OrderQueryVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sell.pojo.FightInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="sell.pojo.CompanyInfo" %>
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

    <div class="col-lg-6 col-lg-offset-3">
    <form class="form-signin" id="sign" method="post" action="/QueryOrderList">
        <h2 class="form-signin-heading">机票查询</h2>
        <%
            OrderQueryVO orderQueryVO=(OrderQueryVO)request.getAttribute("orderQueryVO");
            List<FightInfo> fightInfoList=orderQueryVO.getFightInfos();
            List<CompanyInfo> companyInfos=orderQueryVO.getCompanyInfoList();

        %>
       起始地： <select name="Departure" class="form-control">
        <option selected></option>
        <%
            for (FightInfo fightInfo : fightInfoList) {
                %>

        <option value="<%=fightInfo.getDeparture()%>"><%=fightInfo.getDeparture()%></option>


        <%
            }

        %>
        </select>

        目的地：<select name="Destination" class="form-control">
        <option selected></option>
        <%
            for (FightInfo fightInfo : fightInfoList) {
        %>

        <option value="<%=fightInfo.getDestination()%>"><%=fightInfo.getDestination()%></option>


        <%
            }

        %>
        </select>

        航空公司：
        <select  name="CompanyId" class="form-control">
            <option selected></option>
            <%
                for (CompanyInfo companyInfo : companyInfos) {
            %>

            <option value="<%=companyInfo.getCompanyId()%>"><%=companyInfo.getCompanyName()%></option>


            <%
                }

            %>
        </select>


        <button class="btn btn-lg btn-primary btn-block" type="submit">提交查询</button>
    </form>
    </div>

</div>

</div>
<%@include file="layout/footer.jsp"%>
</body>
</html>

