//package com.start.backend.user.service;
//
//public class UserCRUDService {
//
//}
package com.start.backend.favorite.service;

import com.start.backend.favorite.dao.FavoriteDao;
import com.start.backend.user.dao.UserDao;
import com.start.backend.user.vo.User;
import com.start.backend.user.vo.UserSearch;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FavoriteCRUDService implements FavoriteService {
	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private FavoriteDao favoriteDao;



}
