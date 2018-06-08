package controller;

import model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import view.Parser;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Title", "Main page");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String main(@RequestParam String query, Model model) throws IOException {

        List<Answer> answers;
        try {
            answers = Parser.getAnswers(query.replace(' ', '+'));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
        LOGGER.debug(answers != null ? answers.toString() : "answers is null");
        model.addAttribute("Title", "Query for stackoverflow");
        model.addAttribute("answers", answers);
        return "index";
    }

}
