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
<<<<<<< Updated upstream
=======

	<!-- モーダルウィンドウ -->
<!--	<div class="overlay"> -->
<!--		<div class="btn_area"> -->
	<!--		<p class="ttl">バカ兄貴ーはやしくしろー！！</p> -->
		<!--	<p><br><br>タスクの期限が１時間前です。<br></p> -->
			<!--<button>閉じる</button> -->
	<!--	</div>-->
<!--	</div>-->
	<!-- モーダルウィンドウここまで -->

  <div class="modal" id="modal">
    <a href="#!" class="overlay"></a>
    <div class="modal-wrapper">
      <div class="modal-contents">
        <a href="#!" class="modal-close">✕</a>
        <div class="modal-content">
          <p id="type"></p><br>
          <p id="test">こんどはちゃんと終わらせるようにしなさいよね</p>

        </div>
      </div>
    </div>
  </div>


>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
*/
			  var log = function(type){
				  console.log("test");
				//音声再生処理
				  if(type==1){
					const music = new Audio("/imoketu/audio/002_未着手二日前.wav");
					music.play();
					document.getElementById("type").innerText = "タスク未着手状態（2日前）";
					document.getElementById("test").innerText = "明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ";
				  }else if(type==2){
					const music = new Audio("/imoketu/audio/003_未着手一日前ver2.wav");
					music.play();
					document.getElementById("type").innerText = "タスク未着手状態（1日前）";
					document.getElementById("test").innerText = "明日締め切りのタスクがあるわよ、大丈夫なの？";
				  }else if(type==3){
					const music = new Audio("/imoketu/audio/004_未着手・未完了当日ver2.wav");
					music.play();
					document.getElementById("type").innerText = "タスク未着手状態（当日朝)";
					document.getElementById("test").innerText = "今日締め切りのタスクがあるわよ、わかってるんでしょうね";
				  }else if(type==4){
					const music = new Audio("/imoketu/audio/005_未完了・未着手3時間前.wav");
					music.play();
					document.getElementById("type").innerText = "タスク未着手状態（3時間前）";
					document.getElementById("test").innerText = "3時間前よ、はやくしなさいって！";
				  }else if(type==5){
					const music = new Audio("/imoketu/audio/006_未着手・未着手　1時間前.wav");
					music.play();
					document.getElementById("type").innerText = "タスク未着手状態（1時間前)";
					document.getElementById("test").innerText = "バカ兄貴ーはやしくしろー！！";
				  }else if(type==6){
					const music = new Audio("/imoketu/audio/012_タスク期限超過.wav");
					music.play();
					document.getElementById("type").innerText = "タスク期限超過時";
					document.getElementById("test").innerText = "はぁ...あきれた。何度いったらわかるの？";
				  }
				};
				setTimeout(log, 5000,1);
				setTimeout(log, 10000,2);
				setTimeout(log, 15000,3);
				setTimeout(log, 20000,4);
				setTimeout(log, 25000,5);
				setTimeout(log, 30000,6);
>>>>>>> Stashed changes
		  });
	});
});
</script>
</body>
</html>