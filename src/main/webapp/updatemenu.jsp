<%--
  Created by IntelliJ IDEA.
  User: 86187
  Date: 2023-12-17
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>修改菜品</title>
  <link rel="stylesheet" href="./css/mdui.css">
  <link rel="stylesheet" href="./css/table.css">
</head>
<body>
  <jsp:include page="head.jsp"/>

  <div class="table"  id="modifyGoods" style="width:fit-content;height: fit-content">
    <div class="mdui-dialog-title">修改菜品</div>
    <div class="mdui-dialog-content">

      <div class="mdui-textfield">
        <label for="img_modify" class="mdui-textfield-label">菜品图片</label>
        <div id="imagePreview" class="mdui-m-t-2"></div>
        <input id="img_modify" value="${requestScope.goods.image}" name="img" class="mdui-textfield-input" type="file" onchange="displayImage(this)"/>
      </div>

      <div class="mdui-textfield">
        <label for="name_modify" class="mdui-textfield-label">菜品名称</label>
        <input id="name_modify" value="${requestScope.goods.name}" name="name" class="mdui-textfield-input" type="text"/>
      </div>
      <div class="mdui-textfield">
        <label for="price_modify" class="mdui-textfield-label">菜品价格</label>
        <input id="price_modify" value="${requestScope.goods.price}" name="price" class="mdui-textfield-input" type="text"/>
      </div>
      <div class="mdui-textfield">
        <label for="description_modify" class="mdui-textfield-label">菜品描述</label>
        <input id="description_modify" value="${requestScope.goods.description}" name="description" class="mdui-textfield-input" type="text"/>
      </div>
    </div>
    <div class="mdui-dialog-actions">
      <button class="mdui-btn mdui-ripple" mdui-dialog-close>取消</button>
      <button onclick="changeGoods('name_modify','price_modify','description_modify')" class="mdui-btn mdui-ripple" mdui-dialog-confirm>修改
      </button>
    </div>
  </div>
</body>
<script type="text/javascript" src="./js/updateMenu.js"></script>
</html>
