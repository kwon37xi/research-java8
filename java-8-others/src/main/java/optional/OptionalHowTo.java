package optional;

import java.util.Optional;

/**
 * <a href="https://dzone.com/articles/java-8-optional-replace-your-get-calls">Java 8 Optional—Replace Your Get() Calls</a>
 */
public class OptionalHowTo {
    public static void main(String[] args) {
        Optional<String> petNameOp = Optional.of("Bobby");
        System.out.println("petNameOp : " + petNameOp.orElse(""));

        petNameOp = Optional.empty();
        System.out.println("petNameOp empty : " + petNameOp.orElse(""));

        try {
            petNameOp = Optional.empty();
            petNameOp.orElseThrow(() -> new IllegalArgumentException("no pet name"));
        } catch (IllegalArgumentException ex) {
            System.out.println("orElseThrow : " + ex.getMessage());
        }

        petNameOp = Optional.of("Bobby");

        System.out.println("filter out empty name (not empty) : " +
            petNameOp.filter(name -> !name.trim().isEmpty()).orElseThrow(IllegalArgumentException::new));

        try {
            petNameOp = Optional.empty();

            petNameOp.filter(name -> !name.trim().isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("name is null"));
        } catch (IllegalArgumentException ex) {
            System.out.println("filter out empty name (null) : " + ex.getMessage());
        }

        try {
            petNameOp = Optional.of("  ");

            petNameOp.filter(name -> !name.trim().isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("name is empty"));
        } catch (IllegalArgumentException ex) {
            System.out.println("filter out empty name (empty) : " + ex.getMessage());
        }

        // ifPresent

        Optional<LoyaltyCard> loyaltyCardOp = Optional.of(new LoyaltyCard("card", 0));

        loyaltyCardOp.ifPresent(c -> c.addPoints(3));

        System.out.println("addPoint(3) point : " + loyaltyCardOp.get().getPoints());

        // map : null이 아니면 매핑을 하고, null이면 empty Optional 리턴
        loyaltyCardOp = Optional.of(new LoyaltyCard("card", 123));
        System.out.println("loyalty card point result : " + loyaltyCardOp.map(LoyaltyCard::getPoints).orElse(-123));

        loyaltyCardOp = Optional.empty();
        System.out.println("loyalty card point empty result : " + loyaltyCardOp.map(LoyaltyCard::getPoints).orElse(-123));

        // flatMap : map과 같지만 map 결과가 Optional일 때는 Optional을 벗겨내고 리턴.

        loyaltyCardOp = Optional.of(new LoyaltyCard("flatMapTest", 0, new Gift("Biography of Guybrush Threepwood")));

        System.out.println("flatMap gift name : " + loyaltyCardOp.flatMap(LoyaltyCard::getLastGift).map(Gift::getName).orElse("no gift"));

    }

    public static class LoyaltyCard {
        private String cardName;
        private int points;

        private Gift lastGift;

        public LoyaltyCard(String cardName, int points) {
            this.cardName = cardName;
            this.points = points;
        }

        public LoyaltyCard(String cardName, int points, Gift lastGift) {
            this.cardName = cardName;
            this.points = points;
            this.lastGift = lastGift;
        }

        public int addPoints(int pointToAdd) {
            return points += pointToAdd;
        }

        public String getCardName() {
            return cardName;
        }

        public int getPoints() {
            return points;
        }

        public Optional<Gift> getLastGift() {
            return Optional.ofNullable(lastGift);
        }
    }

    public static class Gift {
        private String name;

        public Gift(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
