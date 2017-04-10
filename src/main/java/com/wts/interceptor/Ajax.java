package com.wts.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class Ajax implements Interceptor {

    public void intercept(Invocation inv) {
        inv.invoke();
//        if (inv.getController().getSession().getAttribute("user") == null
//                || inv.getController().getRequest().getHeader("X-Requested-With") == null
//                ) {
//            inv.getController().renderText("error");
//        } else {
//            inv.invoke();
//        }
    }
}