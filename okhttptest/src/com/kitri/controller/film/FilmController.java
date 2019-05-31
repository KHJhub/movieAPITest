package com.kitri.controller.film;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import com.kitri.dto.FilmDto;
import com.kitri.service.film.FilmService;

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
		
		String path = "/youngfilmapi.jsp";
		
		// 박스오피스 목록 get  (S -> C)
		List<FilmDto> list = FilmService.getFilmService().getBoxOffice();
		
		// 박스오피스 set 		(C -> FC)
		request.setAttribute("box", list);
		
		// 경로 return 		(C -> FC)
		return path;
	}

}
