package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {    //主题
    protected List<Observer> observers = new ArrayList();   //存放Observer类的对象

    public abstract void attach(Observer observer);   //往observers中添加一个Observer对象

    public abstract void detach(Observer observer);   //从observers中删除一个Observer对象

    public abstract void operate();     //通知observes中的对象进行操作

}
