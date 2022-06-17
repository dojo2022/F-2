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
<form class="add_form" id="add_form">
	<h1>タイトル</h1>
		<input type="text" id=taskname placeholder="タスクタイトルを入力して下さい"class="title-name" name="taskname">
	<h1>期限</h1>
		<input type="datetime-local" id=datetime class="datetime" name="tasklimit">
	<div></div>
		<input type="submit" id="register" class="btn-regist" name="REGIST" value="タスク追加">
</form>
<!-- フォーム終わり -->
</div>
</div>
<!-- 追加画面終わり -->
</div>
<script>
$(function(){
	  $('#register').click(function(){
	  //event.preventDefault();
	  var $form = $(this);
	  var $button = $form.find('#register');
	  var data = $('#add_form').serializeArray();  // ①form to json
	  console.log(data);
	  //event.preventDefault();

	  //非同期処理
	  $.ajax({
		  type: 'POST',
		  url: "/imoketu/TaskAddServlet",
		  data: JSON.stringify(data),
		  timeout: 10000,
		  dataType: 'json',
		  // 送信前
		  beforeSend: function(xhr, settings) {
              // ボタンを無効化し、二重送信を防止
              $button.attr('disabled', true);
              const music = new Audio("/imoketu/audio/001_タスク追加時.wav");
			  music.play();
          },
          // 応答後
          complete: function(xhr, textStatus) {
              // ボタンを有効化し、再送信を許可
              $button.attr('disabled', false);
          },
		  })
		  .done(function(data){
			  console.log("aaa");

		  })
	  	  .fail(function(data){
	  		 console.log("bbb");
	  	  });
	  });
	});



</script>
</body>
</html>