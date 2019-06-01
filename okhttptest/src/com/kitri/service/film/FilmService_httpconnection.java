package com.kitri.service.film;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kitri.dto.FilmDto;

// S
// ① api 호출

public class FilmService_httpconnection {

	// [싱글톤 패턴 객체]
	private static FilmService_httpconnection filmService;
	static {
		filmService = new FilmService_httpconnection();
	}

	public static FilmService_httpconnection getFilmService() {
		return filmService;
	}

	private FilmService_httpconnection() {
	}

	// [메소드]

	// 1
	// <일별 박스 오피스 영화 목록 출력> 메소드
	// : 영진원 일별 박스오피스 api + 네이버 이미지 검색 api
	public List<FilmDto> getBoxOffice() {
		
		List<FilmDto> box = new ArrayList<>();

		// 1 API 호출 (HttpUrlConnection)

		// 1-1. 영진원 일별 박스오피스 API
		// ① url + 파라미터 값 설정
		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		String urlParameters1 = "key=d497cad784b01e0c354d04518c4ddfc7";
		String urlParameters2 = "targetDt=20190531";

		String httpUrl = url + "?" + urlParameters1 + "&" + urlParameters2;

		// ② API 호출 (GET)
		String response = APIHttpGet(httpUrl, null);

		// ③ response (JSON) 파싱
		// *박스오피스 JSON 구조 : { {boxOfficeResult} - [dailyBoxOfficeList] 여러 개 - {key1 : "", key2 : "" , ...} }
		try {
			
				// JSON 파서 객체 생성
				JSONParser jsonParser = new JSONParser();
				
				// String 타입의 JSON값으로, 가장 큰 JSON 객체 생성
				JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
		
				// dailyBoxOfficeList JSON배열 추출
				JSONObject boxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
				JSONArray dailyBoxOfficeList = (JSONArray) boxOfficeResult.get("dailyBoxOfficeList");
				
				// dailyBoxOfficeList JSON배열의 값(JSON객체)들을 뽑아냄
				int len = dailyBoxOfficeList.size();
				for (int i = 0; i < len; i++) {
					
					JSONObject dailyListItems = (JSONObject) dailyBoxOfficeList.get(i);
					
					String MovieCdYoung = dailyListItems.get("movieCd").toString(); 		// 영화코드(영진원)
					String MovieNm = dailyListItems.get("movieNm").toString(); 			// 영화명
					
		// 1-2. 네이버 이미지 검색 API
					
					// 헤더값 생성
					HashMap<String, String> header = new HashMap<>();
					//header.put("");
					
				} // for문 end
		
		} catch (ParseException e) {
			e.printStackTrace();
		}



	// 2 API를 통해 얻은 값(영화명, 영화코드, 포스터URL)을 box에 세팅

	// 3 box 리턴

	return null;
	
} // 메소드 end

	
	// 2 <HTTP GET 호출> 메소드

	// *인자값
	// - String httpUrl : 파라미터 포함 url
	// - HashMap header : 헤더(key-value) *헤더 없으면 null 넣기
	// * return
	// JSON형식의 응답결과 (String 타입)

	public String APIHttpGet(String httpUrl, HashMap header) {

			String response = "";    // 응답 결과 담을 String
			
	        try {

	        	// ① HttpUrlConnection 객체 생성 및 세팅
	        	URL obj = new URL(httpUrl);
	        	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        	
	        	con.setRequestMethod("GET");  					// 전송방식 설정 (GET)
	        	con.setConnectTimeout(10000);    				// 연결 제한시간 10초
	        	con.setReadTimeout(5000);             			// 컨텐츠 조회 제한시간 5초

	        	// 헤더 설정
	        	if(header != null) {
	        		Iterator<String> keys = header.keySet().iterator();
	        		while(keys.hasNext()) {
	        			String key = keys.next();
	        			String value = (String) header.get(key);
	        			con.setRequestProperty(key, value); 		// Request Header 정의
	        		}
	        	}
	        	
	        	int responseCode = con.getResponseCode();  // response의 status 코드 얻어옴
	        	
	        	// ② 호출이 정상일 때, 응답 결과 사용
	        	if (responseCode == 200) {   	
	        	
		        	Charset charset = Charset.forName("UTF-8");
		        	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
		        	String inputLine;
		        	StringBuffer sr = new StringBuffer();
		        	
					while ((inputLine = in.readLine()) != null) {
						sr.append(inputLine);
					}
					in.close();
					
					response = sr.toString();
					
	        	}
	        	
				} catch (IOException e) {
					e.printStackTrace();
				}
	     
			return response;
		}

}
