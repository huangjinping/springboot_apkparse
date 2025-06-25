package com.example.demo.controller;

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

    @RequestMapping("/ios")
    public ModelAndView ios() {
//        Logger logger = LoggerFactory.getLogger("recommend");
//        System.out.println("log"+logger);
//        logger.info("ihis");

        return new ModelAndView("ios");
    }

    @RequestMapping("/iosDebug")
    public ModelAndView iosDebug() {
//        Logger logger = LoggerFactory.getLogger("recommend");
//        System.out.println("log"+logger);
//        logger.info("ihis");

        return new ModelAndView("iosDebug");
    }

    @RequestMapping("/parsepackage")
    public ModelAndView parsepackage() {
        return new ModelAndView("index");
    }

    @RequestMapping("/")
    public ModelAndView demo1(HttpServletResponse resp) throws IOException {
        return new ModelAndView("router");
    }

    @RequestMapping("/JsonNodeComparator")
    public ModelAndView JsonNodeComparator() {
        return new ModelAndView("JsonNodeComparator");
    }

    @RequestMapping("/nbjson")
    public ModelAndView nbjson() {
        return new ModelAndView("nbjson");
    }


    @RequestMapping("/index531")
    public ModelAndView index531() {
        return new ModelAndView("index531");
    }

    @RequestMapping("/appscheme")
    public ModelAndView appscheme() {
        return new ModelAndView("appscheme");
    }


    @RequestMapping("/debug531")
    public ModelAndView debug531() {
        return new ModelAndView("debug531");
    }

    @RequestMapping("/nbjson531")
    public ModelAndView nbjson531() {
        return new ModelAndView("nbjson531");
    }

    @RequestMapping("/serverTest")
    public ModelAndView serverSpider() {


        return new ModelAndView("serverTest");
    }


}
