package com.example.demo.controller;

import com.example.demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"count", "myString"})
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        if (!model.containsAttribute("count")) model.addAttribute("count", new Count());
        if (!model.containsAttribute("myString")) model.addAttribute("myString", new MyString());
        return "home"; // templates/home.html
    }

    @GetMapping("/get_question")
    public String questionForm(Model model) {
        Count count = (Count) model.getAttribute("count");
        if (count == null) { count = new Count(); model.addAttribute("count", count); }
        count.increment();

        GetQuestion getQuestion = new GetQuestion();
        MyString myStringObject = (MyString) model.getAttribute("myString");
        if (myStringObject == null) { myStringObject = new MyString(); }
        myStringObject.setMyString(getQuestion.nextQuestion(count.getCount()).getQuestion());

        model.addAttribute("myString", myStringObject);
        model.addAttribute("count", count);
        return "question"; // templates/question.html
    }

    @PostMapping("/get_question")
    public String questionFormPOST(@ModelAttribute("count") Count count,
                                   @ModelAttribute("myString") MyString myStringObject,
                                   @RequestParam String answer,
                                   Model model) {
        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse qtf = getQuestion.nextQuestion(count.getCount());

        boolean answerBool = Boolean.parseBoolean(answer);
        if (answerBool == qtf.getAnswer()) System.out.println("Correct!");
        else System.out.println("Wrong!");

        count.increment();
        myStringObject.setMyString(getQuestion.nextQuestion(count.getCount()).getQuestion());

        model.addAttribute("count", count);
        model.addAttribute("myString", myStringObject);
        return "question";
    }

    @GetMapping("/reset")
    public String reset(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
