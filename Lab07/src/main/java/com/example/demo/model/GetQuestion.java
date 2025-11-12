package com.example.demo.model;

public class GetQuestion {
    private final QuestionTrueFalse[] questions = {
        new QuestionTrueFalse("The sky is blue.", true),
        new QuestionTrueFalse("2 + 2 = 5.", false),
        new QuestionTrueFalse("Spring Boot uses Java.", true),
        new QuestionTrueFalse("Thymeleaf is a JS framework.", false)
    };

    public QuestionTrueFalse nextQuestion(int count) {
        if (count <= 0) count = 1;
        return questions[(count - 1) % questions.length];
    }
}
