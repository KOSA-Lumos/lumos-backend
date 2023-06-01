package com.start.backend.favorite.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.backend.favorite.vo.Favorite;
import com.start.backend.information.vo.Information;

@Repository
public class FavoriteDaoImpl  implements FavoriteDao {

	private Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private SqlSession session;

	@Override
	public int insertOne(Favorite favorite) {
		
		return session.insert("favorite.insertOne", favorite);
	}

	@Override
	public List<Favorite> selectList(Favorite favorite) {
		// TODO Auto-generated method stub
		return session.selectList("favorite.selectList", favorite);
	}
	
	
	@Override
	public int deleteOne(Favorite favorite) {
		
		return session.delete("favorite.deleteOne", favorite);
	}

	@Override
	public List<Information> selectFavoriteList(String userId) {

		return session.selectList("favorite.selectFavoriteList", userId);
	}
	
	

}
