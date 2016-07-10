package ch08.sec02;

public class LambdaAndStrategyPatternExample {
    public static void main(String[] args) {

        validateWithStrategies();
        validateWithLambdas();
    }

    private static void validateWithStrategies() {
        System.out.println("== strategy pattern");
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbb");

        System.out.println("numeric validate : " + b1 + ", lowerCase validate : " + b2);
    }

    private static void validateWithLambdas() {
        System.out.println("== strategy pattern with lambda");

        Validator numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b1 = numericValidator.validate("aaa");
        Validator lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println("lambda numeric validate : " + b1 + ", lowerCase validate : " + b2);
    }
}
