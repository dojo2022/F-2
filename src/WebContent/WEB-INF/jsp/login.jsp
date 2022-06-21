<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いもけつ|ログイン</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Yusei+Magic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/imoketu/css/login.css">
</head>
<body>
<div class="wrap">
<video id="bg-video" src="img/heart_bg.mp4" poster="img/haikei.png" autoplay loop muted></video>
<h1>妹がケツを叩いてタスク管理を<br>してくれるアプリ</h1>
        <div class=formout>
			<form id="login_form" class="form" method="POST"
				action="/imoketu/LoginServlet">
				ログインID
				<input type="text" name="ID" id="user_id"><br><br>
				パスワード
				<input type="password" name="PW" id="password"><br><br>
				<button name="submit" value="submit" type="submit" id="login_button">Login</button>
				<br>
				<span id="error_message">${message}</span>
			</form>
		</div>
		<div class="imagediv">
			<img alt="妹" src="./img/03okoru.png" class="image">
		</div>
</div>
<script type="text/javascript">
		document
				.getElementById("login_button")
				.addEventListener(
						"click",
						function(e) {
							// リンクがクリックされたときの処理
							const id_check = document
									.getElementById('login_form').ID.value;
							const pw_check = document
									.getElementById('login_form').PW.value;

							if (id_check === "" || pw_check === "") {
								document.getElementById('error_message').textContent = "ID又はパスワードが入力されていません";
								e.preventDefault(); // 画面遷移を無効化
							}
						}, false);
	</script>

</body>

</html>

