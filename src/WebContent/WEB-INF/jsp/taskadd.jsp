<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理追加画面</title>
<link rel="stylesheet" href="/imoketu/css/taskadd.css">
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
    <!-- 追加画面始まり -->
    <div class="adddiv">
     <div class="add_header">
     	タスク追加
     </div>
<div class="title">
<!-- フォーム -->
<form action="/imoketu/TaskAddServlet" class="add_form" id="add_form" method="POST">
	<h1>タイトル</h1>
		<input type="text" placeholder="タスクタイトルを入力して下さい"class="title-name" name="taskname">
	<h1>期限</h1>
		<input type="datetime-local" class="datetime" name="tasklimit">
	<div></div>
		<input type="submit" id="register" class="btn-regist" name="REGIST" value="タスク追加">
</form>
<!-- フォーム終わり -->
</div>
</div>
<!-- 追加画面終わり -->
</div>
</body>
</html>