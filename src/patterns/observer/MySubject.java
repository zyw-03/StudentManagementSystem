package patterns.observer;


import patterns.*;
public class MySubject extends Subject{


    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);

    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void operate() {
        for(Observer observer : observers){
            observer.updateTableView();
        }
    }
}
