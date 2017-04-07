package com.wts.controller;

import com.jfinal.core.Controller;

public class MainController extends Controller {
    public void index() {
        render("/static/html/index.html");
    }
}
