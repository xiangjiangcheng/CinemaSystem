package com.cinema.action;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.model.User;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * IndexAction
 * Created by rayn on 2015/12/27.
 */
@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {

	@Autowired
	private UserDao userDao;

	public IndexAction() {
		super(IndexAction.class);
	}

	@Action(value = "/",
			results = {
					@Result(name = "login", type = "redirect",
							params = { "location", "/login" }),
					@Result(name = "admin", type = "redirect",
							params = { "location", "admin/users" }),
					@Result(name = "normal", type = "redirect",
							params = { "location", "index" })
			}
	)
	public String main() {
		User user = (User) session.getAttribute(LoginHelper.USER_SESSION);
		if (user == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user")) {
						String username = "";
						try {
							username = URLDecoder.decode(cookie.getValue(), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						user = userDao.findByUsername(username);
						if (user != null) {
							session.setAttribute(LoginHelper.USER_SESSION, user);
							return LoginHelper.dispatchUser(user);
						}
					}
				}
			}
		} else {
			return LoginHelper.dispatchUser(user);
		}
		return LOGIN;
	}

	@Action(value = "/index",
			results = {
					@Result(name = "success", location = "main/index.jsp")
			}
	)
	public String index() {
		return SUCCESS;
	}
}
