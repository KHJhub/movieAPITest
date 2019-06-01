package com.kitri.service.film;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kitri.dto.FilmDto;

// S
// ① api 호출

public class FilmService {

		// [싱글톤 패턴 객체]
		private static FilmService filmService;
		static {
			filmService = new FilmService();
		}
		public static FilmService getFilmService() {
			return filmService;
		}
		private FilmService() {}
		
		
		// [메소드]
		
		// 1
		// <일별 박스 오피스 영화 목록> 출력 메소드
		// 영진원 - 일별 박스오피스 api
		public List<FilmDto> getBoxOffice() {
			
			List<FilmDto> box = new ArrayList<>();
			
			// 1 API 호출 (httpClient 라이브러리 사용 -> 이름 바뀜 httpComponent - 6줄)
			String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
			String urlParameters1 = "key=d497cad784b01e0c354d04518c4ddfc7";
        	String urlParameters2 = "targetDt=20190531";

        	String httpUrl = url + "?" + urlParameters1 + "&" + urlParameters2;
        	
			try {
				HttpClient client = HttpClientBuilder.create().build(); 					// HttpClient 생성
				HttpGet getRequest = new HttpGet(httpUrl); 							// GET 메소드 URL 생성
				
				//getRequest.addHeader("x-api-key", RestTestCommon.API_KEY); 	// Header 설정

				HttpResponse response = client.execute(getRequest);

				//Response 출력
				if (response.getStatusLine().getStatusCode() == 200) {   				// response의 status 코드 출력

					ResponseHandler<String> handler = new BasicResponseHandler();
					String body = handler.handleResponse(response);
					
					// String에 담긴 json 파싱 *************************
					JSONParser jsonParser = new JSONParser();
		             
		            //JSON데이터를 넣어 JSON Object 로 만들어 준다.
		            JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
		             
		            //boxOfficeResult의 배열을 추출
		            JSONObject boxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
		            JSONArray dailyBoxOfficeList = (JSONArray) boxOfficeResult.get("dailyBoxOfficeList");
		            
		            System.out.println("* 어제자 박스오피스 top 10 *");
		 
		            for(int i=0; i<dailyBoxOfficeList.size(); i++){
		 
		                System.out.println("--------------"+(i+1) +" 위 -------------");
		                 
		                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
		                JSONObject dailyListItems = (JSONObject) dailyBoxOfficeList.get(i);
		                 
		                //JSON name으로 추출
		                System.out.println("순위==>"+dailyListItems.get("rank"));
		                System.out.println("영화코드==>"+dailyListItems.get("movieCd"));
		                System.out.println("영화명==>"+dailyListItems.get("movieNm"));
		                System.out.println("개봉일==>"+dailyListItems.get("openDt"));
		 
		            }
		 

				} else {

					System.out.println("response is error : " + response.getStatusLine().getStatusCode());

				}

			} catch (Exception e){
				System.err.println(e.toString());
			}
			
			// 2 응답값 box에 세팅
			
			// 3 box 리턴
			
			return null;
		}
	
}
