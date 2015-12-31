package com.cinema.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cinema.action.base.BaseAction;
import com.cinema.dao.CinemaSaleDao;
import com.cinema.dao.OrderDao;
import com.cinema.dao.SeatDao;
import com.cinema.dao.UserDao;
import com.cinema.dao.generic.PageResult;
import com.cinema.model.*;
import com.cinema.util.LoginHelper;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * OrderAction
 * Created by rayn on 2015/12/29.
 */
@Controller
@Scope("prototype")
public class OrderAction extends BaseAction {

    @Autowired
    private CinemaSaleDao cinemaSaleDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private UserDao userDao;

    private String title;
    private long saleId;
    private CinemaSale sale;
    private Film film;

    private int page;
    private int pageSize;

    public OrderAction() {
        super(OrderAction.class);
    }

    @Action(value = "/sale/seat",
            results = {
                    @Result(name = "success", location = "/views/main/seats.jsp")
            }
    )
    public String chooseSeat() {
        sale = cinemaSaleDao.findOne(saleId);
        film = sale.getFilm();
        title = film.getFilmName() + " - 选座";
        return SUCCESS;
    }

    @Action(value = "/sale/seat/get")
    public String getSeats() {
        sale = cinemaSaleDao.findOne(saleId);
        jsonResponse.put("ret", JsonResult.OK);
        jsonResponse.put("items", sale.getSeats());
        return "json";
    }


    @Action(value = "/order/add")
    public String addOrder() {
        User customer = userDao.findOne(LoginHelper.getCurrentUser(session).getId());
        JSONObject jsonObject = JSON.parseObject(request.getParameter("request"));
        saleId = (Integer) jsonObject.get("saleId");
        sale = cinemaSaleDao.findOne(saleId);

        List jsonSeats = (List) jsonObject.get("seats");
        Set<Seat> seatSet = new HashSet<Seat>();
        for (Object obj : jsonSeats) {
            Map jsonSeat = (Map) obj;
            int row = (Integer) jsonSeat.get("row");
            int col = (Integer) jsonSeat.get("col");
            if (seatDao.findByCinemaSaleWithRowAndCol(sale, row, col) != null) {
                jsonResponse.put("ret", JsonResult.FAIL);
                jsonResponse.put("error", "已售座位不能购买");
            }
            Seat seat = new Seat();
            seat.setCinemaSale(sale);
            seat.setRowNumber(row);
            seat.setColNumber(col);
            //seat = seatDao.create(seat);
            seatSet.add(seat);
        }
        Order order = new Order();
        order.setCinemaSale(sale);
        order.setUser(customer);
        order.setOrderTime(new Date());
        order = orderDao.create(order);
        for (Seat s : seatSet) {
            s.setOrder(order);
            seatDao.saveOrUpdate(s);
        }
        jsonResponse.put("ret", JsonResult.OK);
        return "json";
    }


    @Action(value = "/orders/self",
            results = {
                    @Result(name = "success", location = "/views/main/orders.jsp")
            }
    )
    public String myOrders() {
        title = "我的订单";
        return SUCCESS;
    }

    @Action(value = "/orders/get")
    public String getOrders() {
        User user = userDao.findOne(LoginHelper.getCurrentUser(session).getId());
        logger.info("page:" + page + "pageSize:" + pageSize);
        if (user.isAdmin()) {
            PageResult<Order> pageResult = orderDao.findAllWithOrder(page, pageSize, "orderTime", "desc");
            jsonResponse.put("totalPage", pageResult.getPages());
            jsonResponse.put("page", page);
            jsonResponse.put("items", pageResult.getItems());
        } else {
            Set<Order> orderSet = user.getOrders();
            jsonResponse.put("totalPage", 1);
            jsonResponse.put("page", 1);
            jsonResponse.put("items", orderSet);
        }
        return "json";
    }


    public String getTitle() {
        return title;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public CinemaSale getSale() {
        return sale;
    }

    public Film getFilm() {
        return film;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
