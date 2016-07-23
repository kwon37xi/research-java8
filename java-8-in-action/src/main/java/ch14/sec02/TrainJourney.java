package ch14.sec02;

public class TrainJourney {
    public int price;
    public TrainJourney onward;
    public TrainJourney(int p, TrainJourney t) {
        price = p;
        onward = t;
    }

    // 사용하지 말 것.
    public static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }

        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    // 아예 새로운 TrainJourney 객체를 만드는 방식으로 처리
    public static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }
}
