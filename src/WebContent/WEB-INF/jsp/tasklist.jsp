<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous">
  </script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いもケツ</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Yusei+Magic&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/imoketu/css/tasklist.css">
<link rel="stylesheet" href="/imoketu/css/common.css">
</head>
<body>
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
				<li class="menu_content_image"><a class="menu_add"><img src="./img/03okoru.png" class="image"></a></li>
			</ul>
		</div>
		<!-- タスク確認始まり -->
		<!-- 追加画面始まり -->
		<div class="listdiv">
			<div class="list_header">☆タスク確認☆</div>
			<div id="table">
				<table id="list">
					<tr class="columnitem">
						<div>
							<th>タスク名</th>
							<th>期限</th>
							<th>状態</th>
							<th>状態操作</th>
						</div>
					</tr>
					<c:forEach var="e" items="${taskList}">


						<c:if test="${e.stateflag == 0 }">
							<tr id="data_row${e.taskid}">
								<td>${e.taskname}</td>
								<td>${e.tasklimit}</td>
								<td id="state"><input type="text" id="state_box${e.taskid}"
									class="state" name="statebox" value="未着手" readonly style=""></td>
								<td><input type="submit" class="btn1" id="begin${e.taskid}"
									name="${e.taskid}" value="着手" classs="${e.tasklimit}"><input
									type="submit" class="btn2" id="complete${e.taskid}"
									name="${e.taskid}" value="完了" classs="${e.tasklimit}"><input
									type="submit" class="btn3" id="delete${e.taskid}"
									name="${e.taskid}" value="削除"></td>
							</tr>
						</c:if>

						<c:if test="${e.stateflag == 1 }">
							<tr id="data_row${e.taskid}">
								<td>${e.taskname}</td>
								<td>${e.tasklimit}</td>
								<td id="state"><input type="text" id="state_box${e.taskid}"
									name="statebox" value="着手" readonly style="" class="state"></td>
								<td><input type="submit" class="btn2"
									id="complete${e.taskid}" name="${e.taskid}" value="完了"
									classs="${e.tasklimit}"><input type="submit"
									class="btn3" id="delete${e.taskid}" name="${e.taskid}"
									value="削除"></td>
							</tr>
						</c:if>

						<c:if test="${e.stateflag == 2 }">
							<tr id="data_row${e.taskid}">
								<td>${e.taskname}</td>
								<td>${e.tasklimit}</td>
								<td id="state"><input type="text" id="state_box${e.taskid}"
									name="statebox" value="完了" readonly style="" class="state"></td>
								<td><input type="submit" class="btn3" id="${e.taskid}"
									name="${e.taskid}" value="削除"></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
<script>
	//メニューバーのマウスオーバー時のイベント
	let bar = document.getElementById('menu_bar');
	let menu_list = document.getElementById('menu_list');
	let menu_add = document.getElementById('menu_add');
	let menu_extra = document.getElementById('menu_extra');

	bar.addEventListener("mouseover", function(event) {
  	// mouseover の対象を強調
  		event.target.style.color = "orange";

  	// 少し待ってから色をリセット
  	setTimeout(function() {
    	event.target.style.color = "";
  	}, 500);
	}, false);
	//メニューバーここまで
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
	//状態変更の非同期通信部
$(function(){

  $('input').click(function(){

  //クリックした時の値を取得(numはId,checkはクリックした所,taskswは状態変化に利用)
  const num = $(this).attr("name");
  const check = $(this).val();
  let tasksw;

  var nowDate = new Date(); //現在時刻
  var kigen = new Date($(this).attr('classs'));//押したボタンのある行の期限
  var keisan = (kigen.getTime() - nowDate.getTime()) / (60*60*1000);//押したボタンのところの、期限-現在時間を時間単位で出してる

  //ボタンをクリックしたら、値がセットされる様にしている
  if(check === $('.btn2').val()) {
	  tasksw = 1;
    }
    else if(check === $('.btn1').val()){
      tasksw = 2;
    }
    else if(check === $('.btn3').val()){
	  tasksw = 3;
    }


  //非同期処理
  $.ajax({
	  type: 'GET',
	  url: "/imoketu/AjaxServlet",
	  async: true,
	  timeout: 10000,
	  data: "tid="+num+"&tasksw="+tasksw,//２つのdataを送る時の方法←
	  })
	  .done(function(data){
		//完了ボタン押下時
	    if(check === $('.btn2').val()) {
			  //期限以内の時
			  if(kigen > nowDate){
				  $("#begin"+num).css('display','none');
				  $("#complete"+num).css('display', 'none');
				  $("#state_box"+num).val('完了');
				  const music = new Audio("/imoketu/audio/010_完了時期限以内ver2.wav");
				  music.play();
			  }
			  //期限超過時
			  else if(nowDate > kigen){
				  $("#begin"+num).css('display','none');
				  $("#complete"+num).css('display', 'none');
				  $("#state_box"+num).val('完了');
				  const music = new Audio("/imoketu/audio/011_完了期限超過ver2.wav");
				  music.play();
			  }
	    }
		//着手ボタン押下時
	    else if(check === $('.btn1').val()){
		      //前日以前
		      if(keisan > 24){
		    	  $("#begin"+num).css('display','none');
				  $("#state_box"+num).val('着手');
				  const music = new Audio("/imoketu/audio/007_着手前日以前.wav");
				  music.play();
		      }
		      //当日
		      else if(24 >= keisan && keisan > 2){
		    	  $("#begin"+num).css('display','none');
				  $("#state_box"+num).val('着手');
				  const music = new Audio("/imoketu/audio/008_着手当日.wav");
				  music.play();
		      }
		      //締め切り2時間前
		      else if(keisan < 2){
		    	  $("#begin"+num).css('display','none');
				  $("#state_box"+num).val('着手');
				  const music = new Audio("/imoketu/audio/009_着手2時間前.wav");
				  music.play();
		      }
	    }
		  //削除ボタン押下時
	    else if(check === $('.btn3').val()){
	    	$("#data_row"+num).css('visibility','collapse');
	    }

	  });

  });
});
</script>
</body>
</html>