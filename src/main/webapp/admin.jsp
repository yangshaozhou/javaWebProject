<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <link rel="stylesheet" href="css/login.css">
</head>

<body>
<div class="login-title">外卖管理系统</div>

<div class="container">
    <div class="login-box">
        <form action="Login" method="post">
            <div class="apple-btn login-apple">
                <span class="red-btn"></span>
                <span class="yellow-btn"></span>
                <span class="green-btn"></span>
            </div>
            <div class="title">Login</div>
            <div class="input">
                <label for="login-user">
                </label><input type="text" name="username" id="login-user" placeholder="请输入用户名">
            </div>
            <div class="input">
                <label for="login-password">
                </label><input type="password" name="password" id="login-password" placeholder="请输入密码">
            </div>
            <div class="btn login-btn">
                <input type="submit" value="登录">
            </div>
            <div class="change-box login-change">
                <div class="change-btn toSign">
                    <span>去注册</span>
                </div>
            </div>
        </form>
    </div>

    <div class="sign-box">
        <form action="Register" method="post">

            <div class="apple-btn sign-apple">
                <span class="red-btn"></span>
                <span class="yellow-btn"></span>
                <span class="green-btn"></span>
            </div>
            <div class="title">Sign</div>
            <div class="input">
                <label for="sign-user">
                </label><input type="text" name="username" id="sign-user" placeholder="你还没有一个用户名吗">
            </div>
            <div class="input">
                <label for="sign-password">
                </label><input type="password" name="password" id="sign-password" placeholder="快输入密码">
            </div>
            <div class="btn sign-btn">
                <input type="submit" value="注册">
            </div>
            <div class="change-box sign-change">
                <div class="change-btn toLogin">
                    <span>去登陆</span>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="js/login.js"></script>

</body>
</html>
