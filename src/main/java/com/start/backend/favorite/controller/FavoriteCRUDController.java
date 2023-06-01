package com.start.backend.favorite.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.favorite.service.FavoriteService;
import com.start.backend.favorite.vo.Favorite;
import com.start.backend.information.vo.Information;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8", value="/favorite")
public class FavoriteCRUDController implements FavoriteController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private FavoriteService fvService;
	
	@PostMapping(value="/{centerNum}/favorite")
	public List<Favorite> isFavorite(@PathVariable int centerNum, @ModelAttribute Favorite favorite) {
		
		
		log.debug(favorite);
		
		List<Favorite> favoriteList = fvService.isFavorite(favorite);
		
		if(favoriteList.isEmpty()) {
			fvService.addFavorite(favorite);
			log.debug("찜하기");
		} else {
			fvService.removeFavorite(favorite);
			log.debug("찜제거하기");
		}
		
		
		List<Favorite> newFavoriteList = fvService.isFavorite(favorite);
		
		return newFavoriteList;

	}
	
	@GetMapping
	public List<Information> getFavoriteList(String userId) {
		
		log.debug(userId);
		List<Information> list = fvService.favoriteList(userId);
		log.debug(list);
		
		return list;
	}

//	@Override
//	@PostMapping(value="/{centerNum}/favorite")
//	public int addFavorite(Favorite favorite) {
//		
//		int result = fvService.addFavorite(favorite);
//		
//		return result;
//	}
	
	
	
}
