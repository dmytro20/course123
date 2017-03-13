package ua.nure.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Controller
public class TestController {


    @RequestMapping(value = "/testController")
    ModelAndView test() {
        return new ModelAndView("tours", "message", "pisun");
    }

    @RequestMapping(value = "/home")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    ModelAndView view(@RequestParam String name) {
        return new ModelAndView("name", "list", name);
    }

    @RequestMapping(value = "/name")
    public String viewGet(Model model) {
        return "name";
    }
}
