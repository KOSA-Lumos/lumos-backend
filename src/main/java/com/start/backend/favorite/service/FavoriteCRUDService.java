package com.start.backend.favorite.service;

import com.start.backend.favorite.dao.FavoriteDao;
import com.start.backend.favorite.vo.Favorite;
import com.start.backend.information.vo.Information;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FavoriteCRUDService implements FavoriteService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private FavoriteDao fvDao;

	@Override
	public List<Favorite> isFavorite(Favorite favorite) {
		
		return fvDao.selectList(favorite);
	}

	@Override
	public int addFavorite(Favorite favorite) {
		
		return fvDao.insertOne(favorite);
	}

	@Override
	public int removeFavorite(Favorite favorite) {

		return fvDao.deleteOne(favorite);
	}

	@Override
	public List<Information> favoriteList(String userId) {

		return fvDao.selectFavoriteList(userId);
	}



}
