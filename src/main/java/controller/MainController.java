package controller;

import model.Answer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import view.Parser;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String main(@RequestParam String query, Model model) throws IOException {
        StringBuilder sb = new StringBuilder();

        List<Answer> answers = Parser.getAnswers(query.replace(' ', '+'));
        System.out.println(answers);
        model.addAttribute("Title", "Query for stackoverflow");
        model.addAttribute("answers", answers);
        return "index";
    }

}
