package com.ongiao.wis.controller.admin;

import com.ongiao.wis.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    /**
     * Home page
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return this.render("home");
    }
}
