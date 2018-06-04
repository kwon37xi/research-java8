package ch06.lazyevaluation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

public class EvaluationTest {
    private long startTime;

    @Before
    public void setUp() throws Exception {
        startTime = Instant.now().toEpochMilli();
    }

    @Test
    public void eagerEvaluator() {
        Evaluation.eagerEvaluator(Evaluation.evaluate(1), Evaluation.evaluate(2));
    }

    @Test
    public void lazyEvaluator() {
        Evaluation.lazyEvaluator(() -> Evaluation.evaluate(1), () -> Evaluation.evaluate(2));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("elapsed : " + (Instant.now().toEpochMilli() - startTime));
    }
}
