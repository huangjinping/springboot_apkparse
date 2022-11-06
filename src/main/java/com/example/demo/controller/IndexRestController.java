package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class IndexRestController {
    @RequestMapping("/index")
    public ModelAndView index() {
//        Logger logger = LoggerFactory.getLogger("recommend");
//        System.out.println("log"+logger);
//        logger.info("ihis");

        return new ModelAndView("index");
    }

    @RequestMapping("/debug1")
    public ModelAndView debug1() {
//        Logger logger = LoggerFactory.getLogger("recommend");
//        System.out.println("log"+logger);
//        logger.info("ihis");

        return new ModelAndView("debug1");
    }

    @RequestMapping("/parsepackage")
    public ModelAndView parsepackage() {
        return new ModelAndView("index");
    }

    @RequestMapping("/")
    public ModelAndView demo1(HttpServletResponse resp) throws IOException {
        return new ModelAndView("router");
    }

    @RequestMapping("/nbjson")
    public ModelAndView nbjson() {
        return new ModelAndView("nbjson");
    }


}
