<%@ page import="sell.dao.VO.OrderVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sell.pojo.User" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>


    <title>用户订票</title>

    <%@include file="layout/header.jsp"%>


</head>

<body>

<div class="container">
    <%@include file="layout/nav.jsp"%>
<div class="row">
    <%
        OrderVO orderVO=(OrderVO)request.getAttribute("orderVO");
    %>
    <div class="col-lg-6 col-lg-offset-3">
    <form class="form-signin" id="sign" method="post" action="/order">
        <h2 class="form-signin-heading">机票预定</h2>
        <input type="hidden" value="<%=orderVO.getFightId()%>" name="fightId"/>
        <p>预定航班号：<%=orderVO.getFightNo()%><p/>
        <p>飞机票价格：<span id="cost"><%=orderVO.getFightPay()%></span></p>
        <p>订票人姓名：<%=orderVO.getUser().getUserName()%></p>
        <p>订票人性别：<%=orderVO.getSex()%></p>
        <p>身份证号码：<%=orderVO.getIdentity()%></p>
        <p>联系电话：<%=orderVO.getPhone()%></p>
        选择乘机人：
        <select  id="sele" class="form-control" onchange="changeSelect(this.options[this.options.selectedIndex].id,this.options[this.options.selectedIndex].value)">
            <option selected ></option>
            <option id="<%=orderVO.getUser().getuNo()%>" value="<%=orderVO.getUser().getuId()%>"><%=orderVO.getUser().getUserName()%></option>

            <%
                if(orderVO.getUsers()!=null&&orderVO.getUsers().size()!=0){
                    for (User us:orderVO.getUsers()){
            %>
            <option id="<%=us.getuNo()%>" value="<%=us.getuId()%>"><%=us.getUserName()%></option>
            <%
                } }
            %>


        </select>
        <div id="person">

        </div>
        <div id="total"></div>


        <button class="btn btn-lg btn-primary btn-block" type="submit">提交订单</button>
    </form>
    </div>

</div>
    <script>
        var num=0;

        function changeSelect(optionId,value){
            var oid='#'+optionId;
            var userName=$(oid).text();

            num++;

            $("#person").append('<p id=\"'+num+'\">'+"选择的乘机人:"+userName+'<input name=\"uId\" type=\"hidden\" value="'+value+'" /><button type="button" onclick=\"removePerson('+num+','+value+',\''+optionId+'\',\''+userName+'\')\">'+"删除"+'</button></p>');
            $(oid).remove();
            $("#total").html('<p>'+"当前机票数量："+num+"总价："+$("#cost").text()*num+'</p>');


        }

        function removePerson(id,value,optionId,name){
            var label='#'+id;

            $("#sele").append('<option id=\"'+optionId+'\" value=\"'+value+'\">'+ name+'</option>');
            $(label).remove();
            num--;
            $("#total").html('<p>'+"当前机票数量："+num+"总价："+$("#cost").text()*num+'</p>');
        }


    </script>


</div>
<%@include file="layout/footer.jsp"%>
</body>
</html>

