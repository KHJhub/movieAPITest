<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>박스 오피스 api - java에서 도전</title>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

</head>

<body>

포스터<br>
<img alt="영화 포스터" src=""></img>

영화명<br>
<div id="movieNm">
<%
request.getAttribute("box");
System.out.println(request.getAttribute("box"));
%>
</div>


</body>
</html>