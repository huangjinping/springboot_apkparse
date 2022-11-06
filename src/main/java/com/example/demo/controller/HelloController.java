package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HelloController {


    @RequestMapping("/demo/index")
    public ModelAndView index() {


        return new ModelAndView("index");
    }


    @RequestMapping("/demo/parsepackage")
    public ModelAndView parsepackage() {
        return new ModelAndView("index");
    }


    @RequestMapping("/demo/nbjson")

    public ModelAndView nbjson() {
        return new ModelAndView("nbjson");
    }


}