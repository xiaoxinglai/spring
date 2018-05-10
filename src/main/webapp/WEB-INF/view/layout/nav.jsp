<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12 column">
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="/index">首页</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active">
                <a href="/user/center ">个人中心</a>
            </li>

            <li>
                <a href="/QueryOrder">航班查询</a>
            </li>




        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
               <a id="cityName"></a>
            </li>
            <li>
                <a id="city"></a>
            </li>
            <li>
                <a href="/login">管理员登陆</a>
            </li>
            <li>
                <a href="/user/loginOut">注销登陆</a>
            </li>

        </ul>
    </div>
<script>
    /**
     * 定位当前城市
     */

        $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(_result) {
            if (remote_ip_info.ret == '1') {
                cityName = remote_ip_info.province;
                city=remote_ip_info.city;
                $("#cityName").html(cityName);
                $("#city").html(city);
            }
        });

</script>
</nav>
</div>