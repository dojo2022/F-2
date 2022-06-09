<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いもケツ</title>
<link rel="stylesheet" type="text/css" href="/imoketu/css/tasklist.css">
</head>
<body>

<ul>
 	<li><a class="active" href="#home">Home</a></li>
 	<li><a href="#tasklist">タスク確認</a></li>
 	<li><a href="#taskadd">タスク追加</a></li>
 	<li><a href="#about">エクストラモード・about</a></li>
</ul>
<div id="table">
  <table id="list">
    <tr class="columnitem">
      <div ><th>タスク名</th><th>期限</th><th>状態</th><th>状態操作</th></div>
    </tr>
<c:forEach var="e" items="${taskList}" >
    <tr class="data_row"> <td>${e.name}</td><td>${e.Limit}</td><td>${e.state}</td><td> <input type="submit" id="start${e.id}" name="submit" value="着手"><input type="submit" id="complete${e.id}" name="submit" value="完了"><input type="submit" id="delete${e.id}" name="submit" value="削除"></td></tr>
</c:forEach>
  </table>
  </div>
<script></script>
</body>
</html>