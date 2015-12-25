package com.cinema.action;

import com.cinema.dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * LoginAction
 * Created by rayn on 2015/12/24.
 */
@ResultPath("/views")
public class LoginAction extends ActionSupport {

	@Autowired
	private UserDao userDao;
	private static Logger logger = LogManager.getLogger(LoginAction.class.getName());

	private long userCount;

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	@Action(
			value = "/login",
			results = {
					@Result(name = "success", location = "login.jsp")
			}
	)
	public String login() {
		userCount = userDao.count();
		logger.info(userCount);
		return SUCCESS;
	}
}
