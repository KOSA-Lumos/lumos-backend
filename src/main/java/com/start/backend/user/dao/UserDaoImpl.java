//package com.start.backend.user.dao;
//
//public class UserDaoImpl {
//
//}
package com.start.backend.user.dao;

import com.start.backend.user.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SqlSession session;

	@Override
	public User addUser(String userId) {
		
		User user = session.selectOne("User.addUser", userId);
		
		return user;
	}
	
	@Override
	public User getUser(String userId) {
		
		User user = session.selectOne("User.getUser", userId);
		
		return user;
	}
	
	@Override
	public User selectKaKaoUser(User user){

		User user1 = new User();
		user1 = session.selectOne("User.getKakaoUser", user.getUserId());
		return user1;
	}

	@Override
	public int insertUser(User user) throws Exception {
		
		return session.insert("User.insertUser", user);
	}

	
	
//	@Override
//	public List<Object> getUser(String userId) {
//		
//		List<Object> user = session.selectList("User.*****", userId);
//		
//		return user;
//	}

//	@Override
//	public void insertUser(String userId) throws Exception {
//		
//		session.insert("User.insertUser", userId);
//		
//	}
	
	@Override
	public void updateUser(String userId) throws Exception {
		
		session.update("User.updateUser", userId);
		
	}
	
	@Override
	public void deleteUser(String userId) throws Exception {
		
		session.delete("User.deleteUser", userId);
		
	}

}

