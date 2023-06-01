package com.start.backend.favorite.dao;

import java.util.List;

import com.start.backend.favorite.vo.Favorite;
import com.start.backend.information.vo.Information;



public interface FavoriteDao {

	int insertOne(Favorite favorite);

	List<Favorite> selectList(Favorite favorite);

	int deleteOne(Favorite favorite);

	List<Information> selectFavoriteList(String userId);

}
