
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
        <jsp:include page="head.jsp"/>
        <div class="title">
                <h1>${sessionScope.admin.name}</h1>
            <h1><span style="color: crimson;">My</span> Homepage</h1>
        </div>

</body>
</html>
