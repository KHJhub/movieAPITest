package com.kitri.service.film;

import java.util.ArrayList;
import java.util.List;

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
			
			// 1 API 호출
			
			// 2 응답값 box에 세팅
			
			// 3 box 리턴
			
			return null;
		}
	
}
