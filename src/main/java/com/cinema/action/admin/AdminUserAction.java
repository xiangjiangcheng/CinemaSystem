package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.UserDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
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
	private int page;
	private int pageSize;

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
		pageSize = 10;
		return SUCCESS;
	}

	@Action(value = "/users/get",
			results = {
					@Result(name = "json", type = "json", params = {"root", "jsonResponse"})
			}
	)
	public String users() {
		logger.info(pageSize);
		PageResult pageResult = userDao.findAll(page, pageSize);
		jsonResponse.put("totalPage", pageResult.getPages());
		jsonResponse.put("page", page);
		jsonResponse.put("items", pageResult.getItems());
		return "json";
	}

	@Action(value = "/users/del",
			results = {
					@Result(name = "json", type = "json", params = {"root", "jsonResponse"})
			}
	)
	public String deleteUser() {
		long user_id = Long.parseLong(request.getParameter("id"));
		if (user_id == LoginHelper.getCurrentUser(session).getId()) {
			jsonResponse.put("ret", JsonResult.FAIL);
			jsonResponse.put("error", "不能删除本人账户");
		} else {
			userDao.delete(user_id);
			jsonResponse.put("ret", JsonResult.OK);
		}
		return "json";
	}

	@Action(value = "/users/edit",
			results = {
					@Result(name = "json", type = "json", params = {"root", "jsonResponse"})
			}
	)
	public String editUser() {
		String jsonString = request.getParameter("item");
		logger.info(jsonString);
		return "json";
	}

	public String getTitle() {
		return title;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
