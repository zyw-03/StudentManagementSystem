package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers = new ArrayList();

    public abstract void attach(Observer observer);

    public abstract void detach(Observer observer);

    public abstract void operate();

}
