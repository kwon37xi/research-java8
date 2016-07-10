package ch08.sec02;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
