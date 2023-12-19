<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="./css/mdui.css">
    <script src="./js/mdui.js"></script>
    <link rel="stylesheet" type="text/css" href="css/default.css">
</head>
<body>

<html>
<body class="mdui-appbar-with-toolbar mdui-appbar-with-tab">
<div class="mdui-appbar mdui-appbar-fixed">
    <div class="mdui-toolbar mdui-color-indigo">
        <a href="javascript:void(0)" class="mdui-typo-title">外卖管理系统</a>
        <div class="mdui-toolbar-spacer"></div>
            <a  href="Logout" class="mdui-btn mdui-btn-icon"><i
                    class="mdui-icon material-icons">&#xe879;</i></a>
    </div>
        <div class="mdui-color-indigo" style="text-align: right">
            <a href="/showAllGoods" class="mdui-btn">菜品管理</a>
            <a href="/ManageOrders" class="mdui-btn">订单管理</a>
            <a href="modifyAdmin.jsp" class="mdui-btn">修改密码</a>
        </div>
</div>
</body>
</html>
