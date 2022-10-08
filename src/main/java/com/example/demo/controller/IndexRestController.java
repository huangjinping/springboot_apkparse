package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexRestController {
    @RequestMapping("/index")
    public ModelAndView demo1() {
        return new ModelAndView("index");
    }


    @RequestMapping("/nbjson")
    public ModelAndView nbjson() {
        return new ModelAndView("nbjson");
    }


}
