package com.ongiao.wis.controller;

public abstract class BaseController {

    public static String THEME = "themes/default";

    /**
     * Return thymeleaf template given the view name
     * @param viewName
     * @return
     */
    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    /**
     * Redirect to another page given the url
     * @param url
     * @return
     */
    public String redirect(String url) {
        return "redirect:" + url;
    }
}
