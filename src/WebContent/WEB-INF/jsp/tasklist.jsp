<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous">
  </script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いもケツ</title>
<link rel="stylesheet" href="/imoketu/css/tasklist.css">
<link rel="stylesheet" href="/imoketu/css/common.css">
</head>
<body>

<div class="parent">
      <div class="menu">
        <ul class="menu_ber">
        	<li class="menu_content"><a class="active" id="clock">現在日時</a></li>
        	<li class="menu_content"><a class="menu_add" href="/imoketu/TaskListServlet">タスク確認</a></li>
        	<li class="menu_content"><a class="menu_add" href="/imoketu/TaskAddServlet">タスク追加</a></li>
        	<li class="menu_content"><a class="menu_add" href="/imoketu/ExtraServlet">エクストラモード<br>about</a></li>
          <li class="menu_content_image"><a class="menu_add">キャラクター(JSON？)</a></li>
        </ul>
      </div>
    <!-- タスク確認始まり -->

<div id="table">
  <table id="list">
    <tr class="columnitem">
      <div ><th>タスク名</th><th>期限</th><th>状態</th><th>状態操作</th></div>
    </tr>
<c:forEach var="e" items="${taskList}" >


		<c:if test="${e.stateflag == 0 }" >
		<tr class="data_row"><td>${e.taskname}</td><td>${e.tasklimit}</td><td id="state"> <input type="text" id="state_box" name="statebox" value="未着手" readonly style="lightgray"></td><td> <input type="submit" id="start${e.taskid}" name="submit" value="着手" onclick="startClick(${e.taskid});"><input type="submit" id="complete${e.taskid}" name="submit" value="完了" onclick="completeClick(${e.taskid});"><input type="submit" id="delete${e.taskid}" name="submit" value="削除" onclick="deleteClick(${e.taskid});" ></td></tr>
    </c:if>

    <c:if test="${e.stateflag == 1 }" >
    <tr class="data_row"><td>${e.taskname}</td><td>${e.tasklimit}</td><td id="state"> <input type="text" id="state_box" name="statebox" value="着手" readonly style="lightgray"></td><td> <input type="submit" id="complete${e.taskid}" name="submit" value="完了" onclick="completeClick(${e.taskid});"><input type="submit" id="delete${e.taskid}" name="submit" value="削除" onclick="deleteClick(${e.taskid});" ></td></tr>
    </c:if>

    <c:if test="${e.stateflag == 2 }" >
    <tr class="data_row"><td>${e.taskname}</td><td>${e.tasklimit}</td><td id="state"> <input type="text" id="state_box" name="statebox" value="完了" readonly style="lightgray"></td><td> <input type="submit" id="delete${e.taskid}" name="submit" value="削除" onclick="deleteClick(${e.taskid});" ></td></tr>
    </c:if>
</c:forEach>
  </table>
  </div>
</div>
<script>

function startClick(line){  //着手ボタンクリック時
	//ボタンクリック時の見た目の変更
  var p1 = $('#start'+line).parents('.data_row');
  var p2 = $(p1).find("#state_box");
  console.log(p2);
  $(p2).val('着手');

  $('#start'+line).hide();

  //状態変更の非同期通信部

}

function completeClick(line){ //完了ボタンクリック時
  var p1 = $('#complete'+line).parents('.data_row');
  var p2 = $(p1).find("#state_box");
  console.log(p2);
  $(p2).val('完了');
  $('#start'+line).hide();
  $('#complete'+line).hide();

	//状態変更の非同期通信部

}

function deleteClick(line){ //削除ボタンクリック時
  //console.log($(this).parents('.tr'));
  $($('#delete'+line).parents('.data_row')).hide();

  //状態変更の非同期通信部

}




</script>
</body>
</html>