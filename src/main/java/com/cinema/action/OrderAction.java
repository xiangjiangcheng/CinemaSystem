package com.cinema.action;

import com.cinema.action.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * SaleAction
 * Created by rayn on 2015/12/29.
 */
@Controller
@Scope("prototype")
public class OrderAction extends BaseAction {

    public OrderAction() {
        super(OrderAction.class);
    }

    @Action(value = "/order/seat",
            results = {
                    @Result(name = "success", location = "main/seats.jsp")
            }
    )
    public String chooseSeat() {
        return SUCCESS;
    }
}
