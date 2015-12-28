package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * AdminUserAction
 * Created by rayn on 2015/12/27.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminUserAction extends BaseAction {

	@Autowired
	private UserDao userDao;

	private String title;


	public AdminUserAction() {
		super(AdminUserAction.class);
	}

	@Action(value = "/users",
			results = {
					@Result(name = "success", location = "admin/users.jsp")
			}
	)
	public String index() {
		title = "用户管理";
		return SUCCESS;
	}

	@Action(value = "/users/get",
			results = {
					@Result(name = "json", type = "json", params = {"root", "jsonResponse"})
			}
	)
	public String users() {
		jsonResponse.put("users", userDao.findAll());
		return "json";
	}

	public String getTitle() {
		return title;
	}
}
