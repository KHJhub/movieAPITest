<%@page import="com.kitri.dto.FilmDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>박스 오피스 api - java에서 도전</title>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<style>
img{
width : 300px;
}
</style>

<%
List<FilmDto> list = (List<FilmDto>)request.getAttribute("box");
%>

</head>

<body>

<%
for(FilmDto dto : list){
%>	
	
포스터<br>
<img alt="영화 포스터" src="<%=dto.getMovieImage()%>"></img>

<br>영화명 : 
<span id="movieNm">
<%=dto.getMovieNm()%>
</span>
<br><br>
<%
}
%>



</body>
</html>