<%--
  Created by IntelliJ IDEA.
  User: 86187
  Date: 2023-12-17
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="head.jsp"/>

<div style="width: 90vw; padding: 1vw; border: 1px solid #ddd; margin: 0 auto" id="addGoods" >
  <div class="mdui-dialog-title">添加菜品</div>
  <div class="mdui-dialog-content">

    <div class="mdui-textfield">
      <label for="photo" class="mdui-textfield-label">菜品图片</label>
      <div id="imagePreview" class="mdui-m-t-2"></div>
      <input id="photo"  name="img" class="mdui-textfield-input" type="file" onchange="displayImage(this)"/>
    </div>
    <div class="mdui-textfield">
      <label for="name" class="mdui-textfield-label">菜品名称</label>
      <input id="name" name="name" class="mdui-textfield-input" type="text"/>
    </div>
    <div class="mdui-textfield">
      <label for="price" class="mdui-textfield-label">菜品价格</label>
      <input id="price" name="price" class="mdui-textfield-input" type="text"/>
    </div>
    <div class="mdui-textfield">
      <label for="description" class="mdui-textfield-label">菜品描述</label>
      <input id="description" name="description" class="mdui-textfield-input" type="text"/>
    </div>
  </div>
  <div class="mdui-dialog-actions">
    <button class="mdui-btn mdui-ripple" onclick="goback()" mdui-dialog-close>取消</button>
    <button class="mdui-btn mdui-ripple"
            onclick="addGoods('name','price','description','photo')" mdui-dialog-confirm>添加
    </button>
  </div>
</div>

</body>
<script type="text/javascript" src="./js/addMenu.js"></script>
</html>
