package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import view.Parser;

import java.io.IOException;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ModelAndView main(@RequestAttribute String query) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("answers", Parser.getAnswers(query));
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
