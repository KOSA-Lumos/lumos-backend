package com.start.backend.favorite.service;

import java.util.List;

import com.start.backend.favorite.vo.Favorite;
import com.start.backend.information.vo.Information;

public interface FavoriteService {

	List<Favorite> isFavorite(Favorite favorite);

	int addFavorite(Favorite favorite);

	int removeFavorite(Favorite favorite);

	List<Information> favoriteList(String userId);




}
