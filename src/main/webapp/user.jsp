<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>用户管理</title>
  <link rel="stylesheet" href="./css/menu.css">
  <link rel="stylesheet" href="./css/mdui.css">
</head>
<body>
<jsp:include page="head.jsp"/>

<div style="width: 90vw; margin: 0 auto; margin-top: 4%" class="mdui-table-fluid table">
  <table  class="mdui-table mdui-table-hoverable table"  width="100%" id="recruit">
    <thead>
    <tr>
      <th>头像</th>
      <th>姓名</th>
      <th>地址</th>
      <th>电话号码</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="users" items="${requestScope.page.list}">
      <tr class="tableStyle">
        <td><img style="width: 10vw" src="upload/user/${users.head_Photo}?v=${System.currentTimeMillis()}" alt="${goods.image}"></td>
        <td>${users.username}</td>
        <td>${users.address }</td>
        <td>${users.phoneNumber}</td>
        <td>
          <button onclick="deleteUser('${users.username}')" class="custom-btn btn-16">删除</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>



<div  class="mdui-card" style="text-align: center;width: fit-content;margin: 0 auto;padding: 5px; margin-top: 2vh">
  <c:choose>
    <c:when test="${requestScope.page.currentPageNum!=1}">
      <a class="mdui-btn" href="showAllUser?pageNum=1">第一页</a>
    </c:when>
    <c:otherwise>
      <a class="mdui-btn" href="showAllUser?pageNum=1" disabled>第一页</a>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${requestScope.page.currentPageNum!=1}">
      <a class="mdui-btn"
         href="showAllUser?pageNum=${requestScope.page.currentPageNum-1}">上一页</a>
    </c:when>
    <c:otherwise>
      <a class="mdui-btn" href="showAllUser?pageNum=${requestScope.page.currentPageNum-1}"
         disabled>上一页</a>
    </c:otherwise>
  </c:choose>
  <a href="javascript:void(0)"
     class="mdui-btn">${requestScope.page.currentPageNum}/${requestScope.page.allPageNum}页</a>
  <c:choose>
    <c:when test="${requestScope.page.currentPageNum != requestScope.page.allPageNum}">
      <a class="mdui-btn"
         href="showAllUser?pageNum=${requestScope.page.currentPageNum+1}">下一页</a>
    </c:when>
    <c:otherwise>
      <a class="mdui-btn" href="showAllUser?pageNum=${requestScope.page.currentPageNum+1}"
         disabled>下一页</a>
    </c:otherwise>
  </c:choose>

  <c:choose>
    <c:when test="${requestScope.page.currentPageNum != requestScope.page.allPageNum}">
      <a class="mdui-btn" href="showAllUser?pageNum=${requestScope.page.allPageNum}">最后一页</a>
    </c:when>
    <c:otherwise>
      <a class="mdui-btn" href="showAllUser?pageNum=${requestScope.page.allPageNum}&search=${param.search}"
         disabled>最后一页</a>
    </c:otherwise>
  </c:choose>


</div>
</body>
<script type="text/javascript" src="./js/deleteUser.js"></script>
</html>
