<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>非同期通信実験</title>
<style>
#delbox{
	display: none;
}
#result_box{
	margin-top: 20px;
	padding:10px 25px 10px;
	border: solid 1px  #765521;
	width:400px;
}
</style>
</head>
<body>
<p>
ボタンをクリックすると非同期通信でサーブレットからDBデータを取得します。
</p>
<button id="getbox" type="button">データの取得</button>
<button id="delbox">データ消去ボタン</button>
<div id="result"></div>
<script>
const getData = () =>{
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
  request.open('GET', 'http://localhost:8080/z2/UpdateDbApi?role=1', true);
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
delbox.addEventListener('click', del, false);
</script>
</body>
</html>