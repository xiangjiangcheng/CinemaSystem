package com.cinema.dao.impl;

import com.cinema.dao.UserDao;
import com.cinema.dao.generic.HibernateCurdDao;
import com.cinema.dao.generic.HibernatePageableDao;
import com.cinema.model.User;
import org.springframework.stereotype.Repository;

/**
 * UserDao implement
 * Created by rayn on 2015/12/24.
 */
@Repository("userDao")
public class UserDaoImpl extends HibernatePageableDao<User, Long>
		implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
}
