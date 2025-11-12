package com.example.demo;

import com.example.demo.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DemoApplicationTests {

    @Test
    void testCountIncrement() {
        Count c = new Count();
        assertEquals(0, c.getCount());
        c.increment();
        assertEquals(1, c.getCount());
    }

    @Test
    void testGetQuestionCycles() {
        GetQuestion gq = new GetQuestion();
        assertTrue(gq.nextQuestion(1).getAnswer());
        assertFalse(gq.nextQuestion(2).getAnswer());
    }

    @Test
    void testQuestionTrueFalse() {
        QuestionTrueFalse q = new QuestionTrueFalse("Test", true);
        assertEquals("Test", q.getQuestion());
        assertTrue(q.getAnswer());
    }
}
