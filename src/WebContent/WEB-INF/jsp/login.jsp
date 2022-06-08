<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いもけつ|ログイン</title>
<link href="https://fonts.googleapis.com/earlyaccess/nikukyu.css" rel="stylesheet">
</head>
<body>
<h1>妹がケツを叩いてタスク管理を<br>してくれるアプリ</h1>


			<form id="login_form" class="form" method="POST"
				action="/imoketu/LoginServlet">
				<img src="img/logo.jpg"><br><br>
				ログインID<br><br>
				<input type="text" name="ID"  id="user_id"><br><br>
				パスワード<br><br>
				<input type="password" name="PW" id="password"><br><br>
				<button name="submit" value="submit" type="submit" id="login_button">Login</button>
				<br>
				<span id="error_message">${message}</span>
			</form>

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

<style>
body{
	background-color:#fcc;
	font-family:"Noto Sans JP";
}
form{
	/*width:800px;
    margin:auto;
    font-family:"Nikukyu";*/
    text-align:center;

    }
h1{
	text-align:center;
}
</style>

</html>

