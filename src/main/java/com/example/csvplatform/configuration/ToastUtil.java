package com.example.csvplatform.configuration;

import org.springframework.web.servlet.ModelAndView;

public class ToastUtil {
    public static void addToast(ModelAndView mv, String message, String type) {
        mv.addObject("toastMessage", message);
        mv.addObject("toastType", type);
    }
}
