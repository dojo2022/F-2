<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エクストラモード</title>
<link rel="stylesheet" href="/imoketu/css/common.css">
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


  /*音声再生用 ボタンにonclick1~11追加*/
/*   window.onload = function() {
	  for(){
        let my_audio = new Audio("${PathList}");
            //ボタンにクリックイベントを設定
            document.getElementById("btn").onclick = function() {
                my_audio.currentTime = 0;  //再生開始位置を先頭に戻す
                my_audio.play();  //サウンドを再生
            }
	  }
    }
*/

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

/*
$(function(){
    $('.button').click(function(){
		//$(this)はクリックしたオブジェクト（すなわちボタンを指す）
        console.log($(this).text());//ボタンの値
        console.log($(this).attr('id'));//ボタンの属性値を取得
        console.log($(this).attr('name'));//ボタンの属性値を取得
        const audio = new Audio($(this).attr('name'));	//パスを読み込んでmp3ファイルを準備する
        var playAudio = audio.play();

        if (playAudio !== undefined) {
        	playAudio.then(_ => {
              // Automatic playback started!
              // Show playing UI.
              // We can now safely pause audio...
              audio.pause();
            })
            .catch(error => {
              // Auto-play was prevented
              // Show paused UI.
            });
        };
    });
});
*/
/*
$(function(){
    $('.button').click(function(){
		//$(this)はクリックしたオブジェクト（すなわちボタンを指す）
        console.log($(this).text());//ボタンの値
        console.log($(this).attr('id'));//ボタンの属性値を取得
        console.log($(this).attr('name'));//ボタンの属性値を取得
        const music = new Audio($(this).attr('name'));	//パスを読み込んでmp3ファイルを準備する
        music.currentTime = 0;//サウンドを停止
        music.play();  //サウンドを再生
    });
});
*/
</script>

</body>
</html>
