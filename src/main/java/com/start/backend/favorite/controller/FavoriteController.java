package com.start.backend.favorite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.start.backend.favorite.vo.Favorite;

public interface FavoriteController {
	
	List<Favorite> isFavorite(@PathVariable int centerNum, @ModelAttribute Favorite favorite);
	
//	int addFavorite(Favorite favorite);

}
