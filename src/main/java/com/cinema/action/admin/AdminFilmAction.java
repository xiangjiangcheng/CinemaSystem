package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.FilmDao;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * AdminFilmAction
 * Created by rayn on 2015/12/28.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminFilmAction extends BaseAction {

    @Autowired
    private FilmDao filmDao;

    private String title;
    private int page;
    private int pageSize;


    public AdminFilmAction() {
        super(AdminFilmAction.class);
    }

    @Action(value = "/films",
            results = {
                    @Result(name = "success", location = "admin/film_form.jsp")
            }
    )
    public String index() {
        title = "电影管理";
        pageSize = 10;
        return SUCCESS;
    }

    /*
    @Action(value = "/films/get",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "jsonResponse"})
            }
    )
    public String films() {
        logger.info(pageSize);
        PageResult pageResult = filmDao.findAll(page, pageSize);
        jsonResponse.put("totalPage", pageResult.getPages());
        jsonResponse.put("page", page);
        jsonResponse.put("items", pageResult.getItems());
        return "json";
    }
    */

    @Action(value = "/films/add",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "jsonResponse"})
            }
    )
    public String addFilm() {

        logger.debug("");
        return "json";
    }


    public String getTitle() {
        return title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
