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
            <h2 class="sub-header">添加航班</h2>
            <%
                List<CompanyInfo> companyInfos=(List<CompanyInfo>)request.getAttribute("companyInfos");
            %>

            <form method="post" action="/fight/addFight">
                航班编号：<input name="fightNo"/> </br>
                出发地：<input name="departure"/><br>
                目的地：<input name="destination"/><br>
                起飞时间：<input name="departureTime"/><br>
                降落时间：<input name="destinationTime"/><br>
                航空公司：
                <select name="fightCompany">
                    <%
                        for (CompanyInfo companyInfo : companyInfos) {
                            %>
                    <option value="<%=companyInfo.getCompanyId()%>"><%=companyInfo.getCompanyName()%></option>
                    <%
                        }
                    %>
                </select><br>
                航班型号：<input name="fightType"/><br>
                票价：<input name="fightPay"/><br>
                余票：<input name="fightNum"/><br>
                票类型：<select name="ticketType">
                <option value="1">普通票</option>
                <option value="0">特价票</option>
            </select>
                <button class="button" type="submit">提交</button>
            </form>

        </div>

    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

