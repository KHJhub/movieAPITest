<%@page import="java.io.Console"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화진흥원 영화 목록 조회 api test (json 파싱)</title>

<%
Calendar c1 = new GregorianCalendar();
c1.add(Calendar.DATE, -1); // 어제날짜 (박스오피스 기준 날짜)
SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷 
String today = f.format(c1.getTime()); // String으로 저장
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
$(function(){
	$("#btn").click(function(){
		$("#test").empty();
		$.ajax({
			url:'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
			method:'get',
			data: "key=d497cad784b01e0c354d04518c4ddfc7&targetDt=" + <%=today%>,
			success:function(data){
				//var obj = JSON.parse(data); //json을 객체로 받음 //*-> 이미 json 객체를 받아온거라 변환 필요 없음
				
				// 1) json 객체에서 boxOfficeResult 객체(object)를 받아옴
				var boxOfficeResult = data.boxOfficeResult; 
		
				// 2) 1에서 dailyBoxOfficeList 배열을 받아옴 (rank, movieNm, movieCd가 속해있음)
				var dailyBoxOfficeList = boxOfficeResult.dailyBoxOfficeList; 
	
			 	var len = dailyBoxOfficeList.length;        //배열 길이
			 	
			 	// for 1  영화 제목, 영화 코드
				//for(var i = 0; i < len; i++){
					
					//var rank = dailyBoxOfficeList[i].rank;
					//var movieNm = dailyBoxOfficeList[i].movieNm;
					//var movieCd = dailyBoxOfficeList[i].movieCd;
					
					//$("#rank").append(rank + "   ");
					//$("#movieNm").append(movieNm + "   ");
					//$("#movieCd").append(movieCd + "   ");
					
				//}
			 	
			 	var movieNm = dailyBoxOfficeList[0].movieNm; //1위 영화명
			 	
			 	var header = {
			 			"X-Naver-Client-Id" : "Fc4lGVGl3zDMtizzcZbx",
						"X-Naver-Client-Secret" : "q3OgVCUh0y"
			 	}
			 	// ajax 2
				$.ajax({
					url : 'https://openapi.naver.com/v1/search/image',
						method:'get',
						 beforeSend: function(request) {
							    request.setRequestHeader("X-Naver-Client-Id", "Fc4lGVGl3zDMtizzcZbx");
							    request.setRequestHeader("X-Naver-Client-Secret", "q3OgVCUh0y");
							  },
						data: "query="+movieNm+"&display=1&sort=sim",
						success:function(data){
							// for 2 영화 제목 배열 길이만큼 반복, 이미지 배열 얻기
							var poster = data.items.titles;
							alert(poster);
							
							// for 3 이미지 배열만큼 반복, 태그에 뿌리기
						}
				});
				
			},
            error : function(jqXHR, testStatus){
                console.log(testStatus);  // 'error'
            }
		});
		return false;
	});
});
</script>

</head>

<body>

<h2 id="rank">
순위
</h2>
<div id="movieNm">
영화명
</div>
<div id="movieCd">
영화코드(영진원)
</div>

<button id="btn">
api 호출
</button>

<img id="poster" alt="" src="">

</body>
</html>