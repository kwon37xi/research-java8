package ch08.sec02;

public abstract class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Loan extends Product {
        public Loan() {
            super("loan");
        }
    }

    public static class Stock extends Product {
        public Stock() {
            super("stock");
        }
    }

    public static class Bond extends Product {
        public Bond() {
            super("bond");
        }
    }
}
