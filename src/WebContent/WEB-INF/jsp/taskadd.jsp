<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理追加画面</title>
<link rel="stylesheet" href="/imoketu/css/taskadd.css">
<link rel="stylesheet" href="/imoketu/css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
	<h1>タイトル</h1>
		<input type="text" id=taskname placeholder="タスクタイトルを入力して下さい"class="title-name" name="taskname">
	<h1>期限</h1>
		<input type="datetime-local" id=datetime class="datetime" name="tasklimit">
	<div></div>
		<input type="submit" id="register" class="btn-regist" name="REGIST" value="タスク追加">
<!-- フォーム終わり -->
</div>
</div>
<!-- 追加画面終わり -->
</div>
<script>
//タスク追加ボタンを押下時、音声再生
$(function(){
	  $('#register').click(function(){
	  //inputの中身を取得
	  const taskname = document.getElementById("taskname").value;
	  const tasklimit = document.getElementById("datetime").value;

	  //非同期処理
	  $.ajax({
		  type: 'POST',
		  url: "/imoketu/TaskAddServlet",
		  data: "taskname="+taskname+"&tasklimit="+tasklimit,
		  timeout: 10000,
		  dataType: 'text',
	  	  })
		  .done(function(data){
			  const music = new Audio("/imoketu/audio/001_タスク追加時.wav");
			  music.play();
			 //音声再生終了(3秒後)後ページをリロード
			  setTimeout(function(){
				  window.location.reload();
				      },3000);
		  });
	});
});
</script>
</body>
</html>