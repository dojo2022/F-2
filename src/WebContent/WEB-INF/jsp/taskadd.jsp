<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理追加画面</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Yusei+Magic&display=swap" rel="stylesheet">
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
	<h1>タスク名</h1>
		<input type="text" id=taskname placeholder="タスクを入力しなさいよね"class="title-name" name="taskname" maxlength="30">
	<h1>締め切り</h1>
		<input type="datetime-local" id=datetime class="datetime" name="tasklimit">
	<div></div>
		<input type="submit" id="register" class="btn-regist" name="REGIST" value="タスク追加">
<!-- フォーム終わり -->
</div>
</div>
<!-- 追加画面終わり -->
</div>
<script>
/* 時計 */
function recalc() {
  let dayOfWeek = ['日', '月', '火', '水', '木', '金', '土'];
	let now = new Date();
	let year = now.getFullYear();
	let month = now.getMonth();
	let date = now.getDate();
	let hour = now.getHours();
	let min = now.getMinutes();
	let sec = now.getSeconds();
	document.getElementById('clock').innerHTML
	= year + '年' + month + '月' + date + '日'
	+ '(' + dayOfWeek[now.getDay()] + ')' + '<br>'
	+  hour + '時' + min + '分' + sec + '秒';
	refresh();
}

function refresh() {
	setTimeout(recalc, 1000);
}

recalc();
/* 時計おわり */

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