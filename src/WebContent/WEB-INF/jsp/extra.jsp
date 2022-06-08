<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エクストラモード</title>
<link rel="stylesheet" href="/imoketu/css/common.css">
</head>
<body>
  <!-- メイン -->
    <!-- メニューはじまり -->
    <!-- メニュー以外の画面をdivタグで囲んでcssで float: left; で左右配置できます-->
  <main>
    <div class="menu">
      <ul class="menu_ber">
      	<li class="menu_content"><a class="active" id="clock">現在日時</a></li>
      	<li class="menu_content"><a class="menu_add" href="#">タスク確認</a></li>
      	<li class="menu_content"><a class="menu_add" href="#">タスク追加</a></li>
      	<li class="menu_content"><a class="menu_add" href="#">エクストラモード<br>about</a></li>
        <li class="menu_content_image"><a class="menu_add">キャラクターイメージ</a></li>
      </ul>
    </div>

    <!-- メニューおわり -->
    <!-- エクストラ画面はじまり -->
    <div class="extradiv">
      <ul>
          <li class="aboutli">【使い方】</li>
          <li class="aboutli">タスク確認では...</li>
          <li class="aboutli">タスク追加では...</li>
      </ul>
      <ul>
        <li class="extrali">【エクストラモード】</li>
        <div class="newline">
          <li class="extrali"><button type="button">音声1</button></li>
          <li class="extrali"><button type="button">音声2</button></li>
          <li class="extrali"><button type="button">音声3</button></li>
          <li class="extrali"><button type="button">音声4</button></li>
        </div>
        <div class="newline">
          <li class="extrali"><button type="button">音声5</button></li>
          <li class="extrali"><button type="button">音声6</button></li>
          <li class="extrali"><button type="button">音声7</button></li>
          <li class="extrali"><button type="button">音声8</button></li>
        </div>
        <div class="newline">
          <li class="extrali"><button type="button">音声9</button></li>
          <li class="extrali"><button type="button">音声10</button></li>
          <li class="extrali"><button type="button">音声11</button></li>
        </div>
      </ul>
    </div>
    <!-- エクストラ画面おわり -->
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
</script>
</body>
</html>
