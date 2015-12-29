package com.cinema.action.admin;

import com.cinema.action.base.BaseAction;
import com.cinema.dao.CinemaHallDao;
import com.cinema.dao.FilmDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.CinemaHall;
import com.cinema.model.Film;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2015/12/28.
 */
@Namespace("/admin")
@Controller
@Scope("prototype")
public class AdminHallAction extends BaseAction {

    private String title;
    private int page;
    private int pageSize;

    private String name; //影厅名（几号厅）
    private int rowSize;
    private int columnSize;

    @Autowired
    private FilmDao filmDao;
    private CinemaHallDao cinemaHallDao;

    public AdminHallAction() {
        super(AdminHallAction.class);
    }
    /**
     * 跳转到影厅管理主页
     */
    @Action(value = "/halls",
            results = {
                    @Result(name = "success", location = "admin/halls.jsp")
            }
    )
    public String index() {
        title = "影厅管理";
        pageSize = 10;
        return SUCCESS;
    }

    /**
     * 获取影厅信息
     * @return
     */
    @Action(value = "/halls/get",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "jsonResponse"})
            }
    )
    public String halls() {
        logger.info(pageSize);
        PageResult pageResult = cinemaHallDao.findAll(page, pageSize);
        jsonResponse.put("totalPage", pageResult.getPages());
        jsonResponse.put("page", page);
        jsonResponse.put("items", pageResult.getItems());
        return "json";
    }

    /**
     * 添加影厅信息   未完成！
     * @return
     */
    @Action(value = "/halls/add",
            results = {
                    @Result(name = "json", type = "json", params = {"root", "jsonResponse"})
            }
    )
    public String add_hall() {
        CinemaHall cinemaHall = null;
        //根据影厅号 判断该影厅是否存在！



        cinemaHall.setName(name);
        cinemaHall.setColumnSize(columnSize);
        cinemaHall.setRowSize(rowSize);
        //添加
        cinemaHallDao.saveOrUpdate(cinemaHall);
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

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void setName(String name) {
        this.name = name;
    }
}
