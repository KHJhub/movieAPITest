package com.kitri.controller.film;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.FilmDto;
import com.kitri.service.film.FilmService;
import com.kitri.service.film.FilmService_httpconnection;

// C
// ① request.setAttribute

public class FilmController {
	private static FilmController filmController;

	static {
		filmController = new FilmController();
	}

	private FilmController() {

	}

	public static FilmController getUserController() {
		return filmController;
	}
	
	
	// 1
	// 
	public String getBoxOffice(HttpServletRequest request, HttpServletResponse response) {
		
		String path = "/boxservlet.jsp";
		
		// 박스오피스 목록 get  (S -> C)
		//List<FilmDto> list = FilmService.getFilmService().getBoxOffice();
		List<FilmDto> list = FilmService_httpconnection.getFilmService().getBoxOffice();
		
		// 박스오피스 set 		(C -> FC)
		request.setAttribute("box", list);
		
		// 경로 return 		(C -> FC)
		return path;
	}

}
