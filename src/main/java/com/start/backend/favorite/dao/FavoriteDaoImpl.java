//package com.start.backend.user.dao;
//
//public class UserDaoImpl {
//
//}
package com.start.backend.favorite.dao;

import com.start.backend.user.vo.User;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FavoriteDaoImpl implements FavoriteDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;
	
	


}

