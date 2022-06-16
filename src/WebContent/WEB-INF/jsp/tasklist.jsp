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
<link rel="stylesheet" href="/imoketu/css/tasklist.css">
<link rel="stylesheet" href="/imoketu/css/common.css">
</head>
<body>
	<div class="parent">
		<div class="menu">
			<ul class="menu_ber">
				<li class="menu_content"><a class="active" id="clock">現在日時</a></li>
				<li class="menu_content"><a class="menu_add"
					href="/imoketu/TaskListServlet">タスク確認</a></li>
				<li class="menu_content"><a class="menu_add"
					href="/imoketu/TaskAddServlet">タスク追加</a></li>
				<li class="menu_content"><a class="menu_add"
					href="/imoketu/ExtraServlet">エクストラモード<br>about
				</a></li>
				<li class="menu_content_image"><a class="menu_add">キャラクター(JSON？)</a></li>
			</ul>
		</div>
		<!-- タスク確認始まり -->
	<!-- 追加画面始まり -->
    <div class="listdiv">
     <div class="list_header">
     	タスク確認
     </div>
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
								name="statebox" value="未着手" readonly style="lightglay"></td>
							<td><input type="submit" class="btn1"id="begin${e.taskid}" name="${e.taskid}"
								value="着手"><input type="submit" class="btn2"id="complete${e.taskid}"
								name="${e.taskid}" value="完了"><input type="submit" class="btn3"
								id="delete${e.taskid}" name="${e.taskid}" value="削除"></td>
						</tr>
					</c:if>

					<c:if test="${e.stateflag == 1 }">
						<tr id="data_row${e.taskid}">
							<td>${e.taskname}</td>
							<td>${e.tasklimit}</td>
							<td id="state"><input type="text" id="state_box${e.taskid}"
								name="statebox" value="着手" readonly style="lightglay"></td>
							<td><input type="submit" class="btn2" id="complete${e.taskid}"
								name="${e.taskid}" value="完了"><input type="submit" class="btn3"
								id="delete${e.taskid}" name="${e.taskid}" value="削除"></td>
						</tr>
					</c:if>

					<c:if test="${e.stateflag == 2 }">
						<tr id="data_row${e.taskid}">
							<td>${e.taskname}</td>
							<td>${e.tasklimit}</td>
							<td id="state"><input type="text" id="state_box${e.taskid}"
								name="statebox" value="完了" readonly style="lightglay"></td>
							<td><input type="submit" class="btn3" id="${e.taskid}" name="${e.taskid}"
								value="削除"></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
		</div>
	</div>
	<script>

	//状態変更の非同期通信部
$(function(){

  $('input').click(function(){

  //クリックした時の値を取得(numはId,checkはクリックした所,taskswは状態変化に利用)
  const num = $(this).attr("name");
  const check = $(this).val();
  let tasksw;

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
		  $("#begin"+num).css('display','none');
		  $("#complete"+num).css('display', 'none');
		  $("#state_box"+num).val('完了');
	    }
		//着手ボタン押下時
	    else if(check === $('.btn1').val()){
	      $("#begin"+num).css('display','none');
		  $("#state_box"+num).val('着手');
	    	console.log("着手");
	    }
		//削除ボタン押下時
	    else if(check === $('.btn3').val()){
		  $("#data_row"+num).css('visibility','collapse');
	       console.log("削除");
	    }
	  });
  });
});
</script>
</body>
</html>