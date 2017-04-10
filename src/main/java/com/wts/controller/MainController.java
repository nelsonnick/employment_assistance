package com.wts.controller;

import com.jfinal.core.Controller;
import com.wts.entity.model.User;

public class MainController extends Controller {
    public void index() {
        render("/static/html/Login.html");
    }
    public void login() {
        User user = User.dao.findFirst("SELECT * FROM user WHERE login=? AND pass=?",getPara("login"),getPara("pass"));
        if (user!=null) {
            setSessionAttr("user", user);
            renderText("OK");
        } else {
            renderText("error");
        }
    }
}
