<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エクストラモード</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Yusei+Magic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/imoketu/css/common.css">
<link rel="stylesheet" href="/imoketu/css/extra.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<!-- モーダルウィンドウ -->
  <div class="modal" id="modal">
    <a href="#!" class="overlay"></a>
    <div class="modal-wrapper">
      <div class="modal-contents"><%--ここに画像を差し込む --%>
        <a href="#!" class="modal-close">✕</a>
        <div class="modal-content">
          <p id="test">test</p>
          <div id="sasikae"><img src="./img/hiru1.jpg" id="sasi"></div>
        </div>
      </div>
    </div>
  </div>
<!-- モーダルウィンドウここまで -->
  <!-- メイン -->
    <!-- メニューはじまり -->
  <main>
    <div class="parent">
      <div class="menu">
			<div class="clockdiv">
				<a class="active" id="clock">現在日時</a>
			</div>
			<ul class="menu_bar" id="menu_bar">
				<li class="menu_content"><a class="menu_add" id="menu_list"
					href="/imoketu/TaskListServlet">タスク確認</a></li>
				<li class="menu_content"><a class="menu_add" id="menu_add"
					href="/imoketu/TaskAddServlet">タスク追加</a></li>
				<li class="menu_content"><a class="menu_add" id="menu_extra"
					href="/imoketu/ExtraServlet">about
				</a></li>
				<li class="menu_content_image"><a class="menu_add"><img src="./img/01egao.png" class="image"></a></li>
			</ul>
		</div>
    <!-- メニューおわり -->
    <!-- エクストラ画面はじまり -->
    <div class="listdiv">
      <!-- ヘッダーはじまり -->
      <div class="list_header">
        ☆about☆
      </div>
      <!-- ヘッダーおわり -->
      <br><br><br>
      <div class="haikei">
      <ul>
          <li class="aboutli">【使い方】</li>
          <li class="aboutli">タスク確認では登録したタスクの名前と状態が確認できて、タスク状態の変更をすることができるわよ。(｀▽´)  </li>
          <li class="aboutli">タスク追加では新しいタスクの追加ができるわよ。忘れずに登録しなさいよ！(* ･`д･´)</li>
          <li class="aboutli">エクストラモードでは登録されている音声を自由に再生することができるわよ。罵られたいあなたには最適ね(|｜!￣Д￣)</li>
      </ul>
      </div>
      <br>
      <ul>
        <li class="extrali">【エクストラモード】</li>
        <!-- <form method="POST" action="/imoketu/ExtraServlet">  -->
          <li class="extrali"><button class="button" type="button" id=${PathList["ID1"]} name=${PathList["VP1"]}>タスク追加時</button></li>
          <li class="extrali" class="modal-open"><a href="#modal"><button class="button" type="button" id=${PathList["ID2"]} name=${PathList["VP2"]}>未着手二日前</button></a></li>
          <li class="extrali" class="modal-open"><a href="#modal"><button class="button" type="button" id=${PathList["ID3"]} name=${PathList["VP3"]}>未着手一日前</button></a></li>
          <li class="extrali" class="modal-open"><a href="#modal"><button class="button" type="button" id=${PathList["ID4"]} name=${PathList["VP4"]}>未着手・未完了当日</button></a></li>
          <li class="extrali" class="modal-open"><a href="#modal"><button class="button" type="button" id=${PathList["ID5"]} name=${PathList["VP5"]}>未着手・未完了3時間前</button></a></li>
          <li class="extrali" class="modal-open"><a href="#modal"><button  class="button" type="button" id=${PathList["ID6"]} name=${PathList["VP6"]}>未着手・未完了1時間前</button></a></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID7"]} name=${PathList["VP7"]}>着手前日以前</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID8"]} name=${PathList["VP8"]}>着手当日</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID9"]} name=${PathList["VP9"]}>着手2時間前</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID10"]} name=${PathList["VP10"]}>完了時期限以内</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID11"]} name=${PathList["VP11"]}>完了期限超過</button></li>
          <li class="extrali" class="modal-open"><a href="#modal"><button class="button" type="button" id=${PathList["ID12"]} name=${PathList["VP12"]}>タスク期限超過</button></a></li>
          <li class="extrali"><button id="stop" class="stopbutton">停止</button></li>
      </ul>
    </div>
    <!-- エクストラ画面おわり -->
  </div>
  </main>
  <!-- メインおわり -->
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
  /* 音声再生 */
$(function(){
    $('.button').click(function(){
    	//全ての音声ボタンを無効化。でも既に押されたボタン情報は消されることはない。
		//あくまでも見た目のボタンが無効化されるだけで押した事実は残る。
		$(".button").attr('disabled','disabled');
		//$(this)はクリックしたオブジェクト（すなわちボタンを指す）
        console.log($(this).text());//ボタンの値
        console.log($(this).attr('id'));//ボタンの属性値を取得
        console.log($(this).attr('name'));//ボタンの属性値を取得
        const music = new Audio($(this).attr('name'));	//パスを読み込んでファイルを準備する
        //music.currentTime = 0;//サウンドを停止
        music.play();  //サウンドを再生
        if($(this).attr('id') == "2"){
        	document.getElementById("test").innerText = "明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ";
            document.getElementById("sasi").src = "./img/asabefore1-2ver2.jpg";
        }
        if($(this).attr('id') == "3"){
        	document.getElementById("test").innerText = "明日締め切りのタスクがあるわよ、大丈夫なの？";
        	document.getElementById("sasi").src = "./img/asabefore1-2ver2.jpg";
        }
        if($(this).attr('id') == "4"){
        	document.getElementById("test").innerText = "今日締め切りのタスクがあるわよ、わかってるんでしょうね";
        	document.getElementById("sasi").src = "./img/touzituasaVer2.jpg";
        }
        if($(this).attr('id') == "5"){
        	document.getElementById("test").innerText = "3時間前よ、はやくしなさいって！";
        	document.getElementById("sasi").src = "./img/yugatabefore3ver2.jpg";
        }
        if($(this).attr('id') == "6"){
        	document.getElementById("test").innerText = "バカ兄貴ーはやしくしろー！！";
        	document.getElementById("sasi").src = "./img/notcompletebefore1andoverver2.jpg";
        }
        if($(this).attr('id') == "12"){
        	document.getElementById("test").innerText = "はぁ...あきれた。何度いったらわかるの？";
        }
		//音声再生が終了したらボタンを有効化
		music.onended = function(){
			$(".button").removeAttr('disabled');
		};
		//再生の停止処理とボタンの有効化
		$('#stop').click(function(){
			music.pause();
			$(".button").removeAttr('disabled');
		});

    });
});
/* 音声再生おわり */
/*
			  var log = function(type){
				  console.log("test");
				//音声再生処理
				  if(type==1){
					const music = new Audio("/imoketu/audio/002_未着手二日前.wav");
					music.play();
					document.getElementById("test").innerText = "明後日締め切りのタスクがあるわよ、ちゃんと余裕を持ってやりなさいよ";
				  }else if(type==2){
					const music = new Audio("/imoketu/audio/003_未着手一日前ver2.wav");
					music.play();
					document.getElementById("test").innerText = "明日締め切りのタスクがあるわよ、大丈夫なの？";
				  }else if(type==3){
					const music = new Audio("/imoketu/audio/004_未着手・未完了当日ver2.wav");
					music.play();
					document.getElementById("test").innerText = "今日締め切りのタスクがあるわよ、わかってるんでしょうね";
				  }else if(type==4){
					const music = new Audio("/imoketu/audio/005_未完了・未着手3時間前.wav");
					music.play();
					document.getElementById("test").innerText = "3時間前よ、はやくしなさいって！";
				  }else if(type==5){
					const music = new Audio("/imoketu/audio/006_未着手・未着手　1時間前.wav");
					music.play();
					document.getElementById("test").innerText = "バカ兄貴ーはやしくしろー！！";
				  }else if(type==6){
					const music = new Audio("/imoketu/audio/012_タスク期限超過.wav");
					music.play();
					document.getElementById("test").innerText = "はぁ...あきれた。何度いったらわかるの？";
				  }
				};
				setTimeout(log, 5000,1);
				setTimeout(log, 10000,2);
				setTimeout(log, 15000,3);
				setTimeout(log, 20000,4);
				setTimeout(log, 25000,5);
				setTimeout(log, 30000,6);
*/
</script>
</body>
</html>






















