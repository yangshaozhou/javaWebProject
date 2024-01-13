<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>菜品管理</title>
  <link rel="stylesheet" href="./css/menu.css">
  <link rel="stylesheet" href="./css/mdui.css">
</head>

<body>
  <jsp:include page="head.jsp"/>
  <br>
  <form class="form">
    <div class="formDiv">
      <input value="${not empty searchName ? searchName : ''}"  type="text" class="user" id="user" placeholder="请输入菜品名">
      <span class="hint" > </span>
      <button  class="checkBtn" id="checkBtn">搜索</button>
    </div>

    <div>
      <button  class="checkBtn" id="addButton">
        <a style="text-decoration: none; color: #fff" href="addmenu.jsp">添加</a>
      </button>
    </div>
  </form>

  <div style="width: 90vw; margin: 0 auto; margin-top: 4%" class="mdui-table-fluid table">
    <table  class="mdui-table mdui-table-hoverable table"  width="100%" id="recruit">
      <thead>
      <tr>
        <th>封面</th>
        <th>菜名</th>
        <th>价格</th>
        <th>菜品描述</th>
      </tr>
      </thead>

      <tbody >
      <c:forEach var="goods" items="${requestScope.page.list}">
        <tr class="tableStyle">
          <td><img style="width: 10vw" src="upload/${goods.image}?v=${System.currentTimeMillis()}" alt="${goods.image}"></td>
          <td>${goods.name}</td>
          <td>${goods.price }</td>
          <td>${goods.description}</td>
          <td>
            <button class="custom-btn btn-16"> <a style="text-decoration: none" href="/queryGoods?id=${goods.id}">修改信息</a></button>
            <button onclick="deleteGoods(${goods.id})" class="custom-btn btn-16">删除</button>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>



  <div  class="mdui-card" style="text-align: center;width: fit-content;margin: 0 auto;padding: 5px; margin-top: 2vh">
    <c:choose>
      <c:when test="${requestScope.page.currentPageNum!=1}">
        <a class="mdui-btn" href="showAllGoods?pageNum=1">第一页</a>
      </c:when>
      <c:otherwise>
        <a class="mdui-btn" href="showAllGoods?pageNum=1" disabled>第一页</a>
      </c:otherwise>
    </c:choose>

    <c:choose>
      <c:when test="${requestScope.page.currentPageNum!=1}">
        <a class="mdui-btn"
           href="showAllGoods?pageNum=${requestScope.page.currentPageNum-1}">上一页</a>
      </c:when>
      <c:otherwise>
        <a class="mdui-btn" href="showAllGoods?pageNum=${requestScope.page.currentPageNum-1}"
           disabled>上一页</a>
      </c:otherwise>
    </c:choose>
    <a href="javascript:void(0)"
       class="mdui-btn">${requestScope.page.currentPageNum}/${requestScope.page.allPageNum}页</a>
    <c:choose>
      <c:when test="${requestScope.page.currentPageNum != requestScope.page.allPageNum}">
        <a class="mdui-btn"
           href="showAllGoods?pageNum=${requestScope.page.currentPageNum+1}">下一页</a>
      </c:when>
      <c:otherwise>
        <a class="mdui-btn" href="showAllGoods?pageNum=${requestScope.page.currentPageNum+1}"
           disabled>下一页</a>
      </c:otherwise>
    </c:choose>

    <c:choose>
      <c:when test="${requestScope.page.currentPageNum != requestScope.page.allPageNum}">
        <a class="mdui-btn" href="showAllGoods?pageNum=${requestScope.page.allPageNum}">最后一页</a>
      </c:when>
      <c:otherwise>
        <a class="mdui-btn" href="showAllGoods?pageNum=${requestScope.page.allPageNum}&search=${param.search}"
           disabled>最后一页</a>
      </c:otherwise>
    </c:choose>


  </div>
</body>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript" src="./js/deleteGoods.js"></script>
</html>
