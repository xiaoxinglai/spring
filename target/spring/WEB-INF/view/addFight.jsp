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
            <h2 class="sub-header text-center">添加航班</h2>
            <%
                List<CompanyInfo> companyInfos=(List<CompanyInfo>)request.getAttribute("companyInfos");
            %>

            <form method="post" action="/fight/addFight">
                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large">航班编号：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="fightNo" class="form-control"/>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large">出发地：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="departure" class="form-control"/>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large"> 目的地：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="destination" class="form-control"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large"> 起飞时间：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="departureTime" class="form-control"/>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large"> 降落时间：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="destinationTime" class="form-control"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-4">
                        <p  class="pull-right" style="font-size: large"> 航空公司：</p>
                    </div>
                    <div class="col-xs-8">
                        <select name="fightCompany" class="form-control">
                            <%
                                for (CompanyInfo companyInfo : companyInfos) {
                            %>
                            <option value="<%=companyInfo.getCompanyId()%>"><%=companyInfo.getCompanyName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large"> 航班型号：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="fightType" class="form-control"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large">  票价：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="fightPay" class="form-control"/>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-4">
                        <p class="pull-right" style="font-size: large">   余票：</p>
                    </div>
                    <div class="col-xs-8">
                        <input name="fightNum" class="form-control"/>
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-4">
                        <p  class="pull-right" style="font-size: large"> 票类型：</p>
                    </div>
                    <div class="col-xs-8">
                        <select name="ticketType" class="form-control">
                            <option value="1">普通票</option>
                            <option value="0">特价票</option>
                        </select>
                        <button class="btn btn-info" type="submit" >提交</button>
                    </div>
                </div>


            </form>

        </div>

    </div>
</div>


<%@include file="layout/footer.jsp" %>
</body>
</html>

