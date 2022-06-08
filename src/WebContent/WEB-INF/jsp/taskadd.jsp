<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理追加画面</title>
<link rel="stylesheet" type="text/css" href="/WebContent/taskadd.css">
</head>
<body>
<div class="title">
<form action="/imoketu/" class="add_form" id="add_form" method="POST">
	<h1>タイトル</h1>
		<input type="text" placeholder="タスクタイトルを入力して下さい"class="titleName" name="titleName">
	<h1>期限</h1>
		<input type="datetime-local" class="datetime" name="datetime">
	<div></div>
		<input type="submit" id="register" class="btn-regist" name="REGIST" value="タスク追加">
</form>
</div>
</body>
</html>