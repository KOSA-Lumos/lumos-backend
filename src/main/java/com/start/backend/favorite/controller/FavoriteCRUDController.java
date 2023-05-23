package com.start.backend.favorite.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.favorite.service.FavoriteService;
import com.start.backend.favorite.vo.Favorite;
import com.start.backend.information.service.InformationService;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class FavoriteCRUDController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private FavoriteService favoriteService;
	
	@PostMapping(value="/{centerNum}/favorite")
	public List<Favorite> isFavorite(@PathVariable int centerNum, @ModelAttribute Favorite favorite) {
		
		
		log.debug(favorite);
		
		List<Favorite> favoriteList = favoriteService.isFavorite(favorite);
		
		if(favoriteList.isEmpty()) {
			favoriteService.addFavorite(favorite);
			log.debug("찜하기");
		} else {
			favoriteService.removeFavorite(favorite);
			log.debug("찜제거하기");
		}
		
		
		List<Favorite> newFavoriteList = fvservice.isFavorite(favorite);
		
		return newFavoriteList;

	}
}
