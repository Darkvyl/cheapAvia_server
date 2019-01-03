package com.darkvyl.cheapavia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping
    public ModelAndView index(){
        return new ModelAndView();
    }

    @RequestMapping
    public ModelAndView error(){
        return new ModelAndView();
    }
}
