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
<link rel="stylesheet" href="/imoketu/css/modal.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
  <!-- メイン -->
    <!-- メニューはじまり -->
  <main>
    <div class="parent">
      <div class="menu">
        <ul class="menu_ber">
        	<li class="menu_content"><a class="active" id="clock">現在日時</a></li>
        	<li class="menu_content"><a class="menu_add" href="/imoketu/TaskListServlet">タスク確認</a></li>
        	<li class="menu_content"><a class="menu_add" href="/imoketu/TaskAddServlet">タスク追加</a></li>
        	<li class="menu_content"><a class="menu_add" href="/imoketu/ExtraServlet">エクストラモード<br>about</a></li>
          <li class="menu_content_image"><a class="menu_add">キャラクターイメージを差し込む</a></li>
        </ul>
      </div>

    <!-- メニューおわり -->

    <!-- エクストラ画面はじまり -->
    <div class="extradiv">
      <!-- ヘッダーはじまり -->
      <div class="menu_header">
        エクストラモード
      </div>
      <!-- ヘッダーおわり -->
      <ul>
          <li class="aboutli">【使い方】</li>
          <li class="aboutli">タスク確認では...</li>
          <li class="aboutli">タスク追加では...</li>
      </ul>
      <ul>
        <li class="extrali">【エクストラモード】</li>
        <!-- <form method="POST" action="/imoketu/ExtraServlet">  -->
        <div class="newline">
          <li class="extrali"><button class="button" type="button" id=${PathList["ID1"]} name=${PathList["VP1"]}>音声1</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID2"]} name=${PathList["VP2"]}>音声2</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID3"]} name=${PathList["VP3"]}>音声3</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID4"]} name=${PathList["VP4"]}>音声4</button></li>
        </div>
        <div class="newline">
          <li class="extrali"><button class="button" type="button" id=${PathList["ID5"]} name=${PathList["VP5"]}>音声5</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID6"]} name=${PathList["VP6"]}>音声6</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID7"]} name=${PathList["VP7"]}>音声7</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID8"]} name=${PathList["VP8"]}>音声8</button></li>
        </div>
        <div class="newline">
          <li class="extrali"><button class="button" type="button" id=${PathList["ID9"]} name=${PathList["VP9"]}>音声9</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID10"]} name=${PathList["VP10"]}>音声10</button></li>
          <li class="extrali"><button class="button" type="button" id=${PathList["ID11"]} name=${PathList["VP11"]}>音声11</button></li>
        </div>
        <div>
          <li class="extrali"><button id="stop">停止</button></li>
        </div>
      </ul>
    </div>
    <!-- エクストラ画面おわり -->
  </div>
  </main>
  <!-- メインおわり -->

  <!-- モーダルウィンドウはじまり -->
  <div id="modal-overlay">
    <div class="modal-mask"></div>
      <div class="modal-container">
        <div class="modal-inner">
          <div class="modal-title">期限が迫っているタスクを表示</div>
          <div class="modal-text">
          <!-- ここにタスク期限テーブルからタスク情報を引っ張ってくる -->
          	<p>
			ボタンをクリックすると非同期通信でサーブレットからDBデータを取得します。
			</p>
			<button id="getbox" type="button">データの取得</button>
			<button id="delbox">データ消去ボタン</button>
			<div id="result"></div>

          </div>
          <button class="close">×</button>
        </div>
    </div>
  </div>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="/imoketu/js/common.js"></script> <!-- ご自身のパスに変更 -->
  <!-- モーダルウィンドウおわり -->

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
        const music = new Audio($(this).attr('name'));	//パスを読み込んでmp3ファイルを準備する
        //music.currentTime = 0;//サウンドを停止
        music.play();  //サウンドを再生

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

/* モーダルの中身 */
/* const getData = () =>{
	  let request = new XMLHttpRequest();
	  request.onreadystatechange = function(e){
	    if (request.readyState == 4){
	      if (request.status == 200){
	        let node = document.getElementById("result");
	        node.innerHTML = request.responseText;
	      }else{
	        console.error(request.statusText);
	      }
	    }
	  }
	  request.open('GET', 'http://localhost:8080/example/UpdateDbApi?role=1', true);
	  request.send();
	  let del = document.getElementById("delbox");
	  del.style.display = 'block';
	}
	getbox.addEventListener('click', getData, false)
	const del = (e) =>{
		  let output = document.getElementById("result");
		  output.innerHTML = "";
		  e.target.style.display = 'none';
	}
	delbox.addEventListener('click', del, false);*/
/* モーダルの中身おわり */
</script>

</body>
</html>
