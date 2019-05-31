<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 목록 보여줄 화면</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
$(function(){
	$("#btn").click(function(){
		$("#content").empty();
		$.ajax({
			url:'showboxoffice',
			method:'get',
			success:function(result){
					$("div#content").html(result);	
			}
		});
		return false;
	});
});
</script>

</head>

<body>

<button id="btn">
api 호출!
</button>
<div id="content">
결과 화면 나올 곳
</div>


</body>
</html>