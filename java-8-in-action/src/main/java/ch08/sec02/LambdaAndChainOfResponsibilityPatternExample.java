package ch08.sec02;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class LambdaAndChainOfResponsibilityPatternExample {
    public static void main(String[] args) {
        corWithClasses();
        corWithLambdas();
    }

    private static void corWithClasses() {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);
    }

    private static void corWithLambdas() {
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        String result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }
}
